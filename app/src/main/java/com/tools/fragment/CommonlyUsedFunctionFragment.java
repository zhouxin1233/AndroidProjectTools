package com.tools.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tools.R;
import com.tools.activity.LoadingActivity;
import com.tools.activity.RoundZoomImageViewActivity;
import com.tools.activity.SuspensionActivity;
import com.tools.base.BaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 常用功能
 */
public class CommonlyUsedFunctionFragment extends BaseLazyFragment {
    private ListView lv_commonly_used_function_fragment;
    private View rootView;
    private List<String> listDatas = new ArrayList<>();
    private ArrayAdapter<String> mAdatper;
    private Context mContext;
    private Intent intent;
    // 标志位，标志已经初始化完成。.
    private boolean isPrepared;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_commonly_used_function, container, false);
        initView(rootView);
        isPrepared = true;
        lazyLoad();
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
     * 初始化UI控件
     */
    private void initView(View rootView) {
        lv_commonly_used_function_fragment = (ListView) rootView.findViewById(R.id.lv_commonly_used_function_fragment);
    }

    @Override
    protected void lazyLoad() {
        if(!isPrepared || !isVisible) {
            return;
        }
        listDatas.add("三种方式圆形头像图片的实现");
        listDatas.add("loading等待提示框多种实现方式");
        listDatas.add("动态改变悬浮的顶部栏");

        mAdatper = new ArrayAdapter<>(mContext, android.R.layout.simple_list_item_1, listDatas);
        lv_commonly_used_function_fragment.setAdapter(mAdatper);

        lv_commonly_used_function_fragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    /**
                     * 三种方式圆形头像图片的实现
                     */
                    case 0:
                        intent = new Intent(mContext, RoundZoomImageViewActivity.class);
                        break;
                    case 1:
                        intent = new Intent(mContext, LoadingActivity.class);
                        break;
                    case 2:
                        intent=new Intent(mContext,SuspensionActivity.class);
                        break;
//                    /**
//                     * Splash首页添加广告progressBar进度
//                     */
//                    case 1:
//                        intent = new Intent(mContext, SplashAdActivity.class);
//                        break;
//                    /**
//                     * 公告广告通知显示滚动，类似淘宝等头条滚动效果
//                     */
//                    case 2:
//                        intent = new Intent(mContext, MarqueeActivity.class);
//                        break;
//                    /**
//                     * 无限轮播滚动的banner(封装ViewPager)
//                     */
//                    case 3:
//                        intent = new Intent(mContext, AutoViewPagerBannerActivity.class);
//                        break;
//                    /**
//                     * 仿QQ微信提示消息个数两种实现方式
//                     */
//                    case 4:
//                        intent = new Intent(mContext, BadgeViewWaterDropActivity.class);
//                        break;
//                    /**
//                     * 利用Zxing实现二维码的生成与扫描功能
//                     */
//                    case 5:
//                        intent = new Intent(mContext, BarCodeActivity.class);
//                        break;
//                    /**
//                     *验证码的多种实现方法
//                     */
//                    case 6:
//                        intent = new Intent(mContext, CheckCodeActivity.class);
//                        break;
//                    /**
//                     * 仿淘宝,百度外卖,饿了么等头条滚动效果
//                     */
//                    case 7:
//                        intent = new Intent(mContext, UpMarqueeActivity.class);
//
//                        break;
//                    /**
//                     * 进入界面弹出一张大图
//                     */
//                    case 8:
//                        intent = new Intent(mContext, StartDialogImageActivity.class);
//
//                        break;
//                    /**
//                     * loading等待提示框多种实现方式
//                     */
//                    case 9:
//                        intent = new Intent(mContext, LoadingActivity.class);
//
//                        break;
//                    /**
//                     * 各种各样的Tab切换功能效果的实现
//                     */
//                    case 10:
//                        intent = new Intent(mContext, TabActivity.class);
//
//                        break;
//                    /**
//                     * 获取手机验证码，延迟六十秒，防止重复点击
//                     */
//                    case 11:
//                        intent = new Intent(mContext, DebounceClickActivity.class);
//
//                        break;
                }
                if (intent != null) {
                    mContext.startActivity(intent);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        isVisible = true;
        lazyLoad();
    }
}