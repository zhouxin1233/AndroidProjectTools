package com.tools.activity;

import android.view.View;
import android.widget.Button;

import com.tools.R;
import com.tools.base.BaseActivity;
import com.tools.utils.ToastUtils;
import com.tools.view.StarBarView;


/**
 * 封装显示星星个数 常用评论数
 */
public class StarBarViewActivity extends BaseActivity {
    private StarBarView sbv_starbar;
    private Button btn_show_num_star;

    @Override
    protected void initView() {
        setContentView(R.layout.activity_start_bar_view);
        sbv_starbar = (StarBarView) findViewById(R.id.sbv_starbar);
        btn_show_num_star = (Button) findViewById(R.id.btn_show_num_star);
    }

    @Override
    protected void initData() {
        //拿到当前星星数量
        sbv_starbar.getStarRating();

    }

    @Override
    protected void setOnclickListener() {
        super.setOnclickListener();
        btn_show_num_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.show(mContext,(int)sbv_starbar.getStarRating()+"");
            }
        });
    }
}
