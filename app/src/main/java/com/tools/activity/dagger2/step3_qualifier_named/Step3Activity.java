package com.tools.activity.dagger2.step3_qualifier_named;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tools.R;
import com.tools.utils.ToastUtils;

import javax.inject.Inject;

public class Step3Activity extends AppCompatActivity {
    @Inject
    Pot mPot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        DaggerStep3ActivityComponent.create().inject(this);
        ToastUtils.show(getApplication(),mPot.show());
    }
}
/**
 * @Qualifier不是直接注解在属性上的，而是用来自定义注解的。
 */
