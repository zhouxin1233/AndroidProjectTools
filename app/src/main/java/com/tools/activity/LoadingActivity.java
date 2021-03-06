package com.tools.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.tools.R;
import com.tools.base.BaseActivity;
import com.tools.constant.Constants;
import com.tools.utils.DialogThridUtils;
import com.tools.utils.WeiboDialogUtils;
import com.tools.view.LoadingDialog;

import dmax.dialog.SpotsDialog;

/**
 * loading等待提示框多种实现方式
 */
public class LoadingActivity extends BaseActivity {
    private Dialog mDialog;
    private Dialog mWeiboDialog;
    private Button btn_show_weibo_loading;
    private Button btn_show_thrid_loading;
    private Button btn_show_two_loading;
    private Button btn_show_four_loading;
    private Button btn_show_five_loading;
    private ProgressDialog mProgressDialog;
    private AlertDialog githubDialog;
    private LoadingDialog mLoadingDialog;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 1:
                    DialogThridUtils.closeDialog(mDialog);
                    WeiboDialogUtils.closeDialog(mWeiboDialog);
                    hiddenLoading();
                    hiddenGithubLoading();
                    LoadingDialog.hiddenLoadingDialog(mLoadingDialog);
                    break;
            }
        }
    };

    @Override
    protected void initView() {
        setContentView(R.layout.activity_loading);
        btn_show_weibo_loading = (Button) findViewById(R.id.btn_show_weibo_loading);
        btn_show_thrid_loading = (Button) findViewById(R.id.btn_show_thrid_loading);
        btn_show_two_loading = (Button) findViewById(R.id.btn_show_two_loading);
        btn_show_four_loading = (Button) findViewById(R.id.btn_show_four_loading);
        btn_show_five_loading = (Button) findViewById(R.id.btn_show_five_loading);
        githubDialog = new SpotsDialog(mContext);//这里用的是三方的开源框架
//        mWeiboDialog.show();


        // 提示正在加载
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(Constants.LOADING_DATA);
        mProgressDialog.setCancelable(false);


    }

    @Override
    protected void initData() {
        btn_show_weibo_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mWeiboDialog = WeiboDialogUtils.createLoadingDialog(mContext, Constants.LOADING_DATA);
                mHandler.sendEmptyMessageDelayed(1, 2000);
            }
        });

        btn_show_thrid_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog = DialogThridUtils.showWaitDialog(mContext, Constants.LOADING_DATA, false, true);
                mHandler.sendEmptyMessageDelayed(1, 2000);
            }
        });

        btn_show_two_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mProgressDialog.show();
                mHandler.sendEmptyMessageDelayed(1, 2000);

            }
        });

        btn_show_four_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                githubDialog.show();
                mHandler.sendEmptyMessageDelayed(1, 2000);
            }
        });

        btn_show_five_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mLoadingDialog = LoadingDialog.show(mContext, Constants.LOADING_DATA, true, null);
                mHandler.sendEmptyMessageDelayed(1, 2000);


            }
        });
    }


    /**
     * 隐藏正在加载
     */
    private void hiddenLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }

    /**
     * 隐藏正在加载
     */
    private void hiddenGithubLoading() {
        if (githubDialog != null && githubDialog.isShowing()) {
            githubDialog.dismiss();
        }
    }
}
