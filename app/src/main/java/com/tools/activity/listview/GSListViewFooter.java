/**
 * @file XFooterView.java
 * @create Mar 31, 2012 9:33:43 PM
 * @author Maxwin
 * @description XListView's footer
 */
package com.tools.activity.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tools.R;


public class GSListViewFooter extends RelativeLayout {
	public final static int STATE_NORMAL = 0;
	public final static int STATE_READY = 1;
	public final static int STATE_LOADING = 2;

	private Context mContext;

	private View mContentView;
	private View mProgressBar;
	private TextView mHintView;
	
	public GSListViewFooter(Context context) {
		this(context,null);
	}

	public GSListViewFooter(Context context, AttributeSet attrs) {
		super(context, attrs);
		initView(context);
	}

	private void initView(Context context) {
		mContext = context;
		RelativeLayout moreView = (RelativeLayout) LayoutInflater.from(mContext).inflate(R.layout.gs_listview_footer, null);
		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		addView(moreView,params);

		mContentView = moreView.findViewById(R.id.xlistview_footer_content);
		mProgressBar = moreView.findViewById(R.id.xlistview_footer_pb);
		mHintView = (TextView)moreView.findViewById(R.id.xlistview_footer_tv);
	}

	public void setState(int state) {
		mProgressBar.setVisibility(View.GONE);
		switch (state){
			case STATE_READY:
				mHintView.setText("松开载入更多");
				break;
			case STATE_LOADING:
				mHintView.setText("正在加载中...");
				mProgressBar.setVisibility(VISIBLE);
				break;
			case STATE_NORMAL:
				mHintView.setText("上滑加载更多");
				break;
		}
	}

	public void setBottomMargin(int height) {
		if (height < 0) return ;
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.bottomMargin = height;
		mContentView.setLayoutParams(lp);
	}


	public int getBottomMargin() {
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		return lp.bottomMargin;
	}


	/**
	 * 隐藏底部布局
	 */
	public void hide() {
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.height = 0;
		mContentView.setLayoutParams(lp);
	}

	/**
	 * 显示底部布局
	 */
	public void show() {
		LayoutParams lp = (LayoutParams)mContentView.getLayoutParams();
		lp.height = LayoutParams.WRAP_CONTENT;
		mContentView.setLayoutParams(lp);
	}


	public void showNoData(String str) {
		mHintView.setText(str);
		mProgressBar.setVisibility(GONE);
	}
}
