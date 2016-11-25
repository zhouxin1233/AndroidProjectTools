package com.tools;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.LinearLayout;

import com.tools.base.BaseFragmentActivity;
import com.tools.fragment.CommonlyUsedFunctionFragment;
import com.tools.fragment.FamousFrameFragment;
import com.tools.fragment.MainFragment;
import com.tools.fragment.PackageControlFragment;
import com.tools.utils.ActivityManagerUtils;

/**
 * 主页面==>功能分类
 */
public class MainActivity extends BaseFragmentActivity implements View.OnClickListener{
    //首页fragment 知识点
    public MainFragment mainFragment = new MainFragment();
    //常用功能
    public CommonlyUsedFunctionFragment mCommonlyUsedFunctionFragment = new CommonlyUsedFunctionFragment();
    //封装控件
    public PackageControlFragment mPackageControlFragment = new PackageControlFragment();
    //著名开源库
    public FamousFrameFragment mFamousFrameFragment = new FamousFrameFragment();

    public LinearLayout[] mTabs;//底栏四个按钮的集合Tab
    private int currentTabIndex = 0; //当前fragment的index
    private boolean isState = true;//设置双击退出的变量
    private int pressFlag = 0;//判断点击的位置
    private int index;//标识点击的位置，防止多次点击
    //暂存Fragment
    private Fragment mFragment = new Fragment();
    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        ActivityManagerUtils.getInstance().addActivity(MainActivity.this);
        initViews();
    }

    /**
     * 初始化控件布局
     */
    private void initViews() {
        mTabs = new LinearLayout[4];
        mTabs[0] = (LinearLayout) findViewById(R.id.main_knowledge_point);// 首页 知识点详解
        mTabs[1] = (LinearLayout) findViewById(R.id.main_package_control);// 封装控件
        mTabs[2] = (LinearLayout) findViewById(R.id.main_commonly_used_function);// 常用功能
        mTabs[3] = (LinearLayout) findViewById(R.id.mian_famous_frame);// 著名开源库

        pressFlag = 0;
        index = 0;

        mTabs[0].setOnClickListener(this);
        mTabs[1].setOnClickListener(this);
        mTabs[2].setOnClickListener(this);
        mTabs[3].setOnClickListener(this);
        mTabs[0].setSelected(true);
        switchContent(mainFragment);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.main_knowledge_point:
                //首页fragment
                pressFlag = 0;
                index = 0;
                switchContent(mainFragment);
                break;
            case R.id.main_package_control:
                //封装控件fragment
                pressFlag = 1;
                index = 1;
                switchContent(mPackageControlFragment);
                break;
            case R.id.main_commonly_used_function:
                //常用功能fragment
                pressFlag = 2;
                index = 2;
                switchContent(mCommonlyUsedFunctionFragment);
                break;
            case R.id.mian_famous_frame:
                //著名开源库
                pressFlag = 3;
                index = 3;
                switchContent(mFamousFrameFragment);
                break;
        }
        mTabs[currentTabIndex].setSelected(false);
        mTabs[index].setSelected(true);
        currentTabIndex = index;
    }
    /**
     * 选择Fragment存放
     */
    public void switchContent(Fragment to) {
        if (to != null && mFragment != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                // 隐藏当前的fragment，add下一个到Activity中
                transaction.hide(mFragment).add(R.id.content, to).commitAllowingStateLoss();
            } else {
                // 隐藏当前的fragment，显示下一个
                transaction.hide(mFragment).show(to).commitAllowingStateLoss();
            }
            mFragment = to;
        }
    }
}
