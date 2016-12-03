package com.tools.activity.dagger2.step2_module_provides;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

public class Pot {
    private Flower mFlower;

    @Inject
    public Pot(Flower flower) {
        mFlower = flower;
    }
    public String show(){
        return mFlower.whisper();
    }
}
