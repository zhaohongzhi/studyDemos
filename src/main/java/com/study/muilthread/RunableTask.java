package com.study.muilthread;

import org.omg.Messaging.SYNC_WITH_TRANSPORT;

/**
 * Created by thinkpad on 2020/6/17.
 *
 * 创建线程的方式2：实现Runnable接口
 */
public class RunableTask implements  Runnable {
    @Override
    public void run() {
        System.out.println("I am a child thread");
    }

    public static  void main(String args[]){

        RunableTask runableTask = new RunableTask();
        new Thread(runableTask).start();
        new Thread(runableTask).start();
    }

}
