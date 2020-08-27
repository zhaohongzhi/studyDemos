package com.study.spring.comment;

import com.baomidou.mybatisplus.extension.api.R;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhaohz
 * @Date
 * @Discribtion 描述
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Scope {
    //默认singleton
    String value() default "singleton";
}
