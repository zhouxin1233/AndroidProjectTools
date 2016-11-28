package com.tools;

import android.app.Application;

import com.tools.utils.OkHttpUtils;

/**
 * Created by Administrator on 2016/11/27 0027.
 */

public class MyApplication extends Application {
    private OkHttpUtils mOkHttpUtils;
    private static MyApplication app;
    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        initOkHttpUtils();
    }
    public static MyApplication getApp() {
        return app;
    }
    /**
     * 初始化OkHttp
     */
    private void initOkHttpUtils() {
        mOkHttpUtils = OkHttpUtils.getInstance();
    }
    public OkHttpUtils getOkHttpUtils() {
        return this.mOkHttpUtils;
    }
}
