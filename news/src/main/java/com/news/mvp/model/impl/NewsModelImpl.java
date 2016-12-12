package com.news.mvp.model.impl;

import com.news.App;
import com.news.R;
import com.news.db.NewsChannel;
import com.news.db.NewsChannelManager;
import com.news.mvp.callback.RequestCallBack;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.BackpressureStrategy;
import io.reactivex.Flowable;
import io.reactivex.FlowableEmitter;
import io.reactivex.FlowableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class NewsModelImpl implements NewsModel<List<NewsChannel>>{
    @Inject
    public NewsModelImpl() {
    }

    @Override
    public Disposable lodeNewsChannels(final RequestCallBack<List<NewsChannel>> callBack) {
        return Flowable.create(new FlowableOnSubscribe<List<NewsChannel>>() {
            @Override
            public void subscribe(FlowableEmitter<List<NewsChannel>> e) throws Exception {
                NewsChannelManager.initDb();
                e.onNext(NewsChannelManager.loadNewsChannelsMine());
                e.onComplete();
            }
        }, BackpressureStrategy.BUFFER)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<NewsChannel>>() {
                    @Override
                    public void accept(List<NewsChannel> newsChannels) throws Exception {
                        callBack.success(newsChannels);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        callBack.onError(App.getAppContext().getString(R.string.db_error));
                    }
                });
    }
}
