package com.tools.activity.dagger2.step3_qualifier_named;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Module
public class FlowerModule {
    //    @Provides
//    @Named("Rose")
//    Flower providesRose(){
//        return new Rose();
//    }
//    @Provides
//    @Named("Lily")
//    Flower providesLily(){
//        return new LiLy();
//    }
    @Provides
    @RoseFlower
    Flower providesRose() {
        return new Rose();
    }

    @Provides
    @LiLyFlower
    Flower providesLily() {
        return new LiLy();
    }

}
