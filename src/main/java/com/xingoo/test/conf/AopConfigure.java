package com.xingoo.test.conf;


import com.xingoo.test.common.Cache;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Aspect
@Configuration
public class AopConfigure {

    /**
     * 使用内存map模拟缓存
     */
    Map<String, Long> localCache = new HashMap<>();

    @Pointcut("@annotation(com.xingoo.test.common.Cache)")
    public void pointcut(){
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("in pointcut()");

        // 1 获取切面中的方法
        MethodSignature joinPointObject = (MethodSignature) joinPoint.getSignature();
        Method method = joinPointObject.getMethod();

        // 2 获取方法中的注解
        Cache c = method.getAnnotation(Cache.class);
        System.out.println("获得注解中的配置key");

        // 3 校验是否存在key，存在返回map中的值即可；如果不存在则更新，并返回
        if(localCache.containsKey(c.key())){
            System.out.println("方法已经缓存");
            return localCache.get(c.key());
        }else{
            System.out.println("方法还未缓存");
            Long result = (Long) joinPoint.proceed();
            localCache.put(c.key(), result);
            return result;
        }
    }
}
