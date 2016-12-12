package com.news.network;

import com.news.mvp.entity.GirlData;
import com.news.mvp.entity.NewsDetail;
import com.news.mvp.entity.NewsSummary;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Url;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public interface NewsService {
    @GET("nc/article/{type}/{id}/{startPage}-20.html")
    Flowable<Map<String,List<NewsSummary>>> getNewsList(
            @Header("Cache-Control") String cacheControl,
            @Path("type") String type,@Path("id") String id,
            @Path("startPage") int startPage
    );
    @GET("nc/article/{postId}/full.html")
    Flowable<Map<String, NewsDetail>> getNewDetail(
            @Header("Cache-Control") String cacheControl,
            @Path("postId") String postId);
    @GET
    Flowable<ResponseBody> getNewsBodyHtmlPhoto(
            @Url String photoPath);
    //@Url，它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
    // baseUrl 需要符合标准，为空、""、或不合法将会报错

    @GET("data/福利/{size}/{page}")
    Flowable<GirlData> getPhotoList(
            @Path("size") int size,
            @Path("page") int page);
}
