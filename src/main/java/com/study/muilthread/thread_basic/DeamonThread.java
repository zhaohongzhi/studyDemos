package com.study.muilthread.thread_basic;

/**
 * 守护线程
 */
public class DeamonThread {

    public static void main(String args[]){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                for(; ;){
                    System.out.println("---线程进入死循环----");
                }
            }
        });
        //设置线程为守护线程，当线程为守护线程时，主线程结束，子线程也结束；
        //当子线程不是守护线程是，主线程结束，子线程继续运行
        thread.setDaemon(true);
        thread.start();
        System.out.println("==main thread is over==");
    }
}
