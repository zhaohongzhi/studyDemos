package com.study.muilthread.sync;

public class ThreadCommunicate {


    public static void main(String[] args) {

        /**
         * 启动四个线程去进行竞争：
         * 1.AA线程和CC线程去竞争incr方法
         * 2.BB线程和DD线程去竞争decr方法
         * 执行的流程：
         * 线程调用start方法后等待操作系统调度，如果是BB或者DD线程开始执行则去竞争执行decr方法
         * 如果此时数字不是1，则进行等待（其他线程可以抢到锁），如果数字是1则对数字减一操作后随机唤醒一个等待中的线程，此时如果AA或者CC中的任意一个线程被唤醒，则会执行incr方法
         *
         * 如果AA或者CC线程启动，竞争执行incr方法，拿到锁后判断数字是否为0，如果不是0则等待，如果是0则加1后唤醒所有等待中的线程
         */
        ThreadResource threadResource = new ThreadResource();
        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    threadResource.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "AA").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    threadResource.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "BB").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    threadResource.incr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "CC").start();

        new Thread(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    threadResource.decr();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "DD").start();
    }
}
