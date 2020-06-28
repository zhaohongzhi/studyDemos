package com.study.muilthread.thead_method;

/**
 * Created by thinkpad on 2020/6/17.
 * 启动两个线程，分别获取资源监视器resourceA，并且调用wait()方法释放监视器锁，
 * 启动第三个线程获取资源监视器resouceA，并且调用resourceA的notify（）方法，随机唤醒一个；如果调用notifyAll()方法，则全部都唤醒
 */

public class WaitAndNotify {
    //定义共享资源
    private static volatile  Object resourceA = new Object();

    public static void main(String args[]) throws InterruptedException {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    System.out.println("===threadA get resouceA===");

                    try {
                        System.out.println("===threadA begin wait===");
                        resourceA.wait();
                        System.out.println("====threadA end wait===");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (resourceA){
                    System.out.println("===threadB get resouceA===");

                    try {
                        System.out.println("===threadB begin wait===");
                        resourceA.wait();
                        System.out.println("====threadB end wait===");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        Thread threadC = new Thread(new Runnable() {
            @Override
            public void run() {
                    synchronized (resourceA){
                        System.out.println("===threadC begin notifyAll====");
                        //resourceA.notify();实际唤醒一个
                        resourceA.notifyAll();
                    }
            }
        });

        threadA.start();
        threadB.start();
        //Thread.sleep(1000);  如果去掉该步骤，则可能threadB的wait方法在threadC的notifyAll方法之后执行，无法进行唤醒notifyAll之后的方法
        threadC.start();
        threadA.join();//等待线程执行结束方法
        threadB.join();
        threadC.join();
        System.out.println("main--over");

    }
}
