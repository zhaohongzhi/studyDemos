package com.study.muilthread.thread_lock;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by thinkpad on 2020/6/23.
 *
 * 子线程中调用LocKSupport的park方法，主线程中调用unpark()方法进行唤醒
 */
public class LockSupportPrakAndUnparkTest {

    public static  void main(String args[]) throws InterruptedException {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("====child thread begin park===");
                LockSupport.park();
                System.out.println("===== child thread unpark===");
            }
        });

        //启动子线程
        thread.start();

        //主线程sleep，为了让主线程调用unpark方法前子线程已经调用了park方法
        Thread.sleep(100);

        System.out.println("===main thread begin unpark==");

        //主线程调用unpark方法让子线程持有许可证，然后park方法返回
        LockSupport.unpark(thread);

    }
}
