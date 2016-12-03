package com.tools.activity.dagger2.step2_module_provides;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/2 0002.
 */
@Module
public class FlowerModule {
    @Provides
    Flower providesFlower(){
        return new Rose();
    }
}
