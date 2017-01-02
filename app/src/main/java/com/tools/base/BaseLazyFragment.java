package com.tools.base;

import android.support.v4.app.Fragment;

/**
 * 这里是实现懒加载的fragment基类
 */

public abstract class BaseLazyFragment extends Fragment {
    protected boolean isVisible;
    //setUserVisibleHint()视图是否已经对用户可见，系统的方法 会在attach方法之前执行
    // 在上个页面预加载进入后,再进入的时候就只会走setUserVisibleHint 方法 ,并返回true
    // 所以需要用个boolean值判断 是第几次调用setUserVisibleHint 预加载的时候调用一次,真正可见的时候还会调用一次
    //通常做法 在setUserVisibleHint 和 onCreateView 均要调用加载数据的方法
    // ==>只不过这个加载的数据方法得先判断有没有初始化过(确保走完onCreateView等预加载方法),然后再根据getUserVisibleHint判断是不是可见状态
//    @Override
//    public void setUserVisibleHint(boolean isVisibleToUser) {
//        super.setUserVisibleHint(isVisibleToUser);
//        if(getUserVisibleHint()) {
//            isVisible = true;
//            lazyLoad();
//        } else {
//            isVisible = false;
//            onInvisible();
//        }
//    }

    @Override
    public void onHiddenChanged(boolean hidden) {//当前fragment被隐藏后才会调用,没啥卵用
        super.onHiddenChanged(hidden);
        if(hidden) {
            isVisible = true;
            lazyLoad();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected abstract void lazyLoad();
    protected void onInvisible(){}
}
