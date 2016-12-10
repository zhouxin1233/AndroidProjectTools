package com.news.mvp.ui.activitiy.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.blankj.utilcode.utils.LogUtils;
import com.news.App;
import com.news.R;
import com.news.annotations.BindValues;
import com.news.dagger.component.ActivityComponent;
import com.news.dagger.component.DaggerActivityComponent;
import com.news.dagger.module.ActivityModule;
import com.news.mvp.presenter.base.BasePresenter;
import com.news.mvp.ui.activitiy.NewsActivity;
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
    protected NavigationView mBaseNavView;
    private boolean mIsChangeTheme;
    private DrawerLayout mDrawerLayout;
    private Class mClass;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.i(tag,getClass().getSimpleName());
        initAnnotation();//获取自定义的注解,是否有抽屉导航
        NetUtils.isNetworkErrThenShowMsg();//检查网络
        initActivityComponent();
//        setStatusBarTranslucent();
        setNightOrDayMode();
        int layoutId=getLayoutId();
        setContentView(layoutId);
        initInjector();
        initToolBar();
        initView();
        if (mIsHasNavigationView){
            initDrawerLayout();
        }
        if (mPresenter != null) {
            mPresenter.onCreate();
        }
        initNightModeSwitch();
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
    private void initToolBar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }
    public abstract void initView();
    private void initDrawerLayout() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        NavigationView navView = (NavigationView) findViewById(R.id.nav_view);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        if (navView != null) {
            navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem item) {
                    switch (item.getItemId()) {
                        case R.id.nav_news:
                            mClass = NewsActivity.class;
                            break;
                        case R.id.nav_photo:
//                            mClass = PhotoActivity.class;
                            break;
                        case R.id.nav_video:
                            Toast.makeText(BaseActivity.this, "施工准备中...", Toast.LENGTH_SHORT).show();
                            break;
                        case R.id.nav_night_mode:
                            break;
                    }
                    mDrawerLayout.closeDrawer(GravityCompat.START);
                    return false;
                }
            });
        }

        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (mClass != null) {
                    Intent intent = new Intent(BaseActivity.this, mClass);
                    // 此标志用于启动一个Activity的时候，若栈中存在此Activity实例，则把它调到栈顶。不创建多一个
                    intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                    startActivity(intent);
                    overridePendingTransition(0, 0);
                    mClass = null;
                }

                if (mIsChangeTheme) {
                    mIsChangeTheme = false;
                    getWindow().setWindowAnimations(R.style.WindowAnimationFadeInOut);
                    recreate();
                }
            }
        });
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
    private void initNightModeSwitch() {
        if (this instanceof NewsActivity ) {
            MenuItem menuNightMode = mBaseNavView.getMenu().findItem(R.id.nav_night_mode);
            SwitchCompat dayNightSwitch = (SwitchCompat) MenuItemCompat
                    .getActionView(menuNightMode);
            setCheckedState(dayNightSwitch);
            setCheckedEvent(dayNightSwitch);
        }
    }
    private void setCheckedState(SwitchCompat dayNightSwitch) {
        if (MyUtils.isNightMode()) {
            dayNightSwitch.setChecked(true);
        } else {
            dayNightSwitch.setChecked(false);
        }
    }
    private void setCheckedEvent(SwitchCompat dayNightSwitch) {
        dayNightSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    changeToNight();
                    MyUtils.saveTheme(true);
                } else {
                    changeToDay();
                    MyUtils.saveTheme(false);
                }

                mIsChangeTheme = true;
                mDrawerLayout.closeDrawer(GravityCompat.START);
            }
        });
    }
    public void changeToNight() {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        initNightView();
        mNightView.setBackgroundResource(R.color.night_mask);
    }
    public void changeToDay() {
//        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        mNightView.setBackgroundResource(android.R.color.transparent);
    }
}
