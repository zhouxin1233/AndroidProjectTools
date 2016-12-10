package com.news.mvp.model.impl;

import com.news.db.NewsChannel;
import com.news.mvp.callback.RequestCallBack;
import com.news.mvp.model.NewsModel;

import java.util.List;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class NewsModelImpl implements NewsModel<List<NewsChannel>>{
    public NewsModelImpl() {
    }

    @Override
    public Disposable lodeNewsChannels(RequestCallBack<List<NewsChannel>> callBack) {
//        return Flowable.create(new FlowableOnSubscribe<List<NewsChannel>>() {
//            @Override
//            public void subscribe(FlowableEmitter<List<NewsChannel>> e) throws Exception {
//                NewsChannelManager.initDb();
//                e.onNext(NewsChannelManager.loadNewsChannelsMine());
//                e.onComplete();
//            }
//        }, BackpressureStrategy.BUFFER)
//                .compose().subscribe();
        return null;
    }
}
