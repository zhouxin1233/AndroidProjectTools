package com.tools.activity.dagger2.step4_dependence;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Component(dependencies = PotComponent.class)
public interface Step4ActivityComponent {
    void inject(Step4Activity activity);
}
