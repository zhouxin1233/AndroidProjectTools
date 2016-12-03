package com.tools.activity.dagger2.step5_scope_singleton;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class Pot {
    private Flower mFlower;

    public Pot(Flower flower) {
        mFlower = flower;
    }
    public String show(){
        return mFlower.whisper();
    }

}
