package com.news.mvp.ui.fragment;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.news.R;
import com.news.common.LoadViewType;
import com.news.mvp.entity.NewsSummary;
import com.news.mvp.ui.fragment.base.BaseFragment;
import com.news.mvp.view.NewListView;

import java.util.List;

/**
 * Created by Administrator on 2016/12/11 0011.
 */

public class NewsListFragment extends BaseFragment implements NewListView{
    RecyclerView mNewsRV;
    ProgressBar mProgressBar;
    SwipeRefreshLayout mSwipeRefreshLayout;
//    @BindView(R.id.empty_view)
//    TextView mEmptyView;
//
//    @Inject
//    NewsListAdapter mNewsListAdapter;
//    @Inject
//    NewsListPresenterImpl mNewsListPresenter;
//    @Inject
//    Activity mActivity;
    @Override
    public void initInjector() {
        mFragmentComponent.inject(this);
    }

    @Override
    public void initViews(View view) {
        mNewsRV=(RecyclerView)view.findViewById(R.id.news_rv);
        mProgressBar=(ProgressBar)view.findViewById(R.id.progress_bar);
        mSwipeRefreshLayout=(SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    public int getLayoutId() {
        return 0;
    }
    public void click(View view){
        switch (view.getId()){
            case R.id.empty_view:

                break;
        }
    }

    @Override
    public void setNewsList(List<NewsSummary> newsSummary, @LoadViewType.checker int loadType) {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showMsg(String message) {

    }
}
