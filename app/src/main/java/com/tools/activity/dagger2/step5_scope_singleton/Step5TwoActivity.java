package com.tools.activity.dagger2.step5_scope_singleton;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tools.MyApplication;
import com.tools.R;
import com.tools.utils.LogUtil;

import javax.inject.Inject;

import dagger.Lazy;

public class Step5TwoActivity extends AppCompatActivity {
//    @Inject
//    Pot mPot3;

    @Inject
    Lazy<Pot> mPotLazy;//这种写法就是Lazy模式,Inject的时候并没有初始化,只有用到的时候才初始化
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
//        DaggerStep5TwoActivityComponent.builder().potComponent(
//                DaggerPotComponent.builder().flowerComponent(
//                        DaggerFlowerComponent.create()
//                ).build()
//        ).build().inject(Step5TwoActivity.this);
        DaggerStep5TwoActivityComponent.builder().potComponent(MyApplication.getApp().getPotComponent()).build().inject(this);
//        LogUtil.D(" mPot3 : "+mPot3.hashCode());
        LogUtil.D(" mPot3 : "+mPotLazy.get().hashCode());
    }
}
