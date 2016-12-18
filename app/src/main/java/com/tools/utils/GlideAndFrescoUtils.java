package com.tools.utils;

import android.content.Context;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tools.R;


public class GlideAndFrescoUtils {
    /**
     * Glide加载图片
     *
     * @param context
     * @param imageUrl
     * @param imageView
     */
//    public static <T> void withImageView(Context context, T imageUrl, ImageView imageView) {
//        loadConfigGlideImage(context, imageUrl, imageView, 0);
//    }


    /**
     * Fresco显示网络中的图片功能
     *
     * @param mContext          上下文
     * @param imageUrl          图片的url
     * @param mSimpleDraweeView setPlaceHolderImage()加在之前的图片，setRetryImage()重新加载的图片
     *                          <p/>
     *                          setFailureImage()加载失败的图片,setIsCircle()是否进行圆角模式
     *                          <p/>
     *                          <p/>
     *                          这里全部使用同一张，具体看项目来进行更改，这里是直接设置。
     */
    public static void frescoDisplayInternetImage(Context mContext, String imageUrl, SimpleDraweeView mSimpleDraweeView) {
        if (mSimpleDraweeView != null && mContext != null && imageUrl != null) {
            /*显示和网络中的一张图片*/
            new FrescoImageUtils.LoadImageFrescoBuilder(mContext, mSimpleDraweeView, imageUrl).setPlaceHolderImage(BitMapAndDrawableUtils.getDrawableFromResources(mContext, R.mipmap.ic_launcher))
                    .setRetryImage(BitMapAndDrawableUtils.getDrawableFromResources(mContext, R.mipmap.ic_launcher)).setFailureImage(BitMapAndDrawableUtils.getDrawableFromResources(mContext, R.mipmap.ic_launcher)).setIsCircle(true).build();
        }

    }

//
//    /**
//     * 设置前景
//     */
//    private static <T> void loadConfigGlideImage(Context mContext, T t, ImageView imageView, int errorId) {
//        boolean isErrorImage = true;
//        if (errorId <= 0) {
//            isErrorImage = false;
//        }
//
//        if (imageView != null && mContext != null) {
//            RequestManager picasso = Glide.with(mContext);
//            DrawableTypeRequest creator = null;
//            if (t != null) {
//                if (t instanceof Uri) {//图片本地路径
//                    creator = picasso.load((Uri) t);
//                } else if (t instanceof String) {//图片网络路径
//                    creator = picasso.load((String) t);
//                } else if (t instanceof File) {//文件
//                    creator = picasso.load((File) t);
//                } else if (t instanceof Integer) {//资源Id
//                    creator = picasso.load((Integer) t);
//                }
//            }
//
//            DrawableRequestBuilder build = null;
//            if (isErrorImage) {
//                build = creator.error(errorId);
//                build.into(imageView);
//                return;
//            }
//            creator.into(imageView);
//        }
//
//
//    }


}
