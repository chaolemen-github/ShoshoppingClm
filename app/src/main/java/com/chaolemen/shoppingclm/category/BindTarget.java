package com.chaolemen.shoppingclm.category;

import android.app.Activity;

import java.lang.reflect.Field;

public class BindTarget {
    //执行绑定的流程
    static void bind(Activity activity){
        Class<?> aClass = activity.getClass();
        //获取所有类中的所有的变量的类对象
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            MyButterKnife annotation = field.getAnnotation(MyButterKnife.class);
            if (annotation!=null){
                int value = annotation.value();
                try {
                    field.setAccessible(true);
                    field.set(activity,activity.findViewById(value));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
