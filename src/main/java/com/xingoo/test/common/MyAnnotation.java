package com.xingoo.test.common;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * source, 编译时忽略
 * class，编译时保留在class中，jvm加载时忽略
 * runtime，编译时保留，jvm加载（可以通过反射读取相关配置）
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface MyAnnotation {
    String a() default "a_value";
    String b();
    int[] c() default {1, 2, 3, 4};
    Class d() default String.class;
}
