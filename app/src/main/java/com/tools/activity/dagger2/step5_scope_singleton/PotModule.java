package com.tools.activity.dagger2.step5_scope_singleton;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Module
public class PotModule {
    @Provides
    @Singleton
    Pot providePot(@RoseFlower Flower flower){
        return new Pot(flower);
    }
}
