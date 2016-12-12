package com.news.mvp.model.impl;

import com.news.mvp.callback.RequestCallBack;
import com.news.mvp.entity.NewsSummary;
import com.news.mvp.model.NewsListModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class NewsListModelImpl implements NewsListModel<List<NewsSummary>> {
    @Inject
    public NewsListModelImpl(){

    }

    @Override
    public Disposable loadNews(RequestCallBack<List<NewsSummary>> callBack,final String type,final String id, int startPage) {
//        return RetrofitManager.getInstance(HostType.NETEASE_NEWS_VIDEO).getNewsListFlowable(type,id,startPage)
//                .flatMap(new Function<Map<String, List<NewsSummary>>, Publisher<?>>() {
//                    @Override
//                    public Publisher<?> apply(Map<String, List<NewsSummary>> stringListMap) throws Exception {
//                        if (id.endsWith(ApiConstants.HOUSE_ID)){
//                            // 房产实际上针对地区的它的id与返回key不同
//                            return Flowable.fromIterable(stringListMap.get("北京"));
//                        }
//                        return Flowable.fromIterable(stringListMap.get(id));
//                    }
//                }).map(new Function<NewsSummary, NewsSummary>() {
//                    @Override
//                    public NewsSummary apply(NewsSummary newsSummary) throws Exception {
//                        return newsSummary;
//                    }
//                },).
//                .map(new Function<NewsSummary, NewsSummary>() {
//                    @Override
//                    public NewsSummary apply(NewsSummary newsSummary) throws Exception {
//                       String time= MyUtils.formatDate(newsSummary.getPtime());
//                        newsSummary.setPtime(time);
//                        return newsSummary;
//                    }
//                }).distinct().
//
//                ;
        return null;
    }
}
