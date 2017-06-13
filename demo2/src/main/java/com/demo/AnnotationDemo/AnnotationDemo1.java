package com.demo.AnnotationDemo;

import com.demo.ThreadDemo.ThreadDemo1;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by linkang on 17-6-13.
 */
public class AnnotationDemo1 {

    public static void main(String[] args) throws Exception{
        String s1="hello";
        String s2="he"+"llo";

        System.out.println(s1 == s2);

//        for (Field field : ThreadDemo1.class.getDeclaredFields()) {
//            System.out.println(field.getName());
//        }

        for (Method method : ThreadDemo1.class.getMethods()) {
//            System.out.println(method.getName());
        }

        Class<ThreadDemo1> demo1Class = ThreadDemo1.class;

        Method method2 = demo1Class.getMethod("method2", int.class);

        ThreadDemo1 threadDemo1 = demo1Class.newInstance();

        method2.invoke(threadDemo1, 2);


        MyAnnotation annotation1 = method2.getAnnotation(MyAnnotation.class);

        if (annotation1 != null) {
            System.out.println(annotation1.sval());
        }

        Method method1 = demo1Class.getDeclaredMethod("method1");
        method1.setAccessible(true);
        method1.invoke(threadDemo1);

        for (Annotation annotation : demo1Class.getAnnotations()) {
            if (annotation instanceof MyAnnotation) {
                String value = ((MyAnnotation) annotation).sval();
                System.out.println(">>>>" + value);
            }
        }
    }
}
