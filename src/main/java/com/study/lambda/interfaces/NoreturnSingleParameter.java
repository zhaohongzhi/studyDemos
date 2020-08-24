package com.study.lambda.interfaces;

/**
 * @author zhaohz
 * @Date 2020-08-13
 * @Discribtion 没有返回值只有一个参数的接口
 */
@FunctionalInterface
public interface NoreturnSingleParameter {
    void test(int a);
}
