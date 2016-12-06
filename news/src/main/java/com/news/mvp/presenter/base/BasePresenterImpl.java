package com.news.mvp.presenter.base;

import android.support.annotation.NonNull;

import com.news.mvp.callback.RequestCallBack;
import com.news.mvp.view.base.BaseView;
import com.news.utils.MyUtils;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class BasePresenterImpl<T extends BaseView, E> implements BasePresenter,RequestCallBack<E>{
    protected T mView;
    protected Disposable mDisposable;
    @Override
    public void onCreate() {

    }

    @Override
    public void attachView(@NonNull BaseView view) {
        mView= (T) view;
    }

    @Override
    public void onDestroy() {
        MyUtils.cancelSubscription(mDisposable);
    }

    @Override
    public void beforeRequest() {
        mView.showProgress();
    }

    @Override
    public void success(E data) {
        mView.hideProgress();
    }

    @Override
    public void onError(String errorMsg) {
        mView.hideProgress();
        mView.showMsg(errorMsg);
    }
}
