package com.tools.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.tools.R;
import com.tools.activity.OkHttpActivity;
import com.tools.activity.dagger2.DaggerActivity;
import com.tools.activity.rxjava2.Rxjava2Activity;
import com.tools.utils.LogUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * 著名开源库
 */
public class FamousFrameFragment extends Fragment {
    private ListView lv_famous_frame_fragment;
    private View rootView;
    private List<String> listDatas = new ArrayList<>();
    private ArrayAdapter<String> mAdatper;
    private Context mContext;
    private Intent intent;
    private boolean isInit; // 是否可以开始加载数据

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_famous_frame, container, false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            mContext = getActivity();
        }
    }

    /**
     * 初始化数据
     */
    private void initData() {
        listDatas.add("OkHttp的封装与网络请求详解");
        listDatas.add("Dagger2的基础学习");
        listDatas.add("RxJava2的基础学习");
        mAdatper = new ArrayAdapter<String>(mContext, android.R.layout.simple_list_item_1, listDatas);
        lv_famous_frame_fragment.setAdapter(mAdatper);
        lv_famous_frame_fragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                LogUtil.E("position == " + position);
                switch (position) {
                    case 0://OkHttp的封装与网络请求
                        startActivity(new Intent(mContext, OkHttpActivity.class));
                        break;
                    case 1://Dagger2的基础学习
                        startActivity(new Intent(mContext, DaggerActivity.class));
                        break;
                    case 2://rxjava2的基础学习
                        startActivity(new Intent(mContext, Rxjava2Activity.class));
                        break;
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
        lv_famous_frame_fragment = (ListView) rootView.findViewById(R.id.lv_famous_frame_fragment);
    }
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        // 每次切换fragment时调用的方法 前提:跟ViewPager配合使用,或者自己手动调用,此方法不在生命周期里面
        // 相当于Fragment的onResume
        if (isVisibleToUser) {
            isInit = true;
            showData();
        }
    }

    private void showData() {
        if (isInit) {
            isInit = false;// 加载数据完成
            listDatas.clear();
            initData();
        }
    }

    @Override
    public void onResume() {//当前fragment第一次显示 会调用此方法
        super.onResume();
        // 判断当前fragment是否显示
        if (getUserVisibleHint()) {
            isInit = true;
            showData();
        }
    }
}

