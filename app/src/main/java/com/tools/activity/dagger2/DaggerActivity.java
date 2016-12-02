package com.tools.activity.dagger2;

import android.view.View;

import com.tools.R;
import com.tools.base.BaseActivity;

public class DaggerActivity extends BaseActivity {
    /**
     * Dagger2配置
     * 在项目的gradle中编译: classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
     * 在module的gradle中:
     * apply plugin: 'com.neenbedankt.android-apt'
     *
     * apt 'com.google.dagger:dagger-compiler:2.8'
     * compile 'com.google.dagger:dagger:2.8'
     * provided 'javax.annotation:jsr250-api:1.0'
     */

    @Override
    protected void initView() {
        setContentView(R.layout.activity_dagger);
    }

    @Override
    protected void initData() {

    }
    public void click(View v){
        switch (v.getId()){
            case R.id.dagger_bt://正常的写法

                break;
            case R.id.dagger_bt1:

                break;
        }
    }
}
