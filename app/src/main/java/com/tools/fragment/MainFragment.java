package com.tools.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.tools.R;
import com.tools.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/23 0023.
 */

public class MainFragment extends Fragment {
    private ListView lv_main_fragment;
    private View rootView;
    private List<String> listDatas = new ArrayList<>();
    private ArrayAdapter<String> mAdatper;
    private Context mContext;
    private Intent intent;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        LogUtil.E("oncreateView");
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mContext = getActivity();
            LogUtil.E("onActivityCreated");
        }
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        listDatas.add("ListView与adapter的详解");
        listDatas.add("退出app应用的多种实现方式");
        listDatas.add("SwipeRefreshLayout下拉刷新的详解");
        listDatas.add("拨打电话号码的详解代码(比较简单)");
        listDatas.add("系统的沉浸式样式主题实现效果");
        listDatas.add("拍照以及选择相册手机照片");
        listDatas.add("Activity的多种跳转样式动画效果");
        mAdatper = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, listDatas);
        lv_main_fragment.setAdapter(mAdatper);

        lv_main_fragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    /**
                     * ListView与adapter的详解
                     */
                    case 0:
//                        intent = new Intent(mContext, ListViewIntroduceActivity.class);
                        Toast.makeText(getActivity(),"我被点击啦",Toast.LENGTH_LONG).show();
                        break;
                    /**
                     * 退出app应用的多种实现方式
                     */
                    /*case 1:
                        intent = new Intent(mContext, SystemAppFourMethodActivity.class);
                        break;
                    *//**
                     * SwipeRefreshLayout的简单使用教程。下拉刷新控件炫酷效果。
                     *//*
                    case 2:
                        intent = new Intent(mContext, SwipeRefreshListViewActivity.class);
                        break;
                    *//**
                     * 拨打电话号码的详解代码(比较简单)
                     *//*
                    case 3:
                        intent = new Intent(mContext, CallphoneActivity.class);
                        break;
                    *//**
                     *系统的沉浸式样式主题实现效果
                     *//*
                    case 4:
                        intent = new Intent(mContext, SystemStatusThemeActivity.class);
                        break;
                    *//**
                     *拍照以及选择相册手机照片
                     *//*
                    case 5:
                        intent = new Intent(mContext, TakeSelectorPhotoActivity.class);
                        break;
                    *//**
                     *Activity的多种跳转样式动画效果
                     *//*
                    case 6:
                        intent = new Intent(mContext, MoreAnimationStartActivity.class);
                        break;*/
                }
                if (intent != null) {
                    mContext.startActivity(intent);
                }
            }
        });
    }

    /**
     * 初始化UI控件
     */
    private void initView(View rootView) {
        lv_main_fragment = (ListView) rootView.findViewById(R.id.lv_main_fragment);
    }
}
