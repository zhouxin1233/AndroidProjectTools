package com.news.dagger.component;

import android.content.Context;

import com.news.dagger.module.ActivityModule;
import com.news.dagger.qualifier.ContextLife;
import com.news.dagger.scope.PreActivity;
import com.news.mvp.ui.activitiy.NewsActivity;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/4 0004.
 */
@PreActivity
@Component(modules = ActivityModule.class,dependencies = ApplicationComponent.class)
public interface ActivityComponent {
    @ContextLife("Activity")
    Context getActivityContext();
    @ContextLife
    Context getApplicationContext();

//    Activity getActivity();
    void inject(NewsActivity newsActivity);
}
