package com.tools.activity.dagger2.step4_dependence;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tools.R;
import com.tools.utils.ToastUtils;

import javax.inject.Inject;

public class Step4Activity extends AppCompatActivity {
    @Inject
    Pot mPot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        DaggerStep4ActivityComponent.builder()
                .potComponent(DaggerPotComponent.builder().flowerComponent(
                        DaggerFlowerComponent.create()
                ).build()).build().inject(this);
        ToastUtils.show(getBaseContext(),mPot.show());
    }
}
