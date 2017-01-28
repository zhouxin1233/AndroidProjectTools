package com.tools.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.tools.R;
import com.tools.utils.LogUtil;
import com.tools.view.CircleImageView;

public class SuspensionActivity extends AppCompatActivity {
    public static final String TAG="SuspensionActivity";
    private RelativeLayout mSuspensionBar;
    private TextView mSuspensionTv;
    private CircleImageView mSuspensionIv;
    private RecyclerView mRl;
    private int mSuspensionHeight;
    private int mCurrentPosition = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suspension);
        mSuspensionBar = (RelativeLayout) findViewById(R.id.suspention_bar);
        mSuspensionTv = (TextView) findViewById(R.id.tv_nickname);
        mSuspensionIv = (CircleImageView) findViewById(R.id.iv_avatar);

        mRl=(RecyclerView)findViewById(R.id.recycler_suspention);
        mRl.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRl.setLayoutManager(linearLayoutManager);
        mRl.setAdapter(new MyAdapter(this));

        mRl.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                mSuspensionHeight = mSuspensionBar.getHeight();
                LogUtil.I(TAG,mSuspensionHeight+"高度");
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View view = linearLayoutManager.findViewByPosition(mCurrentPosition + 1);
                if (view!=null){
                    if (view.getTop()<=mSuspensionHeight){//两者开始重合
                       mSuspensionBar.setY(view.getTop()-mSuspensionHeight);
                        LogUtil.I(TAG,view.getTop()+" : view.getTop()");
                        LogUtil.I(TAG,mSuspensionHeight+" : mSuspensionHeight");
                        LogUtil.I(TAG,view.getTop()-mSuspensionHeight+" : 差值");
                    }else{
                        mSuspensionBar.setY(0);
                    }
                }
                if(mCurrentPosition!=linearLayoutManager.findFirstVisibleItemPosition()){
                    mCurrentPosition = linearLayoutManager.findFirstVisibleItemPosition();
                    mSuspensionBar.setY(0);

                    Glide.with(SuspensionActivity.this)
                            .load(getAvatarResId(mCurrentPosition))
                            .into(mSuspensionIv);
                    mSuspensionTv.setText("昵称"+mCurrentPosition);
                }

            }
        });
        Glide.with(SuspensionActivity.this)
                .load(getAvatarResId(mCurrentPosition))
                .into(mSuspensionIv);
        mSuspensionTv.setText("昵称"+mCurrentPosition);
    }


    class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

        private Context mContext;

        public MyAdapter(Context context) {
            mContext = context;
        }

        @Override
        public int getItemCount() {
            return 100;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_supention, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Glide.with(mContext)
                    .load(getAvatarResId(position))
                    .into(holder.mIvAvatar);
            Glide.with(mContext)
                    .load(getAvatarResId(position))
                    .into(holder.mIvContent);
            holder.mTvNickname.setText("昵称"+position);
        }
        class MyViewHolder extends RecyclerView.ViewHolder{
            ImageView mIvContent;
            CircleImageView mIvAvatar;
            TextView mTvNickname;
            public MyViewHolder(View itemView) {
                super(itemView);
                mIvAvatar = (CircleImageView) itemView.findViewById(R.id.iv_avatar);
                mIvContent = (ImageView) itemView.findViewById(R.id.iv_content);
                mTvNickname = (TextView) itemView.findViewById(R.id.tv_nickname);
            }
        }
    }
    private int getAvatarResId(int position) {
        switch (position % 4) {
            case 0:
                return R.mipmap.avatar1;
            case 1:
                return R.mipmap.avatar2;
            case 2:
                return R.mipmap.avatar3;
            case 3:
                return R.mipmap.avatar4;
        }
        return 0;
    }

}
