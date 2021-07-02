package com.microfountain.butterknifeapplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2015-2021
 * FileName: BindView
 * Author: hujian
 * Date: 2021/7/2 12:17
 * History:
 * <author> <time> <version> <desc>
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface BindView {
    int value();
}
