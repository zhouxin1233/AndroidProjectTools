package com.news.dagger.module;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.news.dagger.qualifier.ContextLife;
import com.news.dagger.scope.PreFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Administrator on 2016/12/11 0011.
 */
@Module
public class FragmentModule {
    private Fragment mFragment;

    public FragmentModule(Fragment fragment) {
        mFragment = fragment;
    }
    @Provides
    @PreFragment
    @ContextLife("Activity")
    Context provideActivityContext(){
        return mFragment.getActivity();
    }

    @Provides
    @PreFragment
    Activity provideActivity(){
        return mFragment.getActivity();
    }
    @Provides
    @PreFragment
    Fragment provideFragment(){
        return mFragment;
    }
}
