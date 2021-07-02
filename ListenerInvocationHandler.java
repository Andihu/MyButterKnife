package com.microfountain.butterknifeapplication;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Copyright (C), 2015-2021
 * FileName: ListenerInvocationHandler
 * Author: hujian
 * Date: 2021/7/2 15:25
 * History:
 * <author> <time> <version> <desc>
 */
public class ListenerInvocationHandler implements InvocationHandler {

    private Object object;

    private Method mMethod;

    public ListenerInvocationHandler(Object object, Method method) {
        this.object = object;
        this.mMethod = method;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        return this.mMethod.invoke(object,args);
    }
}
