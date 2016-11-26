package com.example.mvp_test.model.impl;

import android.os.Handler;
import android.text.TextUtils;

import com.example.mvp_test.callback.OnLoginFinishCallBack;
import com.example.mvp_test.model.LoginModel;

/**
 * Model层 : 延时模拟登陆（2s），如果名字或者密码为空则登陆失败，否则登陆成功
 */

public class LoginModelImpl implements LoginModel {
    @Override
    public void login(final String userName, final String passWord, final OnLoginFinishCallBack loginFinishCallBack) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (TextUtils.isEmpty(userName)){
                    loginFinishCallBack.userNameError();
                    return;
                }
                if (TextUtils.isEmpty(passWord)){
                    loginFinishCallBack.passWordError();
                    return;
                }
                loginFinishCallBack.success();
            }
        },2000);
    }
}
