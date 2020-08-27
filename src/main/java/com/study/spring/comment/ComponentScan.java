package com.study.spring.comment;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhaohz
 * @Date 2020-08-25
 * @Discribtion 注入注解
 */

/**
 * @Target注解：
 *
 * @Retention注解：
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComponentScan {
    // 表示扫描的package的路径，默认值
    String value() default "";
}
