package com.microfountain.butterknifeapplication;

import androidx.annotation.LayoutRes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2015-2021
 * FileName: BindLayoutId
 * Author: hujian
 * Date: 2021/7/2 12:07
 * History:
 * <author> <time> <version> <desc>
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindLayoutId {
    @LayoutRes
    int value();
}
