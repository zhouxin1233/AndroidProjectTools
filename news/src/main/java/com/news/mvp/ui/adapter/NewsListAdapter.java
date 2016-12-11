package com.news.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.news.R;
import com.news.mvp.callback.OnItemClickListener;
import com.news.mvp.entity.NewsSummary;
import com.news.mvp.ui.adapter.base.BaseRecyclerViewAdapter;

import javax.inject.Inject;

/**
 * Created by Administrator on 2016/12/11 0011.
 */

public class NewsListAdapter extends BaseRecyclerViewAdapter<NewsSummary>{
    public static final int TYPE_PHOTO_ITEM = 2;
    public interface OnNewsListItemClickListener extends OnItemClickListener {
        void onItemClick(View view, int position, boolean isPhoto);
    }
    @Inject
    public NewsListAdapter() {
        super(null);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType){
            case TYPE_FOOTER:
                view = getView(parent, R.layout.item_news_footer);
                return new FooterViewHolder(view);
            case TYPE_ITEM:
                view = getView(parent, R.layout.item_news);
                final ItemViewHolder itemViewHolder = new ItemViewHolder(view);
                setItemOnClickEvent(itemViewHolder, false);
                return itemViewHolder;
//            case TYPE_PHOTO_ITEM:
//                view = getView(parent, R.layout.item_news_photo);
//                final PhotoViewHolder photoItemViewHolder = new PhotoViewHolder(view);
//                setItemOnClickEvent(photoItemViewHolder, true);
//                return photoItemViewHolder;
//            default:
//                throw new RuntimeException("there is no type that matches the type " +
//                        viewType + " + make sure your using types correctly");
        }
        return super.onCreateViewHolder(parent, viewType);
    }
    class ItemViewHolder extends RecyclerView.ViewHolder {
        ImageView mNewsSummaryPhotoIv;
        TextView mNewsSummaryTitleTv;
        TextView mNewsSummaryDigestTv;
        TextView mNewsSummaryPtimeTv;

        public ItemViewHolder(View view) {
            super(view);
            mNewsSummaryPhotoIv=(ImageView)view.findViewById(R.id.news_summary_photo_iv);
            mNewsSummaryTitleTv=(TextView)view.findViewById(R.id.news_summary_title_tv);
            mNewsSummaryDigestTv=(TextView)view.findViewById(R.id.news_summary_digest_tv);
            mNewsSummaryPtimeTv=(TextView)view.findViewById(R.id.news_summary_ptime_tv);
        }
    }
    private void setItemOnClickEvent(final RecyclerView.ViewHolder holder, final boolean isPhoto) {
        if (mOnItemClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((OnNewsListItemClickListener) mOnItemClickListener).onItemClick(v, holder.getLayoutPosition(), isPhoto);
                }
            });
        }
    }
}
