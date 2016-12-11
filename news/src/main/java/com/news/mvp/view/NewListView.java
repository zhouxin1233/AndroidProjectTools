package com.news.mvp.view;

import com.news.common.LoadViewType;
import com.news.mvp.entity.NewsSummary;
import com.news.mvp.view.base.BaseView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/11 0011.
 */

public interface NewListView extends BaseView{
    void setNewsList(List<NewsSummary> newsSummary, @LoadViewType.checker int loadType);

}
