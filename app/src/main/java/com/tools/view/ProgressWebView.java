package com.tools.view;

import android.content.Context;
import android.util.AttributeSet;
import android.webkit.WebView;
import android.widget.ProgressBar;

import com.tools.utils.DisplayUtils;


public class ProgressWebView extends WebView {
    private ProgressBar progressbar;

    public ProgressWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        progressbar = new ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal);
        progressbar.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, DisplayUtils.px2dip(context,26), 0, 0));
        addView(progressbar);
        //        setWebViewClient(new WebViewClient(){});
        //使用WebChromeClient 可以操作Javascript dialogs（js脚本对话框）, favicons（添加收藏的标志）, titles（标题）, 和 progress（进度条）.
        //简单的说，如果除了加载HTML的话，只需要用WebViewClient即可，但是在进行兼容互联网上附加javascript的页面的时候和调用javascript对话框的时候，或者功能较为复杂的内嵌操作的时候，建议使用WebChromeClient 。
        setWebChromeClient(new WebChromeClient());
    }

    class WebChromeClient extends android.webkit.WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            if (newProgress == 100) {
                progressbar.setVisibility(GONE);
            } else {
                if (progressbar.getVisibility() == GONE)
                    progressbar.setVisibility(VISIBLE);
                progressbar.setProgress(newProgress);
            }
            super.onProgressChanged(view, newProgress);
        }
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        LayoutParams lp = (LayoutParams) progressbar.getLayoutParams();
        lp.x = l;
        lp.y = t;
        progressbar.setLayoutParams(lp);
        super.onScrollChanged(l, t, oldl, oldt);
    }
}
