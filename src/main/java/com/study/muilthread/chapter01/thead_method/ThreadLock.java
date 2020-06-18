package com.study.muilthread.chapter01.thead_method;

/**
 * Created by thinkpad on 2020/6/17.
 *
 *  两个线程执行时都需要获取resourceA 和 resourceB
 *  线程A执行时先获取resourceA的监视器锁，后获取resourceB的监视器锁，线程B执行时，首先sleep 1秒，等线程A 获取resourceA 和resouceB的监视器锁；
 *  此时resouceA调用wait()方法，释放了resourceA自身的监视器锁，但是并不会释放resourceB监视器锁，所以threadB一直获取不到resouceB的监视器锁
 */
public class ThreadLock {
    //创建资源
    private static volatile  Object resourceA = new Object();
    private static volatile  Object resourceB = new Object();
    private static volatile  Object resourceC = new Object();

    public static void main(String args[]) throws InterruptedException {

        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取resourceA共享资源的监视锁
                synchronized (resourceA){
                    System.out.println("thread A get resourceA lock");
                    //获取resourceB共享资源的监视器锁
                    synchronized (resourceB){
                        System.out.println("threadA get resourceB lock");
                        //线程A阻塞，释放获取到的resourceA监视器锁，并不释放resourceB监视器锁
                        System.out.println("threadA release resourceA lock");
                        try {
                            resourceA.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                    //获取resourceA共享资源的监视器锁
                    synchronized (resourceA){
                        System.out.println(" threadB get resourceA lock");
                        System.out.println("threadB try to get resoucreB lock");
                        //尝试获取resourceB共享资源的监视器锁
                        synchronized (resourceB){
                            System.out.println("threadB get resoucreB lock");
                            //线程B阻塞，并释放获取到的resourceA的锁
                            System.out.println("threadA release resourceA lock");
                            resourceA.wait();
                        }
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        threadB.start();

        threadA.join();
        threadB.join();

        System.out.println("main over");
    }
}
