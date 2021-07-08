package com.study.muilthread.sync;

/**
 * synchronized关键字实现线程间的通信过程，该类为一个资源类，多个线程同时操作资源类中的方法

 */
public class ThreadResource {

    private int number = 0;

    /**
     * 数字增加方法，当数字为0时，线程处于等待状态，获取到资源以后对数字进行加1的操作
     * @throws InterruptedException
     */
    public synchronized  void incr() throws InterruptedException {
        while(number != 0){
            //线程等待
            /**wait方法必须放在while循环中，避免虚假唤醒*/
            this.wait();
        }
        number++;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        //通知其他线程(唤醒所有所有处在等待状态的线程)
        this.notifyAll();;
    }


    public synchronized  void decr() throws InterruptedException {
        while(number !=1){
            //线程等待
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName()+"::"+number);
        //唤醒其他线程(随机唤醒一个处在等待状态中的线程)
        this.notifyAll();
    }
}
