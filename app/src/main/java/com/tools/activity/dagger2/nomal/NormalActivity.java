package com.tools.activity.dagger2.nomal;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.tools.R;
import com.tools.utils.ToastUtils;

public class NormalActivity extends AppCompatActivity {
    private Pot mPot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_normal);
        Rose rose=new Rose();
        mPot=new Pot(rose);
        ToastUtils.middleShow(getApplicationContext(),mPot.show());
    }
}
//http://www.open-open.com/lib/view/open1474442495481.html 教程