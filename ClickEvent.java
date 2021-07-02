package com.microfountain.butterknifeapplication;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Copyright (C), 2015-2021
 * FileName: ClickEvent
 * Author: hujian
 * Date: 2021/7/2 14:48
 * History:
 * <author> <time> <version> <desc>
 */
@Target(ElementType.ANNOTATION_TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClickEvent {

    String listenerSetter();

    Class<?> listenerType();

    String callbackMethod();

}
