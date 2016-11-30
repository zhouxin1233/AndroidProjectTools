package com.tools.activity;

import android.app.Activity;
import android.os.Bundle;

import com.facebook.drawee.view.SimpleDraweeView;
import com.tools.R;
import com.tools.constant.Urls;
import com.tools.utils.GlideAndFrescoUtils;


/**
 * 圆形头像的实现
 */
public class RoundZoomImageViewActivity extends Activity {
    private SimpleDraweeView simple_image_view_one;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_round_zoom_imageview);
        simple_image_view_one = (SimpleDraweeView) findViewById(R.id.simple_image_view_one);
        GlideAndFrescoUtils.frescoDisplayInternetImage(this, Urls.FRESCO_IMAGE_LOGO_IMAGE_PIPELINE,simple_image_view_one);
    }
}
