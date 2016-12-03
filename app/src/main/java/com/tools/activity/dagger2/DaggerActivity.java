package com.tools.activity.dagger2;

import android.view.View;

import com.tools.R;
import com.tools.activity.dagger2.nomal.NormalActivity;
import com.tools.activity.dagger2.step1_inject_component.Step1Activity;
import com.tools.activity.dagger2.step2_module_provides.Step2Activity;
import com.tools.activity.dagger2.step3_qualifier_named.Step3Activity;
import com.tools.activity.dagger2.step4_dependence.Step4Activity;
import com.tools.activity.dagger2.step5_scope_singleton.Step5Activity;
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
                startActivity(NormalActivity.class);
                break;
            case R.id.dagger_bt1://Inject 和 Component
                startActivity(Step1Activity.class);
                break;
            case R.id.dagger_bt2://Module 和Provides
                startActivity(Step2Activity.class);
                break;
            case R.id.dagger_bt3://Qualifier 和Named
                startActivity(Step3Activity.class);
                break;
            case R.id.dagger_bt4://dependence
                startActivity(Step4Activity.class);
                break;
            case R.id.dagger_bt5://dependence
                startActivity(Step5Activity.class);
                break;
        }
    }
}
