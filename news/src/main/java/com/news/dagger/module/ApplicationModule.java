package com.news.dagger.module;

import android.content.Context;

import com.news.App;
import com.news.dagger.qualifier.ContextLife;
import com.news.dagger.scope.PreApp;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/4 0004.
 */
@Module
public class ApplicationModule {
    private App mApp;

    public ApplicationModule(App app) {
        mApp = app;
    }

    @Provides
    @PreApp
    @ContextLife
    Context provideAppContext(){
        return mApp.getApplicationContext();
    }
}
