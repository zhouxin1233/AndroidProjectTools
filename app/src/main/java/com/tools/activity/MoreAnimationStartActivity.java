package com.tools.activity;/*

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tools.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;


/**
 * Activity的多种跳转样式动画效果
 */

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tools.R;
import com.tools.base.BaseActivity;
import com.tools.constant.Constants;

import java.util.ArrayList;
import java.util.List;

public class MoreAnimationStartActivity extends BaseActivity {
    private ListView lv;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_more_animation_start);
        lv = (ListView) findViewById(R.id.lv);
    }

    @Override
    protected void initData() {
        String[] ls = getResources().getStringArray(R.array.anim_type);
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < ls.length; i++) {
            list.add(ls[i]);
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                startActivity(ProgressWebViewActivity.class);
                switch (position) {
                    /**
                     * 淡入淡出效果
                     */
                    case 0:
                        overridePendingTransition(R.anim.fade, R.anim.hold);
                        break;
                    /**
                     * 放大淡出效果
                     */
                    case 1:
                        overridePendingTransition(R.anim.my_scale_action,
                                R.anim.my_alpha_action);
                        break;
                    /**
                     * 转动淡出效果1
                     */
                    case 2:
                        overridePendingTransition(R.anim.scale_rotate,
                                R.anim.my_alpha_action);
                        break;
                    /**
                     * 转动淡出效果2
                     */
                    case 3:
                        overridePendingTransition(R.anim.scale_translate_rotate,
                                R.anim.my_alpha_action);
                        break;
                    /**
                     * 左上角展开淡出效果
                     */
                    case 4:
                        overridePendingTransition(R.anim.scale_translate,
                                R.anim.my_alpha_action);
                        break;
                    /**
                     * 压缩变小淡出效果
                     */
                    case 5:
                        overridePendingTransition(R.anim.hyperspace_in,
                                R.anim.hyperspace_out);
                        break;
                    /**
                     * 右往左推出效果
                     */
                    case 6:
                        overridePendingTransition(R.anim.push_left_in,
                                R.anim.push_left_out);
                        break;
                    /**
                     * 下往上推出效果
                     */
                    case 7:
                        overridePendingTransition(R.anim.push_up_in,
                                R.anim.push_up_out);
                        break;
                    /**
                     * 左右交错效果
                     */
                    case 8:
                        overridePendingTransition(R.anim.slide_left,
                                R.anim.slide_right);
                        break;
                    /**
                     * 放大淡出效果
                     */
                    case 9:
                        overridePendingTransition(R.anim.wave_scale,
                                R.anim.my_alpha_action);
                        break;
                    /**
                     * 缩小效果
                     */
                    case 10:
                        overridePendingTransition(R.anim.zoom_enter,
                                R.anim.zoom_exit);
                        break;
                    /**
                     * 上下交错效果
                     */
                    case 11:
                        overridePendingTransition(R.anim.slide_up_in,
                                R.anim.slide_down_out);
                        break;

                    default:
                        break;
                }
            }
        });
    }

    @Override
    protected void setTitle() {
        super.setTitle();
        tvTitle.setText(Constants.BASE_ACTIVITY_TITLE);
    }
}
