package com.tools.activity.dagger2.step4_dependence;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Module
public class PotModule {
    @Provides
    Pot providePot(@RoseFlower Flower flower){
        return new Pot(flower);
    }
}
