package com.news.mvp.view;

import com.news.db.NewsChannel;
import com.news.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/7 0007.
 */

public interface NewsView extends BaseView{
    void initViewPager(List<NewsChannel> newsChannels);
}
