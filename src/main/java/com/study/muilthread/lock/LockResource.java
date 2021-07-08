package com.study.muilthread.lock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockResource {

    private int number = 0;

    private Lock lock = new ReentrantLock();
    private  Condition condition = lock.newCondition();
    /**先上锁，判断number是否为0，不为0 一直等待  为0 则加1并且唤醒其他线程*/
    public void incr(){
        /**上锁*/
        lock.lock();
        try{
            while (number != 0){
                condition.await();
            }
            number++;
            System.out.println(Thread.currentThread().getName()+" :: "+number);
            condition.signalAll();
        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }
    /**先上锁，判断number是否为1，不为1 一直等待  为1 则减1并且唤醒其他线程*/
    public void decr(){
        lock.lock();
        try{
            while (number !=1){
                /**为避免虚假唤醒，判断条件必须写在while循环中*/
                condition.await();
            }
            number--;
            System.out.println(Thread.currentThread().getName()+" :: "+number);
            condition.signalAll();

        }catch (Exception e){

        }finally {
            lock.unlock();
        }

    }
}
