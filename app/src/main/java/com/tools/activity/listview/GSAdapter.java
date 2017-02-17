package com.tools.activity.listview;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tools.R;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

/**
 * Created by xinzhou on 2017/2/16.
 */

public class GSAdapter<T> extends BaseAdapter {
    private ArrayList<T> mList=new ArrayList<>();
    private WeakReference<Activity> weakRefActivity;
    public GSAdapter(Activity activity){
        weakRefActivity = new WeakReference<>(activity);
    }

    /**
     * 底部上拉
     */
    public void setData(ArrayList<T> list){
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public Object getItem(int position) {
        return mList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View inflate = View.inflate(weakRefActivity.get(), R.layout.list_item, null);
        TextView tv=(TextView)inflate.findViewById(R.id.list_item_tv);
        tv.setText(mList.get(position)+"");
        return inflate;
    }
}
