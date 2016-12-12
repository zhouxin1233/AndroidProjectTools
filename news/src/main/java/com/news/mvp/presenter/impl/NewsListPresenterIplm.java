package com.news.mvp.presenter.impl;

import android.support.annotation.NonNull;

import com.news.mvp.callback.RequestCallBack;
import com.news.mvp.entity.NewsSummary;
import com.news.mvp.presenter.NewsListPresenter;
import com.news.mvp.presenter.base.BasePresenterImpl;
import com.news.mvp.view.NewListView;
import com.news.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class NewsListPresenterIplm extends BasePresenterImpl<NewListView,List<NewsSummary>>
        implements NewsListPresenter, RequestCallBack<List<NewsSummary>> {
    @Override
    public void setNewsTypeAndId(String newsType, String newsId) {

    }

    @Override
    public void refreshData() {

    }

    @Override
    public void loadMore() {

    }

    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(@NonNull BaseView view) {

    }

    @Override
    public void onDestroy() {

    }
}
