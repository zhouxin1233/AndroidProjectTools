package com.tools.utils;

import com.tools.view.XListView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * XlistView停止 的 类 以及时间类
 */

public class XListviewAndTimeUtils {
    /**
     * 停止
     * @param mListView
     */
    public static void stopWait(XListView mListView) {
        mListView.stopRefresh();
        mListView.stopLoadMore();
        mListView.setRefreshTime(getTime());
    }

    /**
     * 获取到当前时间
     *
     * @return
     */
    public static String getTime() {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }
}


