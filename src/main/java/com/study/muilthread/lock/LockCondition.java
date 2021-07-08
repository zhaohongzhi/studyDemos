package com.study.muilthread.lock;

public class LockCondition {

    public static void main(String[] args) {
        LockResource lockResource = new LockResource();

        new Thread(() -> {
            for(int i = 1;i<10;i++){
            lockResource.incr();
            }
        }, "AA").start();

        new Thread(() -> {
            for(int i = 1;i<10;i++){
                lockResource.decr();
            }
        }, "BB").start();

        new Thread(() -> {
            for(int i = 1;i<10;i++){
                lockResource.incr();
            }
        }, "CC").start();

        new Thread(() -> {
            for(int i = 1;i<10;i++){
                lockResource.decr();
            }
        }, "DD").start();
    }
}
