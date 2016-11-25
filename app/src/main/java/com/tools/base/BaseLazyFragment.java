package com.tools.base;

import android.support.v4.app.Fragment;

/**
 * 这里是实现懒加载的fragment基类
 */

public abstract class BaseLazyFragment extends Fragment {
    protected boolean isVisible;
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
