package com.tools.utils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 弹出Toast 封装类
 */
public class ToastUtils {

    private static Toast mToast;
    private static Handler mhandler = new Handler();
    private static Runnable r = new Runnable() {
        public void run() {
            mToast.cancel();
        }
    };

    public static void show(Context context, String text) {
        mhandler.removeCallbacks(r);
        if (null != mToast) {
            mToast.setText(text);
        } else {
            mToast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        }
        mhandler.postDelayed(r, 3000);
        mToast.show();
    }

    /**
     * Toast中间显示
     **/
    public static void middleShow(Context content, String message) {
        Toast toast = Toast.makeText(content, message, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        /* LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(getApplicationContext());
		imageCodeProject.setImageResource(R.drawable.icon);
		toastView.addView(imageCodeProject, 0);*/
        toast.show();
    }
}
