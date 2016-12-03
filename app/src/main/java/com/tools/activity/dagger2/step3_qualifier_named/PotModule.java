package com.tools.activity.dagger2.step3_qualifier_named;

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
