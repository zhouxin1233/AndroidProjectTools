package com.tools.activity.listview;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;

import com.tools.R;
import com.tools.utils.ToastUtils;

import java.util.ArrayList;

public class GSListViewActivity extends AppCompatActivity {
    private GSListView mListView;
    private ArrayAdapter<String> mAdapter;
    private GSAdapter mGSAdapter;
    private ArrayList<String> items = new ArrayList<String>();
    private Handler mHandler;
    private int start = 0;
    private static int refreshCnt = 0;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);
        geneItems();
        mListView = (GSListView) findViewById(R.id.xListView);
        mListView.setPullLoadEnable(true);
        mGSAdapter =new GSAdapter(this);
        mGSAdapter.setData(items);

        mListView.setAdapter(mGSAdapter);
//		mListView.setPullLoadEnable(false);
//		mListView.setPullRefreshEnable(false);
        mListView.setXListViewListener(new GSListView.IXListViewListener() {
            @Override
            public void onRefresh() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        start -=60;
                        ToastUtils.show(GSListViewActivity.this,"值:"+start);

                        geneItems();
                        mGSAdapter.setData(items);
                        onLoad();
                    }
                }, 2000);
            }

            @Override
            public void onLoadMore() {
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        geneItems();
                        mGSAdapter.setData(items);
                        //有数据
                        onLoad();

//						mListView.showNoData("没有更多结果");
                    }
                }, 2000);
            }
        });
        mHandler = new Handler();
    }

    private void geneItems() {
        items.clear();
        for (int i = 0; i <= 29; ++i) {
            items.add("refresh cnt " + (++start));
            Log.i("++tag", "geneItems: start : "+start);
        }
    }

    private void onLoad() {
        mListView.stopRefresh();
        mListView.setSelection(0);
        mListView.stopLoadMore();
    }
}
