package com.news.utils;

import com.blankj.utilcode.utils.NetworkUtils;
import com.blankj.utilcode.utils.ToastUtils;
import com.news.App;
import com.news.R;

/**
 * Created by Administrator on 2016/12/5 0005.
 */

public class NetUtils {
    public static void isNetworkErrThenShowMsg(){
        if (!NetworkUtils.isAvailableByPing(App.getAppContext())){
            ToastUtils.showLongToast(App.getAppContext(),App.getAppContext().getString(R.string.internet_error));
        }
    }
}
