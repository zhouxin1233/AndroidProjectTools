package com.news.mvp.ui.fragment.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.news.App;
import com.news.dagger.component.DaggerFragmentComponent;
import com.news.dagger.component.FragmentComponent;
import com.news.dagger.module.FragmentModule;
import com.news.mvp.presenter.base.BasePresenter;
import com.news.utils.MyUtils;
import com.squareup.leakcanary.RefWatcher;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2016/12/11 0011.
 */

public abstract class BaseFragment<T extends BasePresenter> extends Fragment {
    protected FragmentComponent mFragmentComponent;
    protected T mPresenter;
    public abstract void initInjector();
    private View mFragmentView;
    public abstract void initViews(View view);
    public abstract int getLayoutId();
    protected Disposable mDisposable;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mFragmentComponent= DaggerFragmentComponent.builder()
                .applicationComponent(App.getApp().getApplicationComponent())
                .fragmentModule(new FragmentModule(this))
                .build();
        initInjector();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mFragmentView == null) {
            mFragmentView = inflater.inflate(getLayoutId(), container, false);
            initViews(mFragmentView);
        }
        return mFragmentView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher();
        refWatcher.watch(this);

        if (mPresenter != null) {
            mPresenter.onDestroy();
        }

        MyUtils.cancelSubscription(mDisposable);
    }
}
