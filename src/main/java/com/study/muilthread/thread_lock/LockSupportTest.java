package com.study.muilthread.thread_lock;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by thinkpad on 2020/6/23.
 *
 * 测试LockSupport的park()方法和unpark()方法的原理
 */
public class LockSupportTest {

    public static void main(String args[]){
        LockSupportTest lockSupportTest = new LockSupportTest();

        lockSupportTest.parkTest();

    }

    /**
     * 直接调用park()方法，由于并没有拿到与LockSupport关联的许可证，所以调用park()方法之后线程会被阻塞
     */
    public void parkTest(){
        System.out.println("====begin park==");

        LockSupport.park();

        LockSupport.unpark(Thread.currentThread());

        System.out.println("====end park====");

    }

    /**
     * 当一个线程调用LockSupport的unpark()方法时，如果参数thread没有持有thread与LockSupport类关联的许可证，
     * 则让thread线程持有。如果thread因为之前调用part()而被挂起，则调用unpark()方法后，该线程会被唤醒
     */
    public void unParkTest(){
        System.out.println("====begin park====");

        LockSupport.unpark(Thread.currentThread());

        LockSupport.park();

        System.out.println("====end park====");
    }
}
