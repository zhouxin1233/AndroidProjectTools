package com.example.mvp_test.presenter.impl;

import com.example.mvp_test.callback.OnLoginFinishCallBack;
import com.example.mvp_test.model.LoginModel;
import com.example.mvp_test.model.impl.LoginModelImpl;
import com.example.mvp_test.presenter.LoginPresenter;
import com.example.mvp_test.view.LoginView;

/**
 * 1 完成presenter的实现。这里面主要是Model层和View层的交互和操作。
 * 2  OnLoginFinishCallBack，其在Presenter层实现，给Model层回调，更改View层的状态，
 * 确保 Model层不直接操作View层 ,回调郭磊的结果 在当前Presenter层处理,告诉View层该怎么更改界面
 */

public class LoginPresenterImpl implements LoginPresenter,OnLoginFinishCallBack {
    private LoginView mLoginView;
    private LoginModel mLoginModel;
    public LoginPresenterImpl(LoginView loginView) {
        mLoginView = loginView;
        mLoginModel=new LoginModelImpl();
    }


    @Override
    public void validate(String userName, String passWord) {
        mLoginView.showProgress();
        mLoginModel.login(userName,passWord,this);
    }

    @Override
    public void userNameError() {
        if (mLoginView!=null){
            mLoginView.hideProgress();
            mLoginView.hintUserNameError();
        }
    }

    @Override
    public void passWordError() {
        if (mLoginView!=null){
            mLoginView.hideProgress();
            mLoginView.hintPassWordError();
        }
    }

    @Override
    public void success() {
        if (mLoginView!=null){
            mLoginView.hideProgress();
            mLoginView.navigateToHome();
        }
    }
}
