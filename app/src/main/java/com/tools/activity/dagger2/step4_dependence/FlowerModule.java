package com.tools.activity.dagger2.step4_dependence;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Module
public class FlowerModule {
    @Provides
    @RoseFlower
    Flower provideRose(){
        return new Rose();
    }
    @Provides
    @LilyFlower
    Flower provideLily(){
        return new Lily();
    }
}
