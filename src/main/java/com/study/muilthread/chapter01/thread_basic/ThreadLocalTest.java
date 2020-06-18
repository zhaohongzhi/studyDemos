package com.study.muilthread.chapter01.thread_basic;


/**
 * 通过ThreadLocal声明在多线程访问同一个变量的情况下，读取变量的副本;
 *
 * 可以用ThreadLoca声明变量，保证在同一个主线程中每一个子线程读取到的变量都是自己的
 */
public class ThreadLocalTest {


    public static void print(String param){
        System.out.println("=====输出的值为===="+param+"===="+threadLocal.get());
        //threadLocal.remove();  清空当前值
    }

    static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String args[]){

        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                threadLocal.set("==threadOne local param==");
                print("threadOne");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("====threadOne after remove=="+threadLocal.get());
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("====threadTwo get threadLocal==="+threadLocal.get());
                threadLocal.set("===threadTwo local param==");
                print("threadB");
                System.out.println("===threadTwo after remove=="+threadLocal.get());
            }
        });

        threadOne.start();
        threadTwo.start();
    }
}
