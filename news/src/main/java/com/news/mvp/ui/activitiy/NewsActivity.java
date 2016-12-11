package com.news.mvp.ui.activitiy;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.news.R;
import com.news.annotations.BindValues;
import com.news.db.NewsChannel;
import com.news.event.ChannelChangeEvent;
import com.news.event.ScrollToTopEvent;
import com.news.mvp.presenter.impl.NewPresenterImpl;
import com.news.mvp.ui.activitiy.base.BaseActivity;
import com.news.mvp.view.NewsView;
import com.news.utils.RxBus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;

@BindValues(mIsHasNavigationView = true)
public class NewsActivity extends BaseActivity implements NewsView{
    @Inject
    NewPresenterImpl mNewsPresenter;
    Toolbar mToolbar;
    TabLayout mTabs;
    ViewPager mViewPager;
    NavigationView mNavView;
    FloatingActionButton mFab;
    DrawerLayout mDrawerLayout;

    private List<Fragment> mNewsFragmentList = new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDisposable= RxBus.getInstance().toFlowable(ChannelChangeEvent.class)
                .subscribe(new Consumer<ChannelChangeEvent>() {
                    @Override
                    public void accept(ChannelChangeEvent channelChangeEvent) throws Exception {
                        mNewsPresenter.onChannelDbChanged();
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
        mToolbar=(Toolbar)findViewById(R.id.toolbar);
        mTabs=(TabLayout)findViewById(R.id.tabs);
        mViewPager=(ViewPager)findViewById(R.id.view_pager);
        mNavView=(NavigationView)findViewById(R.id.nav_news);
        mFab=(FloatingActionButton)findViewById(R.id.fab);
        mDrawerLayout=(DrawerLayout)findViewById(R.id.drawer_layout);
        mBaseNavView = mNavView;

        mPresenter = mNewsPresenter;
        mPresenter.attachView(this);
    }

    @Override
    public void initViewPager(List<NewsChannel> newsChannels) {
        final List<String> channelNames = new ArrayList<>();
        if (newsChannels != null) {
//            setNewsList(newsChannels, channelNames);
//            setViewPager(channelNames);
        }
    }
//    private void setNewsList(List<NewsChannel> newsChannels, List<String> channelNames) {
//        mNewsFragmentList.clear();
//        for (NewsChannel newsChannel : newsChannels) {
//            NewsListFragment newsListFragment = createListFragments(newsChannel);
//            mNewsFragmentList.add(newsListFragment);
//            channelNames.add(newsChannel.getNewsChannelName());
//        }
//    }
    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg(String message) {

    }
    public void  click(View v){
        switch (v.getId()){
            case R.id.fab:
                RxBus.getInstance().post(new ScrollToTopEvent());
                break;
            case R.id.add_channel_iv:
//                Intent intent = new Intent(this, NewsChannelActivity.class);
//                startActivity(intent);
                break;
        }
    }

}
