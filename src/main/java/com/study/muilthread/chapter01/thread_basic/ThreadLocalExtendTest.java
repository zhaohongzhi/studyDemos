package com.study.muilthread.chapter01.thread_basic;


/**
 * ThreadLocal不支持继承，及子线程无法访问到父线程中ThreadLocal的变量；
 *
 * 但是通过InheriatableThreadLoca可以访问到父线程中的变量。
 */
public class ThreadLocalExtendTest {

    public static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    public static InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();
    public static void main(String args[]){
        //父线程中的TrheadLocal变量
        threadLocal.set("I am father ThreadLocal");
        //父线程中的 InheritableThreadLocal 变量
        inheritableThreadLocal.set(" I am fater InheritableThreadLocal");
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(" I am child ThreadLocal=="+threadLocal.get());
                System.out.println(" I am child InheritableThreadLocal=="+inheritableThreadLocal.get());
            }
        });
        thread.start();
        System.out.println("I am main ThreadLocal =="+threadLocal.get());
        System.out.println("I am main InheritableThreadLocal=="+inheritableThreadLocal.get());
    }
}
