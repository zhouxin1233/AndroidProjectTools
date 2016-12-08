package com.news.mvp.ui.activitiy;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.news.R;
import com.news.annotations.BindValues;
import com.news.event.ChannelChangeEvent;
import com.news.mvp.ui.activitiy.base.BaseActivity;
import com.news.utils.RxBus;

import io.reactivex.functions.Consumer;

@BindValues(mIsHasNavigationView = true)
public class NewsActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisposable= RxBus.getInstance().toFlowable(ChannelChangeEvent.class)
                .subscribe(new Consumer<ChannelChangeEvent>() {
                    @Override
                    public void accept(ChannelChangeEvent channelChangeEvent) throws Exception {

                    }
                });
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_news;
    }

    @Override
    public void initInjector() {
        mActivityComponent.inject(this);
    }

    @Override
    public void initView() {

    }
}
