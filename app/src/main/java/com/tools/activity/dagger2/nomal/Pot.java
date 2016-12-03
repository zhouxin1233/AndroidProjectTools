package com.tools.activity.dagger2.nomal;

/**
 * Created by Administrator on 2016/12/1 0001.
 */

public class Pot {
    private Rose mRose;

    public Pot(Rose rose) {
        mRose = rose;
    }
    public String show(){
        return mRose.whisper();
    }
}
