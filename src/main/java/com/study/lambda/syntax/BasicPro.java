package com.study.lambda.syntax;

import com.study.lambda.interfaces.NoreturnSingleParameter;
import com.study.lambda.interfaces.SingleReturnSingleParameter;

/**
 * @author zhaohz
 * @Date 2020-08-13
 * @Discribtion Lambda表达式的语法精简
 */
public class BasicPro {

    public static void main(String[] args){
        //1.实现一个参数，有返回值的函数式接口，相对于基本语法，参数的类型可以省略
        SingleReturnSingleParameter lambda1 = (a)->{
            System.out.println("=一个参数，有返回值=省略参数类型的lambda表达式==");
            return a*a;
        };
        lambda1.test(10);


        //2.实现一个参数，有返回值类型的函数式接口,相对于基本语法，如果只有一个参数，参数类型可以省略，()可以省略
        SingleReturnSingleParameter lambda11 = a -> {
            System.out.println("=一个参数，有返回值=省略参数类型和()的lambda表达式==");
            return a*a;
        };
        lambda1.test(10);



        //3.实现一个参数没有返回值类型的函数式接口,如果方法体中只有一行代码，则{}可以省略
        NoreturnSingleParameter lambda2 = a -> System.out.println("===一个参数,没有返回值，方法体" +
                "只有一行代码，省略 参数类型、()、{}==参数"+a);
        lambda2.test(10);


        //4.实现一个参数，有返回值，方法体只有返回语句一行代码的函数式接口,省略参数类型，省略(),省略{}，
        //同时return关键字也必须省略，否则会报错

    }
}
