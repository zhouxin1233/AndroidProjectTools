package com.example.mvp_test.model;

import com.example.mvp_test.callback.OnLoginFinishCallBack;

/**
 * 模拟登陆的操作的接口，实现类为LoginModelImpl.相当于MVP模式中的Model层
 */

public interface LoginModel {
    void login(String userName, String passWord, OnLoginFinishCallBack loginFinishCallBack);
}
