package com.example.mvp_test.presenter;

/**
 * 登陆的Presenter 的接口，实现类为LoginPresenterImpl，完成登陆的验证，以及销毁当前view.
 */

public interface LoginPresenter {
    void validate(String userName,String passWord);
}
