package com.tools.base;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.tools.R;

/**
 * Created by Administrator on 2016/11/22.
 */

public abstract class BaseActivity extends Activity {
    protected Context mContext;
//    protected OkHttpUtils okHttpUtils = MyApplication.getApp().getOkHttpUtils();
    protected TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        mContext = this;
        initView();
        initData();
        setOnclickListener();
        setTitle();

    }

    /**
     * 设置标题
     */
    protected void setTitle() {
        tvTitle = (TextView) findViewById(R.id.tv_title);

    }

    /**
     * 设置点击事件
     */
    protected void setOnclickListener() {
    }

    /**
     * 绑定UI布局，以及初始化UI控件
     */
    protected abstract void initView();


    /**
     * 初始化数据
     */
    protected abstract void initData();

    /**
     * onResume与onPause()封装提取原因友盟统计
     */
    protected void onResume() { // Umeng 对处理事件的统计
        super.onResume();
//        MobclickAgent.onResume(this);
    }

    protected void onPause() {
        super.onPause();
//        MobclickAgent.onPause(this);
    }

    //    跳转类
    protected void startActivity(Class<?> activity) {
        Intent intent = new Intent(getBaseContext(), activity);
        startActivity(intent);
    }
}
