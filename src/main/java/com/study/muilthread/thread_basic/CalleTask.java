package com.study.muilthread.thread_basic;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by thinkpad on 2020/6/17.
 * 实现线程的第三种方式：通过FutureTask来创建，前两种方式没法拿到线程执行的结果，使用FutrueTask的方式可以拿到线程执行的结果
 */
public class CalleTask implements Callable<String> {
    @Override
    public String call() throws Exception {
        return "hello I am Callable";
    }

    public static void main(String args[]){
        FutureTask<String> futureTask = new FutureTask<String>(new CalleTask());
        new Thread(futureTask).start();
        try {
            String result = futureTask.get();
            System.out.println(result);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
