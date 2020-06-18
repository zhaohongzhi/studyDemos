package com.study.muilthread.chapter01.thread_basic;

/**
 * Created by thinkpad on 2020/6/17.
 */
public class ThreadTest{

    /**
     * 创建线程的方式1：实现继承Thread类，重写run()方法，在线程启动时直接调用run()方法
     * 缺点:Java是单继承，无法实现多继承方式
     */
    public static class MyThread extends  Thread{
        @Override
        public void run() {
            System.out.println("I am a child thread");
        }
    }

    public static void main(String args[]){
        MyThread myThread = new MyThread();
        myThread.start();
    }

}
