package com.tools.activity.dagger2.step3_qualifier_named;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class Pot {
    private Flower mFlower;

    //    @Inject
//    public Pot(@Named("Lily") Flower flower) {
//        mFlower = flower;
//    }

    public Pot(@RoseFlower Flower flower) {
        mFlower = flower;
    }

    public String show() {
        return mFlower.whisper();
    }
}
