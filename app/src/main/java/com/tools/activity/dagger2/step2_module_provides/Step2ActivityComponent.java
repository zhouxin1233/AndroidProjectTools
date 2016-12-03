package com.tools.activity.dagger2.step2_module_provides;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
@Component(modules = FlowerModule.class)
public interface Step2ActivityComponent {
    void inject(Step2Activity activity);
}
