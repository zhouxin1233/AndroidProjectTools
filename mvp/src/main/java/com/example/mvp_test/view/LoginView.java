package com.example.mvp_test.view;

/**
 * 登陆View的接口，实现类也就是登陆的activity
 */

public interface LoginView {
    void showProgress();
    void hideProgress();
    void hintUserNameError();
    void hintPassWordError();
    void navigateToHome();
}
