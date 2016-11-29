package com.tools.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;


/**
 * Picasso与Imageloader显示图片的功能封装
 */
public class PicassoWithImageLoaderImageViewUtils {
    /**
     * Picasso加载图片
     * @param context
     * @param imageUrl
     * @param imageView
     */
    public static void withImageView(Context context, String imageUrl, ImageView imageView) {
        if (imageView != null && context != null && imageUrl != null) {
            Picasso.with(context).load(imageUrl).into(imageView);
        }

    }



}
