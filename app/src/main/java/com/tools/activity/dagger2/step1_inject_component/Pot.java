package com.tools.activity.dagger2.step1_inject_component;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/12/2 0002.
 */

public class Pot {
    private Rose mRose;

    @Inject
    public Pot(Rose rose) {
        mRose = rose;
    }
    public String show(){
       return mRose.whisper();
    }
}
