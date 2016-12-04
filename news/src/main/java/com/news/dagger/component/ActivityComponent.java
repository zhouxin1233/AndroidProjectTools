package com.news.dagger.component;

import android.content.Context;

import com.news.dagger.module.ActivityModule;
import com.news.dagger.qualifier.ContextLife;
import com.news.dagger.scope.PreActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/4 0004.
 */
@Component(modules = ActivityModule.class,dependencies = ApplicationComponent.class)
@PreActivity
public interface ActivityComponent {
    @ContextLife("Activity")
    Context getActivityContext();
    @ContextLife
    Context getApplicationContext();

//    Activity getActivity();
}
