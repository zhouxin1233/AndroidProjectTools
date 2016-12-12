package com.news.mvp.model.impl;

import com.news.mvp.callback.RequestCallBack;
import com.news.mvp.entity.NewsSummary;
import com.news.mvp.model.NewsModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2016/12/12 0012.
 */

public class NewsListModelImpl implements NewsModel<List<NewsSummary>> {
    @Inject
    public NewsListModelImpl(){

    }
    @Override
    public Disposable lodeNewsChannels(RequestCallBack<List<NewsSummary>> callBack) {
        return null;
    }
}
