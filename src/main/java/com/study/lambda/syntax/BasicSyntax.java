package com.study.lambda.syntax;

import com.study.lambda.interfaces.*;

/**
 * @author zhaohz
 * @Date 2020-08-13
 * @Discribtion Lambda表达式的基本语法
 */
public class BasicSyntax {

    public static void main(String[] args){

        //1.实现无参数无返回值的接口,lambda直接实现该接口(使用接口直接声明，相当于接口实现类中重载接口的方法)
        NoreturnNoParameter lambda = ()-> System.out.println("==1==这是一个无参数无返回值的方法");
        //接口的引用调用接口中的方法
        lambda.test();

        //2.实现一个参数，无返回值的函数式接口，跟上面方法相比->后面多了{},在有些情况下,{}可以省略掉
        NoreturnSingleParameter noReturnLambda = (int a)-> System.out.println("==2==这是一个一个参数无返回值的方法，参数a"+a);
        noReturnLambda.test(10);

        //3.实现多个参数没有返回值的函数式接口
        NoreturnMutipleParameter lambda3 = (int a,String b)->{
            System.out.println("==3==多个参数，没有返回值的接口，参数a"+a+"--参数b--"+b);
        };
        //接口的引用调用接口中的方法
        lambda3.test(100,"200");

        //4.实现无参数有返回值的函数式接口
        SingleReturnNoParameter lambda4 = ()->{
            System.out.println("==4==这是一个无参数有返回值的函数式接口");
            return "10";
        };
        String result = lambda4.test();
        System.out.println("==4==无参函数返回值=="+result);

        //5.实现一个参数，有返回值的参数接口
        SingleReturnSingleParameter lambda5 = (int a)->{
            System.out.println("==5==一个参数一个返回值的接口，返回值=="+a);
            return a;
        };
        lambda5.test(10);


        //6.实现多个参数有返回值的函数式接口
        SingleReturnMutipleParameter lambda6 = (int a,int b)->{
            System.out.println("==6==多个参数有返回值的接口==a"+a+"==b=="+b);
            return a+b;
        };
        lambda6.test(100,200);

    }
}
