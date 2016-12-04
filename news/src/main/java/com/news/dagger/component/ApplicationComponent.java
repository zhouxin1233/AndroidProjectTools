package com.news.dagger.component;

import android.content.Context;

import com.news.dagger.module.ApplicationModule;
import com.news.dagger.qualifier.ContextLife;
import com.news.dagger.scope.PreApp;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/4 0004.
 */
@Component(modules = ApplicationModule.class)
@PreApp
public interface ApplicationComponent {
    @ContextLife
    Context getApplicationContext();
}
