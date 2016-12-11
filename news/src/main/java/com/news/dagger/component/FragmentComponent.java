package com.news.dagger.component;

import android.content.Context;

import com.news.dagger.module.FragmentModule;
import com.news.dagger.qualifier.ContextLife;
import com.news.dagger.scope.PreFragment;
import com.news.mvp.ui.fragment.NewsListFragment;

import dagger.Component;

/**
 * Created by Administrator on 2016/12/11 0011.
 */
@PreFragment
@Component(dependencies = ApplicationComponent.class,modules = FragmentModule.class)
public interface FragmentComponent {
    @ContextLife("Activity")
    Context getActivityContext();
    @ContextLife
    Context getApplicationContext();

//    Activity getActivity();
    void inject(NewsListFragment newsListFragment);
}
