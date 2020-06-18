package com.study.muilthread.chapter01.thead_method;

/**
 * Created by thinkpad on 2020/6/17.
 *
 * threadA调用共享对象obj的wait()方法后阻塞挂起自己，然后主线程休眠1s后中断了threadA线程，中断后threadA在obj.wait()抛出异常而返回并终止
 */
public class WaitNotifyInterrupt {

    static Object obj = new Object();

    public static void main(String args[]) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                System.out.println("--thread run begin---");
                synchronized (obj){
                        obj.wait();
                    }
                System.out.println("---thread run end---");
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        threadA.start();
        Thread.sleep(100);
        System.out.println("====begin interrupt threadA====");
        threadA.interrupt();
        System.out.println("====end interrupt threadA======");
    }
}
