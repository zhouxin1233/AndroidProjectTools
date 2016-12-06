package com.news.mvp.callback;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public interface RequestCallBack<T> {
    void beforeRequest();

    void success(T data);

    void onError(String errorMsg);
}
