package com.news;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.utils.LogUtils;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.news.mvp.ui.activitiy.NewsActivity;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class SplashActivity extends AppCompatActivity {

//    @BindView(R.id.logo_outer_iv)
    ImageView mLogoOuterIv;
//    @BindView(R.id.logo_inner_iv)
    ImageView mLogoInnerIv;
//    @BindView(R.id.app_name_tv)
    TextView mAppNameTv;
    boolean isShowingRubberEffect = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.zoomin, 0);
        initView();
        initAnimation();
    }

    private void initView() {
        setContentView(R.layout.activity_splash);
//        ButterKnife.bind(this);
        mLogoInnerIv=(ImageView)findViewById(R.id.logo_inner_iv);
        mLogoOuterIv=(ImageView)findViewById(R.id.logo_outer_iv);
        mAppNameTv=(TextView)findViewById(R.id.app_name_tv);
    }

    private void initAnimation() {
        startLogoInner();
        startLogoOuterAndAppName();
    }
    private void startLogoInner(){
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.anim_top_in);
        mLogoInnerIv.startAnimation(animation);
    }
    private void startLogoOuterAndAppName(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float fraction = valueAnimator.getAnimatedFraction();
                LogUtils.i("fraction : "+fraction);
                if (fraction>=0.8 && !isShowingRubberEffect){
                    isShowingRubberEffect=true;
                    startLogoOuter();
                    startShowAppName();
                    statActivityFinish();
                } else if (fraction>=0.95){
                    valueAnimator.cancel();
                    startLogoInner2();
                }
            }
        });
        valueAnimator.start();
    }

    private void statActivityFinish() {
        Observable.timer(1000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        startActivity(new Intent(SplashActivity.this, NewsActivity.class));
                        overridePendingTransition(0, android.R.anim.fade_out);
                        finish();
                    }
                });
    }

    private void startLogoOuter(){
        YoYo.with(Techniques.RubberBand).duration(1000).playOn(mLogoOuterIv);
    }
    private void startShowAppName(){
        YoYo.with(Techniques.FadeIn).duration(1000).playOn(mAppNameTv);
    }
    private void startLogoInner2() {
        YoYo.with(Techniques.Bounce).duration(1000).playOn(mLogoInnerIv);
    }
}

/**
 * CSDN动画学习的博客http://blog.csdn.net/yegongheng/article/details/38366081
 */
