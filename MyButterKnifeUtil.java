package com.microfountain.butterknifeapplication;

import android.util.Log;
import android.view.View;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Copyright (C), 2015-2021
 * FileName: MyButterKnifeUtil
 * Author: hujian
 * Date: 2021/7/2 12:10
 * History:
 * <author> <time> <version> <desc>
 */
public class MyButterKnifeUtil {

    private static final String TAG = "MyButterKnifeUtil";

    public static void injectLayoutId(Object activity) {
        Class<?> activityClass = activity.getClass();
        if (activityClass.isAnnotationPresent(BindLayoutId.class)) {
            BindLayoutId bindLayoutId = activityClass.getAnnotation(BindLayoutId.class);
            if (bindLayoutId != null) {
                int layoutId = bindLayoutId.value();
                try {
                    Method setContentView = activityClass.getMethod("setContentView", int.class);
                    setContentView.invoke(activity, layoutId);
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    Log.e(TAG, "injectLayoutId: IllegalAccessException " + e.getMessage());
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                    Log.e(TAG, "injectLayoutId: InvocationTargetException " + e.getMessage());
                }
            }
        }
    }

    public static void injectViewId(Object activity) {
        Class<?> activityClass = activity.getClass();
        Field[] fields = activityClass.getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(BindView.class)) {
                BindView annotation = field.getAnnotation(BindView.class);
                if (annotation != null) {
                    int id = annotation.value();
                    try {
                        Method findViewById = activityClass.getMethod("findViewById", int.class);
                        View view = (View) findViewById.invoke(activity, id);
                        field.setAccessible(true);
                        field.set(activity, view);
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        Log.e(TAG, "injectViewId: IllegalAccessException " + e.getMessage());
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                        Log.e(TAG, "injectViewId: InvocationTargetException " + e.getMessage());
                    }
                }
            }
        }
    }

    public static void injectListener(Object o) {
        Class<?> oClass = o.getClass();
        Method[] methods = oClass.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] annotations = method.getAnnotations();
            for (Annotation annotation : annotations) {
                Class<? extends Annotation> annotationType = annotation.annotationType();
                ClickEvent clickEvent = annotationType.getAnnotation(ClickEvent.class);
                if (clickEvent == null) {
                    continue;
                }
                String listenerSetter = clickEvent.listenerSetter();
                Class<?> aClass = clickEvent.listenerType();
                String callbackMethod = clickEvent.callbackMethod();
                try {
                    Method declaredMethod = annotationType.getDeclaredMethod("value");

                    int[] ids = (int[]) declaredMethod.invoke(annotation);
                    if (ids == null) {
                        continue;
                    }
                    for (int id : ids) {

                        Method findViewById = oClass.getMethod("findViewById", int.class);

                        View view = (View) findViewById.invoke(o, id);

                        if (view == null) {
                            continue;
                        }

                        ListenerInvocationHandler listenerInvocationHandler = new ListenerInvocationHandler(o, method);

                        Object proxy = Proxy.newProxyInstance(aClass.getClassLoader(), new Class[]{aClass}, listenerInvocationHandler);

                        Method listenerSetterMethod = view.getClass().getMethod(listenerSetter, aClass);

                        listenerSetterMethod.invoke(view, proxy);

                    }

                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    Log.e(TAG, "injectListener: IllegalAccessException " + e.getMessage());
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    Log.e(TAG, "injectListener: InvocationTargetException " + e.getMessage());
                    e.printStackTrace();
                }

            }
        }
    }


}
