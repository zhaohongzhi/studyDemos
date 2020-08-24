package com.study.util;

/**
 * @author zhaohz
 * @Date 2020-08-22
 * @Discribtion 描述
 */
public class SplideTest {

    public static void main(String []args){
        String message = "order_PatrolTask_0100";
        String[] type = message.split("_");
        System.out.println(type[1]);
        System.out.println(type[2]);
    }
}
