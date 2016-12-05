package com.news.dagger.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * 自定义注解 针对单利模式 生命周期啊啊啊啊
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PreActivity {
}
