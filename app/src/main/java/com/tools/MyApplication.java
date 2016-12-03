package com.tools;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.tools.activity.dagger2.step5_scope_singleton.DaggerFlowerComponent;
import com.tools.activity.dagger2.step5_scope_singleton.DaggerPotComponent;
import com.tools.activity.dagger2.step5_scope_singleton.PotComponent;
import com.tools.utils.OkHttpUtils;

/**
 * Created by Administrator on 2016/11/27 0027.
 */

public class MyApplication extends Application {
    private OkHttpUtils mOkHttpUtils;
    private static MyApplication app;
    PotComponent mPotComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
        initOkHttpUtils();
        //初始化Fresco
        Fresco.initialize(this);
        initDaggerPotComponent();//初始化dagger的potcomponent
    }

    private void initDaggerPotComponent() {
        mPotComponent= DaggerPotComponent.builder().flowerComponent(
                DaggerFlowerComponent.create()
        ).build();
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
    public PotComponent getPotComponent(){
        return mPotComponent;
    }
}
