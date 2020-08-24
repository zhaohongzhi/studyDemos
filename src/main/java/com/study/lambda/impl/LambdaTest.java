package com.study.lambda.impl;

/**
 * @author zhaohz
 * @Date 2020-08-12
 * @Discribtion 描述
 */
public class LambdaTest {
}

@FunctionalInterface
interface Test1{
    void test1();
}

@FunctionalInterface
interface Test2{
    void test2();
    @Override
    String toString();
    default void test(){};
}