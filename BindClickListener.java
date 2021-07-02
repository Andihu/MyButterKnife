package com.microfountain.butterknifeapplication;

import android.sax.Element;
import android.view.View;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2015-2021
 * FileName: BindClickListener
 * Author: hujian
 * Date: 2021/7/2 14:51
 * History:
 * <author> <time> <version> <desc>
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ClickEvent(listenerSetter = "setOnClickListener",listenerType = View.OnClickListener.class,callbackMethod = "onClick")
public @interface BindClickListener {
    int[] value();
}
