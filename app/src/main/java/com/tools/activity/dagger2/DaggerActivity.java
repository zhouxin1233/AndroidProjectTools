package com.tools.activity.dagger2;

import com.tools.R;
import com.tools.base.BaseActivity;

public class DaggerActivity extends BaseActivity {
    /**
     * Dagger2配置
     * 在项目的gradle中编译: classpath 'com.neenbedankt.gradle.plugins:android-apt:1.8'
     * 在module的gradle中:
     * apply plugin: 'com.neenbedankt.android-apt'
     *
     *   apt 'com.google.dagger:dagger-compiler:2.2'
     *   provided 'org.glassfish:javax.annotation:10.0-b28'
     *   compile 'com.google.dagger:dagger:2.2'
     */

    @Override
    protected void initView() {
        setContentView(R.layout.activity_dagger);
    }

    @Override
    protected void initData() {

    }
}
