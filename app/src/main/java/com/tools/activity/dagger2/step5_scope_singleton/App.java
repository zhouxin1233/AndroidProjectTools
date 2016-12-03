package com.tools.activity.dagger2.step5_scope_singleton;

import android.app.Application;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class App extends Application {
    PotComponent mPotComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        mPotComponent=DaggerPotComponent.builder().flowerComponent(
                DaggerFlowerComponent.create()
        ).build();
    }
    public PotComponent getPotComponent(){
        return mPotComponent;
    }
}
