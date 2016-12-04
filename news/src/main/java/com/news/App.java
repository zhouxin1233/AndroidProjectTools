package com.news;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;
import android.os.StrictMode;

import com.blankj.utilcode.utils.LogUtils;
import com.news.dagger.component.ApplicationComponent;
import com.news.dagger.component.DaggerApplicationComponent;
import com.news.dagger.module.ApplicationModule;
import com.news.utils.WriteLogUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class App extends Application implements Thread.UncaughtExceptionHandler{
    RefWatcher mRefWatcher;
    ApplicationComponent mApplicationComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        initLeakCanary(); //LeakCanary的初始化
        LogUtils.init(this,BuildConfig.DEBUG,true,'v',"MyTag");//Log的初始化
        initActivityLifecycleLogs();//打印出Activity的生命周期
        initStrictMode();//严格模式
        Thread.setDefaultUncaughtExceptionHandler(this);//捕获全局的异常
        initApplicationComponent();
    }



    private void initLeakCanary() {
        if (BuildConfig.DEBUG){
            mRefWatcher= LeakCanary.install(this);
        }else{
            mRefWatcher=RefWatcher.DISABLED;
        }
    }

    private void initActivityLifecycleLogs() {
       registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
           @Override
           public void onActivityCreated(Activity activity, Bundle bundle) {
                LogUtils.i("ActivityLife",activity+" : onActivityCreated");
           }

           @Override
           public void onActivityStarted(Activity activity) {
               LogUtils.i("ActivityLife",activity+" : onActivityStarted");
           }

           @Override
           public void onActivityResumed(Activity activity) {
               LogUtils.i("ActivityLife",activity+" : onActivityResumed");
           }

           @Override
           public void onActivityPaused(Activity activity) {
               LogUtils.i("ActivityLife",activity+" : onActivityPaused");
           }

           @Override
           public void onActivityStopped(Activity activity) {
               LogUtils.i("ActivityLife",activity+" : onActivityStopped");
           }

           @Override
           public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {
               LogUtils.i("ActivityLife",activity+" : onActivitySaveInstanceState");
           }

           @Override
           public void onActivityDestroyed(Activity activity) {
               LogUtils.i("ActivityLife",activity+" : onActivityDestroyed");
           }
       });
    }

    private void initStrictMode(){
        if (BuildConfig.DEBUG){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
//                    .penaltyDialog() // 弹出违规提示对话框
                    .penaltyLog() //在logcat中打印违规异常信息
                    .build()
            );
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build()
            );
        }
    }

    @Override
    public void uncaughtException(Thread thread, Throwable throwable) {
        throwable.printStackTrace();
        String info = null;
        ByteArrayOutputStream baos = null;
        PrintStream printStream = null;
        try {
            baos = new ByteArrayOutputStream();
            printStream = new PrintStream(baos);
            throwable.printStackTrace(printStream);
            info = new String(baos.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (printStream != null) {
                    printStream.close();
                }
                if (baos != null) {
                    baos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        WriteLogUtils.writeLog("Error","error",info);  // 捕获闪退的异常,并保存到本地
    }

    private void initApplicationComponent() {
        mApplicationComponent= DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }
}
