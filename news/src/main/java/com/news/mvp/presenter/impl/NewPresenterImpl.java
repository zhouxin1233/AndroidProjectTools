package com.news.mvp.presenter.impl;

import com.news.db.NewsChannel;
import com.news.mvp.model.NewsModel;
import com.news.mvp.model.impl.NewsModelImpl;
import com.news.mvp.presenter.NewsPresenter;
import com.news.mvp.presenter.base.BasePresenterImpl;
import com.news.mvp.view.NewsView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class NewPresenterImpl extends BasePresenterImpl<NewsView, List<NewsChannel>> implements NewsPresenter {
    private NewsModel<List<NewsChannel>> mNewsModel;

    @Inject
    public NewPresenterImpl(NewsModelImpl newsModel) {
        mNewsModel = newsModel;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        loadNewsChannels();
    }

    private void loadNewsChannels() {
        mDisposable = mNewsModel.lodeNewsChannels(this);
    }

    @Override
    public void onChannelDbChanged() {
        loadNewsChannels();
    }

    @Override
    public void success(List<NewsChannel> data) {
        super.success(data);
        mView.initViewPager(data);
    }
}
