package com.news.mvp.model;

import com.news.mvp.callback.RequestCallBack;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public interface NewsModel<T> {
    Disposable lodeNewsChannels(RequestCallBack<T> callBack);
}
