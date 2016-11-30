package com.tools.activity;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.tools.R;
import com.tools.base.BaseActivity;
import com.tools.callback.OnRefreshWebViewListener;
import com.tools.constant.Urls;
import com.tools.view.WebViewRelativeLayout;

/**
 * 自定义WebView支持下拉刷新的功能
 */
public class WebViewPullRefreshActivity extends BaseActivity {
    /**
     * 若是需要设置一下功能 可在WebViewRelativeLayout中进行设置
     */
    private WebViewRelativeLayout pullWebView;
    private ToggleButton tb;
    @Override
    protected void initView() {
        setContentView(R.layout.activity_pulltorefresh_webview);
        pullWebView = (WebViewRelativeLayout) findViewById(R.id.pullWebView);
        pullWebView.setVisibility(View.VISIBLE);
        tb = (ToggleButton) findViewById(R.id.tb);
    }

    @Override
    protected void initData() {
        pullWebView.setOnRefreshWebViewListener(new OnRefreshWebViewListener() {
            @Override
            public void onRefresh() {
                // 模拟接口调用3秒
                new Handler() {
                    @Override
                    public void handleMessage(Message msg) {
                        //调用接口结束
                        pullWebView.setRefreshSuccess();
//                        pullWebView.setRefreshFail();
                    }
                }.sendEmptyMessageDelayed(0, 3000);
            }
        });

        pullWebView.getWebView().loadUrl(Urls.CSDN_BLOG_DAVID);
        tb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //支持刷新
                    pullWebView.setRefreshEnable(true);
                } else {
                    //不支持刷新
                    pullWebView.setRefreshEnable(false);
                }
            }
        });
    }
}
