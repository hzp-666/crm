package com.softeem.crm.annotation;

import java.lang.annotation.*;

//自定义的注解类
@Target({ElementType.METHOD})//作用在方法上
@Retention(RetentionPolicy.RUNTIME)//运行时
@Documented
public @interface RequirePermission {
    String code() default "";
}