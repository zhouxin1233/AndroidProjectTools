package com.news.dagger.module;

import android.app.Activity;
import android.content.Context;

import com.news.dagger.qualifier.ContextLife;
import com.news.dagger.scope.PreActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/4 0004.
 */
@Module
public class ActivityModule {
    private Activity mActivity;

    public ActivityModule(Activity activity) {
        mActivity = activity;
    }
    @Provides
    @PreActivity
    @ContextLife("Activity")
    Context provideActivitContext(){
        return mActivity;
    }

    @Provides
    @PreActivity
    @ContextLife("Activity")
    Activity provideActivity(){
        return mActivity;
    }
}
