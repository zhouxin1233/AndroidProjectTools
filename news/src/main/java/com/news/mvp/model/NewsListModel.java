package com.news.mvp.model;

import com.news.mvp.callback.RequestCallBack;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public interface NewsListModel<T> {
    Disposable loadNews(RequestCallBack<T> callBack,String type,String id,int startPage);
}
