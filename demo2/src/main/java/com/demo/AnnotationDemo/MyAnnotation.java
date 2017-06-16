package com.demo.AnnotationDemo;

import java.lang.annotation.*;

/**
 * Created by linkang on 17-6-13.
 */


@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.TYPE})
@Documented
public @interface MyAnnotation {
    String sval() default "name";
    int ival() default 0;
    MyTestType[] type() default {};
}
