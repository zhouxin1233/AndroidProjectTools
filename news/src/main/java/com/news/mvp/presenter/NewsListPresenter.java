package com.news.mvp.presenter;

import com.news.mvp.presenter.base.BasePresenter;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public interface NewsListPresenter extends BasePresenter {
    void setNewsTypeAndId(String newsType, String newsId);

    void refreshData();

    void loadMore();
}
