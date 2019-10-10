package com.xingoo.test.common;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClass {

    @MyAnnotation(a = "123", b = "456", c = {7, 8, 9}, d=MyClass.class)
    public void doSomething(){
        System.out.println("hello");
    }

    public static void main(String[] args) {
        MyClass myClass = new MyClass();

        if (MyClass.class.isAnnotationPresent(MyAnnotation.class)){
            System.out.println("have ann");
        }

        Class<MyClass> cls = MyClass.class;
        try {
            Method m = cls.getMethod("doSomething");
            if(m.isAnnotationPresent(MyAnnotation.class)){
                m.invoke(myClass);
            }

            // 获取注解
            MyAnnotation myAnnotation = m.getAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation.a());
            System.out.println(myAnnotation.b());
            System.out.println(myAnnotation.c());
            System.out.println(myAnnotation.d());



        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
