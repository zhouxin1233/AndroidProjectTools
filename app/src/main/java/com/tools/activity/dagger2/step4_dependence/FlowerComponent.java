package com.tools.activity.dagger2.step4_dependence;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Component(modules = FlowerModule.class)
public interface FlowerComponent {
    @RoseFlower
    Flower getRoseFlower();
    @LilyFlower
    Flower getLilyFlower();
}
