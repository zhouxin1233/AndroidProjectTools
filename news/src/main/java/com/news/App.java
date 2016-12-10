package com.news;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.StrictMode;

import com.blankj.utilcode.utils.LogUtils;
import com.news.common.Constants;
import com.news.dagger.component.ApplicationComponent;
import com.news.dagger.component.DaggerApplicationComponent;
import com.news.dagger.module.ApplicationModule;
import com.news.db.gen.DaoMaster;
import com.news.db.gen.DaoSession;
import com.news.db.gen.NewsChannelDao;
import com.news.utils.WriteLogUtils;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.greenrobot.greendao.query.QueryBuilder;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

/**
 * Created by Administrator on 2016/12/3 0003.
 */

public class App extends Application implements Thread.UncaughtExceptionHandler{
    RefWatcher mRefWatcher;
    ApplicationComponent mApplicationComponent;
    public static Context mAppContext;
    public static App mApp;
    private static DaoSession mDaoSession;
    @Override
    public void onCreate() {
        super.onCreate();
        mApp=this;
        initLeakCanary(); //LeakCanary的初始化
        LogUtils.init(this,BuildConfig.DEBUG,true,'v',"MyTag");//Log的初始化
        initActivityLifecycleLogs();//打印出Activity的生命周期
        initStrictMode();//严格模式
        Thread.setDefaultUncaughtExceptionHandler(this);//捕获全局的异常
        setupDataBase();
        initApplicationComponent();
    }

    private void setupDataBase() {
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, Constants.DB_NAME, null);
        SQLiteDatabase db = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster = new DaoMaster(db);
        mDaoSession = daoMaster.newSession();
        // 在 QueryBuilder 类中内置两个 Flag 用于方便输出执行的 SQL 语句与传递参数的值
        QueryBuilder.LOG_SQL = BuildConfig.DEBUG;
        QueryBuilder.LOG_VALUES = BuildConfig.DEBUG;
    }
    public static NewsChannelDao getNewsChannelDao(){
        return mDaoSession.getNewsChannelDao();
    }

    public static App getApp(){
        return mApp;
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
    public ApplicationComponent getApplicationComponent(){
        return mApplicationComponent;
    }
    public static Context getAppContext() {
        return mAppContext;
    }
    public static RefWatcher getRefWatcher(){
        return App.getApp().mRefWatcher;
    }
}
