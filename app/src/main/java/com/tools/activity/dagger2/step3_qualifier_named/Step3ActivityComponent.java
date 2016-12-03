package com.tools.activity.dagger2.step3_qualifier_named;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Component(modules={FlowerModule.class,PotModule.class})
public interface Step3ActivityComponent {
    void inject(Step3Activity activity);
}
