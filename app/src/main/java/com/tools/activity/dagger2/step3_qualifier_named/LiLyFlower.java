package com.tools.activity.dagger2.step3_qualifier_named;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface LiLyFlower {
}
