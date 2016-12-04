package com.news.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Administrator on 2016/12/4 0004.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PreApp {
}
