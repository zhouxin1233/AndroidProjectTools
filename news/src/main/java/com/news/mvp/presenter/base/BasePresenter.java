package com.news.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.news.mvp.view.base.BaseView;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public interface BasePresenter {
    void onCreate();

    void attachView(@NonNull BaseView view);

    void onDestroy();
}
