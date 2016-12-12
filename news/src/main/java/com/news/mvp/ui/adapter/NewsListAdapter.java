package com.news.mvp.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.utils.ConvertUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.news.App;
import com.news.R;
import com.news.mvp.callback.OnItemClickListener;
import com.news.mvp.entity.NewsSummary;
import com.news.mvp.ui.adapter.base.BaseRecyclerViewAdapter;

import java.util.List;

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
            case TYPE_PHOTO_ITEM:
                view = getView(parent, R.layout.item_news_photo);
                final PhotoViewHolder photoItemViewHolder = new PhotoViewHolder(view);
                setItemOnClickEvent(photoItemViewHolder, true);
                return photoItemViewHolder;
            default:
                throw new RuntimeException("there is no type that matches the type " +
                        viewType + " + make sure your using types correctly");
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        setValue(holder,position);
        setItemAppearAnimation(holder, position, R.anim.anim_bottom_in);
    }

    private void setValue(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ItemViewHolder) {
            setItemValues((ItemViewHolder) holder, position);
        } else if (holder instanceof PhotoViewHolder)
            setPhotoItemValues((PhotoViewHolder) holder, position);
    }
    private void setItemValues(ItemViewHolder holder, int position) {
        NewsSummary newsSummary = mList.get(position);
        String title = newsSummary.getLtitle();
        if (title == null) {
            title = newsSummary.getTitle();
        }
        String ptime = newsSummary.getPtime();
        String digest = newsSummary.getDigest();
        String imgSrc = newsSummary.getImgsrc();

        holder.mNewsSummaryTitleTv.setText(title);
        holder.mNewsSummaryPtimeTv.setText(ptime);
        holder.mNewsSummaryDigestTv.setText(digest);

        Glide.with(App.getAppContext()).load(imgSrc).asBitmap() // gif格式有时会导致整体图片不显示，貌似有冲突
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.image_place_holder)
                .error(R.drawable.ic_load_fail)
                .into(holder.mNewsSummaryPhotoIv);
    }
    private void setPhotoItemValues(PhotoViewHolder holder, int position) {
        NewsSummary newsSummary = mList.get(position);
        setTextView(holder, newsSummary);
        setImageView(holder, newsSummary);
    }
    private void setTextView(PhotoViewHolder holder, NewsSummary newsSummary) {
        String title = newsSummary.getTitle();
        String ptime = newsSummary.getPtime();

        holder.mNewsSummaryTitleTv.setText(title);
        holder.mNewsSummaryPtimeTv.setText(ptime);
    }
    private void setImageView(PhotoViewHolder holder, NewsSummary newsSummary) {
        int PhotoThreeHeight = ConvertUtils.dp2px(App.getAppContext(),90);
        int PhotoTwoHeight =  ConvertUtils.dp2px(App.getAppContext(),120);
        int PhotoOneHeight = ConvertUtils.dp2px(App.getAppContext(),150);

        String imgSrcLeft = null;
        String imgSrcMiddle = null;
        String imgSrcRight = null;

        ViewGroup.LayoutParams layoutParams = holder.mNewsSummaryPhotoIvGroup.getLayoutParams();

        if (newsSummary.getAds() != null) {
            List<NewsSummary.AdsBean> adsBeanList = newsSummary.getAds();
            int size = adsBeanList.size();
            if (size >= 3) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();
                imgSrcRight = adsBeanList.get(2).getImgsrc();

                layoutParams.height = PhotoThreeHeight;
                holder.mNewsSummaryTitleTv.setText(App.getAppContext()
                        .getString(R.string.photo_collections, adsBeanList.get(0).getTitle()));
            } else if (size >= 2) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                imgSrcMiddle = adsBeanList.get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1) {
                imgSrcLeft = adsBeanList.get(0).getImgsrc();
                layoutParams.height = PhotoOneHeight;
            }
        } else if (newsSummary.getImgextra() != null) {
            int size = newsSummary.getImgextra().size();
            if (size >= 3) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsSummary.getImgextra().get(1).getImgsrc();
                imgSrcRight = newsSummary.getImgextra().get(2).getImgsrc();

                layoutParams.height = PhotoThreeHeight;
            } else if (size >= 2) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();
                imgSrcMiddle = newsSummary.getImgextra().get(1).getImgsrc();

                layoutParams.height = PhotoTwoHeight;
            } else if (size >= 1) {
                imgSrcLeft = newsSummary.getImgextra().get(0).getImgsrc();
                layoutParams.height = PhotoOneHeight;
            }
        } else {
            imgSrcLeft = newsSummary.getImgsrc();
            layoutParams.height = PhotoOneHeight;
        }

        setPhotoImageView(holder, imgSrcLeft, imgSrcMiddle, imgSrcRight);
        holder.mNewsSummaryPhotoIvGroup.setLayoutParams(layoutParams);
    }
    private void setPhotoImageView(PhotoViewHolder holder, String imgSrcLeft, String imgSrcMiddle, String imgSrcRight) {
        if (imgSrcLeft != null) {
            showAndSetPhoto(holder.mNewsSummaryPhotoIvLeft, imgSrcLeft);
        } else {
            hidePhoto(holder.mNewsSummaryPhotoIvLeft);
        }

        if (imgSrcMiddle != null) {
            showAndSetPhoto(holder.mNewsSummaryPhotoIvMiddle, imgSrcMiddle);
        } else {
            hidePhoto(holder.mNewsSummaryPhotoIvMiddle);
        }

        if (imgSrcRight != null) {
            showAndSetPhoto(holder.mNewsSummaryPhotoIvRight, imgSrcRight);
        } else {
            hidePhoto(holder.mNewsSummaryPhotoIvRight);
        }
    }
    private void showAndSetPhoto(ImageView imageView, String imgSrc) {
        imageView.setVisibility(View.VISIBLE);
        Glide.with(App.getAppContext()).load(imgSrc).asBitmap()
                .format(DecodeFormat.PREFER_ARGB_8888)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .placeholder(R.color.image_place_holder)
                .error(R.drawable.ic_load_fail)
                .into(imageView);
    }
    private void hidePhoto(ImageView imageView) {
        imageView.setVisibility(View.GONE);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        if (isShowingAnimation(holder)) {
            holder.itemView.clearAnimation();
        }
    }
    private boolean isShowingAnimation(RecyclerView.ViewHolder holder) {
        return holder.itemView.getAnimation() != null && holder.itemView
                .getAnimation().hasStarted();
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
    class PhotoViewHolder extends RecyclerView.ViewHolder {
        TextView mNewsSummaryTitleTv;
        LinearLayout mNewsSummaryPhotoIvGroup;
        ImageView mNewsSummaryPhotoIvLeft;
        ImageView mNewsSummaryPhotoIvMiddle;
        ImageView mNewsSummaryPhotoIvRight;
        TextView mNewsSummaryPtimeTv;

        public PhotoViewHolder(View view) {
            super(view);
            mNewsSummaryTitleTv=(TextView)view.findViewById(R.id.news_summary_title_tv);
            mNewsSummaryPhotoIvGroup=(LinearLayout)view.findViewById(R.id.news_summary_photo_iv_group);
            mNewsSummaryPhotoIvLeft=(ImageView) view.findViewById(R.id.news_summary_photo_iv_left);
            mNewsSummaryPhotoIvMiddle=(ImageView) view.findViewById(R.id.news_summary_photo_iv_middle);
            mNewsSummaryPhotoIvRight=(ImageView) view.findViewById(R.id.news_summary_photo_iv_right);
            mNewsSummaryPtimeTv=(TextView) view.findViewById(R.id.news_summary_ptime_tv);
        }
    }
}
