package com.news.mvp.presenter.impl;

import com.news.db.NewsChannel;
import com.news.mvp.presenter.NewsPresenter;
import com.news.mvp.presenter.base.BasePresenterImpl;
import com.news.mvp.view.NewsView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public class NewPresenterImpl extends BasePresenterImpl<NewsView,List<NewsChannel>> implements NewsPresenter {
    @Override
    public void onChannelDbChanged() {

    }
}
