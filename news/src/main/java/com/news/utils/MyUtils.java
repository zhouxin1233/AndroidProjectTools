package com.news.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.blankj.utilcode.utils.LogUtils;
import com.blankj.utilcode.utils.SPUtils;
import com.news.App;
import com.news.common.Constants;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2016/12/6 0006.
 */

public class MyUtils {
    public static boolean isNightMode() {
        SPUtils spUtils=new SPUtils(App.getAppContext(),Constants.SHARES_COLOURFUL_NEWS);
        return spUtils.getBoolean(Constants.NIGHT_THEME_MODE,false);
    }
    public static void cancelSubscription(Disposable disposable){
        if (disposable!=null && disposable.isDisposed()){
            disposable.dispose();
        }
    }
    public static boolean getBoolean(String msg,boolean def){
        SPUtils spUtils=new SPUtils(App.getAppContext(),Constants.SHARES_COLOURFUL_NEWS);
        return spUtils.getBoolean(msg,def);
    }
    public static void setBoolean(String msg,boolean def){
        SPUtils spUtils=new SPUtils(App.getAppContext(),Constants.SHARES_COLOURFUL_NEWS);
        spUtils.putBoolean(msg,def);
    }
    public static void saveTheme(boolean isNight){
        SPUtils spUtils=new SPUtils(App.getAppContext(),Constants.SHARES_COLOURFUL_NEWS);
        spUtils.putBoolean(Constants.NIGHT_THEME_MODE, isNight);
    }
    /**
     * 解决InputMethodManager内存泄露现象
     */
    public static void fixInputMethodManagerLeak(Context destContext) {
        if (destContext == null) {
            return;
        }
        InputMethodManager imm = (InputMethodManager) destContext
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm == null) {
            return;
        }
        String[] arr = new String[]{"mCurRootView", "mServedView", "mNextServedView"};
        Field f;
        Object obj_get;
        for (String param : arr) {
            try {
                f = imm.getClass().getDeclaredField(param);
                if (!f.isAccessible()) {
                    f.setAccessible(true);
                }
                obj_get = f.get(imm);
                if (obj_get != null && obj_get instanceof View) {
                    View v_get = (View) obj_get;
                    if (v_get
                            .getContext() == destContext) { // 被InputMethodManager持有引用的context是想要目标销毁的
                        f.set(imm, null); // 置空，破坏掉path to gc节点
                    } else {
                        // 不是想要目标销毁的，即为又进了另一层界面了，不要处理，避免影响原逻辑,也就不用继续for循环了
                        /*if (QLog.isColorLevel()) {
                            QLog.d(ReflecterHelper.class.getSimpleName(), QLog.CLR, "fixInputMethodManagerLeak break, context is not suitable, get_context=" + v_get.getContext()+" dest_context=" + destContext);
                        }*/
                        break;
                    }
                }
            } catch (Throwable t) {
                t.printStackTrace();
            }
        }
    }
    /**
     * from yyyy-MM-dd HH:mm:ss to MM-dd HH:mm
     */
    public static String formatDate(String before) {
        String after;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
                    .parse(before);
            after = new SimpleDateFormat("MM-dd HH:mm", Locale.getDefault()).format(date);
        } catch (ParseException e) {
            LogUtils.e("转换新闻日期格式异常：" + e.toString());
            return before;
        }
        return after;
    }
}
