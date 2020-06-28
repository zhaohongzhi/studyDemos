package com.study.muilthread.thead_method;

/**
 * Created by thinkpad on 2020/6/17.
 * Thread 的 join()方法等待线程执行结束
 */
public class ThreadJoinMethod {

    public static void main(String args[]) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("===I am threadOne===");
            }
        });

        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("===I am threadTwo===");
            }
        });

        Thread threadThree = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("===I am threadThree===");
            }
        });

        Thread threadFour = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("===I am threadFour===");
            }
        });

        threadOne.start();
        threadTwo.start();
        threadThree.start();
        threadFour.start();
        System.out.println("===wait all child thread over!");
        threadOne.join();
        threadTwo.join();
        threadThree.join();
        threadFour.join();
        System.out.println("===main over===");
    }

}
