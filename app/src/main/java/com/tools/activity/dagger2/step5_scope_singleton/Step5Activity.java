package com.tools.activity.dagger2.step5_scope_singleton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.tools.MyApplication;
import com.tools.R;
import com.tools.utils.LogUtil;
import com.tools.utils.ToastUtils;

import javax.inject.Inject;

public class Step5Activity extends AppCompatActivity {
    @Inject
    Pot mPot;
    @Inject
    Pot mPot2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
//        DaggerStep5ActivityComponent.build().potComponent(
//                DaggerPotComponent.build().flowerComponent(
//                        DaggerFlowerComponent.create()).build())
//                .build().inject(this);

        //如果DaggerPotComponent 在application中初始化一次  之后引用application中的
        //DaggerPotComponent的话,讲实现全局单利,,,,,以上写法只是实现局部单利

        DaggerStep5ActivityComponent.builder().potComponent(MyApplication.getApp().getPotComponent()).build().inject(this);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(Step5Activity.this,Step5TwoActivity.class));
            }
        },2000);

        ToastUtils.middleShow(getApplicationContext(),mPot.show());
        LogUtil.D("mPot : "+mPot.hashCode()+"\n, mPot2 : "+mPot2.hashCode());




    }
}
/**
 *  @Scope是用来管理依赖的生命周期的。它和@Qualifier一样是用来自定义注解的,
 *  而@Singleton则是@Scope的默认实现。
 *  @Scope的作用了，它的作用只是保证依赖在@Component中是唯一的，可以理解为“局部单例”。
 * @Scope是需要成对存在的，在Module的Provide方法中使用了@Scope，那么对应的Component中也必须使用@Scope注解，
 * 当两边的@Scope名字一样时（比如同为@Singleton）, 那么该Provide方法提供的依赖将会在Component中保持“局部单例”
 */
