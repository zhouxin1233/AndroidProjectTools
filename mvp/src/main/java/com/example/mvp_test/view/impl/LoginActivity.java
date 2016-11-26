package com.example.mvp_test.view.impl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mvp_test.R;
import com.example.mvp_test.presenter.LoginPresenter;
import com.example.mvp_test.presenter.impl.LoginPresenterImpl;
import com.example.mvp_test.view.LoginView;

/**
 * MVP模式中View层对应一个activity，这里是登陆的activity
 * demo的代码流程：
 * Activity做了一些UI初始化的东西,并需要实例化对应Presenter层的引用和实现
 * LoginActivity是LoginView的接口，监听界面动作，实例化对应LoginPresenter的引用和实现
 * 在onClick里点击时间,通过LoginPresenter的引用把它交给LoginPresenter处理。LoginPresenter接收到了登陆的逻辑就知道要登陆了，
 * 然后LoginPresenter显示进度条并且把逻辑交给我们的Model去处理，也就是这里面的LoginModel，
 * （LoginModel的实现类LoginModelImpl），同时会把OnLoginFinishedListener也就是LoginPresenter
 * 自身传递给我们的Model（LoginModel）。LoginModel处理完逻辑之后，结果通过
 * OnLoginFinishedListener回调通知LoginPresenter，LoginPresenter再把结果返回给view层的Activity，
 * 最后activity显示结果
 */
public class LoginActivity extends AppCompatActivity implements LoginView{

    private EditText mUserName;
    private EditText mPassWord;
    private ProgressBar mProgressBar;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initView() {
        mUserName = (EditText)findViewById(R.id.userName);
        mPassWord = (EditText)findViewById(R.id.passWord);
        mProgressBar = (ProgressBar)findViewById(R.id.progressBar);
    }

    private void initData() {
        mLoginPresenter = new LoginPresenterImpl(this);
    }
    public void click(View view){
        switch (view.getId()){
            case R.id.btn_Login://登录操作
                mLoginPresenter.validate(mUserName.getText().toString(),mPassWord.getText().toString());
                break;
        }
    }
    @Override
    public void showProgress() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void hintUserNameError() {
        mUserName.setError(getString(R.string.error_username));
    }

    @Override
    public void hintPassWordError() {
        mPassWord.setError(getString(R.string.error_password));
    }

    @Override
    public void navigateToHome() {
        Toast.makeText(getApplicationContext(),"登录成功",Toast.LENGTH_LONG).show();
    }
}
