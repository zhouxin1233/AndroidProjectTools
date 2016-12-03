package com.tools.activity.dagger2.step5_scope_singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Module
public class FlowerModule {
    @Provides
    @RoseFlower
    Flower providesRose(){
        return new Rose();
    }
}
