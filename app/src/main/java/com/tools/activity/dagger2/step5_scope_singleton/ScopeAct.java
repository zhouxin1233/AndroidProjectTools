package com.tools.activity.dagger2.step5_scope_singleton;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by Administrator on 2016/12/3 0003.
 */
@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ScopeAct {
}
