package com.tools.activity.dagger2.step2_module_provides;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tools.R;
import com.tools.utils.ToastUtils;

import javax.inject.Inject;

public class Step2Activity extends AppCompatActivity {
    @Inject
    Pot mPot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
        DaggerStep2ActivityComponent.create().inject(this);
        ToastUtils.show(getBaseContext(),mPot.show());
    }
}
/*
@Module需要和@Provide是需要一起使用的时候才具有作用的，并且@Component也需要指定了该Module的时候。

@Module是告诉Component，可以从这里获取依赖对象。Component就会去找被@Provide标注的方法，相当于构造器的@Inject，可以提供依赖。

还有一点要说的是，@Component可以指定多个@Module的，如果需要提供多个依赖的话。

并且Component也可以依赖其它Component存在。*/
