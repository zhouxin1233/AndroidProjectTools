package com.news.mvp.ui.activitiy.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.blankj.utilcode.utils.LogUtils;
import com.news.App;
import com.news.annotations.BindValues;
import com.news.dagger.component.ActivityComponent;
import com.news.dagger.component.DaggerActivityComponent;
import com.news.dagger.module.ActivityModule;
import com.news.mvp.presenter.base.BasePresenter;
import com.news.utils.NetUtils;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class BaseActivity<T extends BasePresenter> extends AppCompatActivity{
    private static String tag="tag_BaseActivity";
    protected boolean mIsHasNavigationView;
    protected ActivityComponent mActivityComponent;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(tag,getClass().getSimpleName());
        initAnnotation();//获取自定义的注解,是否显示抽屉导航
        NetUtils.isNetworkErrThenShowMsg();//检查网络
        initActivityComponent();
//        setStatusBarTranslucent();

    }
    private void initAnnotation() {
        if (getClass().isAnnotationPresent(BindValues.class)) {
            BindValues annotation = getClass().getAnnotation(BindValues.class);
            mIsHasNavigationView = annotation.mIsHasNavigationView();
        }
    }
    private void initActivityComponent(){
        mActivityComponent= DaggerActivityComponent.builder()
                .applicationComponent(App.getApp().getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }
}
