package com.tools.activity.dagger2.step5_scope_singleton;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Singleton
@Component(modules = PotModule.class,dependencies = FlowerComponent.class)
public interface PotComponent  {
    Pot getPot();
}
