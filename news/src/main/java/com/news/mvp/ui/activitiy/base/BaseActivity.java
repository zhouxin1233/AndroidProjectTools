package com.news.mvp.ui.activitiy.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;

import com.blankj.utilcode.utils.LogUtils;
import com.news.App;
import com.news.R;
import com.news.annotations.BindValues;
import com.news.dagger.component.ActivityComponent;
import com.news.dagger.component.DaggerActivityComponent;
import com.news.dagger.module.ActivityModule;
import com.news.mvp.presenter.base.BasePresenter;
import com.news.utils.MyUtils;
import com.news.utils.NetUtils;
import com.squareup.leakcanary.RefWatcher;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity{
    private static String tag="tag_BaseActivity";
    protected boolean mIsHasNavigationView;
    protected ActivityComponent mActivityComponent;
    private boolean mIsAddedView;
    private WindowManager mWindowManager = null;
    private View mNightView = null;
    protected T mPresenter;
    protected Disposable mDisposable;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(tag,getClass().getSimpleName());
        initAnnotation();//获取自定义的注解,是否显示抽屉导航
        NetUtils.isNetworkErrThenShowMsg();//检查网络
        initActivityComponent();
//        setStatusBarTranslucent();
        setNightOrDayMode();
        int layoutId=getLayoutId();
        setContentView(layoutId);
        initInjector();
//        initToolBar();
        initView();
        if (mIsHasNavigationView){
            initDrawerLayout();
        }
        if (mPresenter != null) {
            mPresenter.onCreate();
        }
//        initNightModeSwitch();
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
    private void setNightOrDayMode() {
        if(MyUtils.isNightMode()){//夜间模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
            initNightView();
            mNightView.setBackgroundResource(R.color.night_mask);
        }else {//白天模式
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

    }
    private void initNightView(){
        if (mIsAddedView) {
            return;
        }
        // 增加夜间模式蒙板
        WindowManager.LayoutParams nightViewParam = new WindowManager.LayoutParams(
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSPARENT);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        mNightView = new View(this);
        mWindowManager.addView(mNightView, nightViewParam);
        mIsAddedView = true;
    }
    public abstract int getLayoutId();
    public abstract void initInjector();
    public abstract void initView();
    private void initDrawerLayout() {

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RefWatcher refWatcher = App.getRefWatcher();
        refWatcher.watch(this);
        removeNightModeMask();
        //取消订阅事件....
        MyUtils.cancelSubscription(mDisposable);
        //解决InputMethodManager内存泄露现象
        MyUtils.fixInputMethodManagerLeak(this);
    }
    private void removeNightModeMask() {
        if (mIsAddedView) {
            // 移除夜间模式蒙板
            mWindowManager.removeViewImmediate(mNightView);
            mWindowManager = null;
            mNightView = null;
        }
    }
}
