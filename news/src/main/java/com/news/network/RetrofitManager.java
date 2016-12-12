package com.news.network;

import android.util.SparseArray;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.NetworkUtils;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import com.news.App;
import com.news.common.ApiConstants;
import com.news.common.HostType;
import com.news.mvp.entity.NewsSummary;

import org.greenrobot.greendao.annotation.NotNull;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class RetrofitManager {
    private NetService mNetService;

    //设缓存有效期为两天
    private static final long CACHE_STALE_SEC = 60 * 60 * 24 * 2;

    //查询缓存的Cache-Control设置，为if-only-cache时只查询缓存而不会请求服务器，max-stale可以配合设置缓存失效时间
    //max-stale 指示客户机可以接收超出超时期间的响应消息。如果指定max-stale消息的值，那么客户机可接收超出超时期指定值之内的响应消息。
    private static final String CACHE_CONTROL_CACHE = "only-if-cached, max-stale=" + CACHE_STALE_SEC;

    //查询网络的Cache-Control设置，头部Cache-Control设为max-age=0
    // (假如请求了服务器并在a时刻返回响应结果，则在max-age规定的秒数内，浏览器将不会发送对应的请求到服务器，数据由缓存直接返回)时则不会使用缓存而请求服务器
    private static final String CACHE_CONTROL_AGE = "max-age=0";

    private static volatile OkHttpClient sOkHttpClient;

    private static SparseArray<RetrofitManager> sRetrofitManager=new SparseArray<>(HostType.TYPE_COUNT);

    public RetrofitManager(@HostType.HostTypeChecker int hostType){
        Retrofit retrofit=new Retrofit.Builder().baseUrl(ApiConstants.getHost(hostType))
                .client(getsOkHttpClient()).addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        mNetService=retrofit.create(NetService.class);
    }
    private OkHttpClient getsOkHttpClient(){
        if (sOkHttpClient==null){
            synchronized (RetrofitManager.class){
                Cache cache=new Cache(new File(App.getAppContext().getCacheDir(),"HttpCache"),1024*1024*100);
                if (sOkHttpClient==null){
                    sOkHttpClient=new OkHttpClient.Builder().cache(cache)
                            .connectTimeout(6, TimeUnit.SECONDS)
                            .readTimeout(6,TimeUnit.SECONDS)
                            .writeTimeout(6,TimeUnit.SECONDS)
                            .addInterceptor(mRewriteCacheControlInterceptor)
                            .addNetworkInterceptor(mRewriteCacheControlInterceptor)
                            .addInterceptor(mLoggingInterceptor).build();
                }
            }
        }
        return sOkHttpClient;
    }
    /**
     * 云端响应头拦截器，用来配置缓存策略
     */
    private final Interceptor mRewriteCacheControlInterceptor=new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request=chain.request();
            if (!NetworkUtils.isConnected(App.getAppContext())){
                request=request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                LogUtils.d("tag===RetrofitManager","当前没有网络");
            }
            Response originalReponse=chain.proceed(request);
            if (NetworkUtils.isConnected(App.getAppContext())){
                //有网的时候读接口上的@Headers里的配置，你可以在这里进行统一的设置
                String cacheControl = request.cacheControl().toString();
                return originalReponse.newBuilder()
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")
                        .build();
            }else{
                return originalReponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + CACHE_STALE_SEC)
                        .removeHeader("Pragma")
                        .build();
            }
        }
    };
    private final Interceptor mLoggingInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            long t1 = System.nanoTime();
            LogUtils.i(String.format("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers()));
            Response response = chain.proceed(request);
            long t2 = System.nanoTime();
            LogUtils.i(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    };
    /**
     * @param hostType NETEASE_NEWS_VIDEO：1 （新闻，视频），GANK_GIRL_PHOTO：2（图片新闻）;
     *                 EWS_DETAIL_HTML_PHOTO:3新闻详情html图片)
     */
    public static RetrofitManager getInstance(int hostType){
        RetrofitManager retrofitManager=sRetrofitManager.get(hostType);
        if (retrofitManager==null){
            retrofitManager=new RetrofitManager(hostType);
            sRetrofitManager.put(hostType,retrofitManager);
            return retrofitManager;
        }
        return retrofitManager;
    }

    /**
     * 根据网络状况获取缓存的策略
     */
    @NotNull
    private String getCacheControl(){
        return NetworkUtils.isConnected(App.getAppContext())? CACHE_CONTROL_AGE:CACHE_CONTROL_CACHE;
    }

    /**
     *  example：http://c.m.163.com/nc/article/headline/T1348647909107/0-20.html
     */
    public Observable<Map<String,List<NewsSummary>>> getNewsListObservable(
            String newsType,String newsId,int startPage){
        return mNetService.getNewsList(getCacheControl(),newsType,newsId,startPage);
    }
}
