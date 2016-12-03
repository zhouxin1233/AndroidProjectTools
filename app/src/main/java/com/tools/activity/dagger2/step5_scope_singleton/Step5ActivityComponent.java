package com.tools.activity.dagger2.step5_scope_singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Component(dependencies = PotComponent.class)
@ScopeAct
public interface Step5ActivityComponent {
    void inject(Step5Activity activity);
}
