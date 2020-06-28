package com.study.redis.signlepoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by thinkpad on 2020/6/22.
 */
@RestController
public class TestLockController {

    @Autowired
    RedisLock redisLock;


    int count = 0;

    @RequestMapping("/index")
    public String index() throws InterruptedException {


        int clientcount =1000;
        CountDownLatch countDownLatch = new CountDownLatch(clientcount);

        ExecutorService executorService = Executors.newFixedThreadPool(clientcount);
        long start = System.currentTimeMillis();
        for (int i = 0;i<clientcount;i++){
            executorService.execute(() -> {

                //通过Snowflake算法获取唯一的ID字符串
                String id = String.valueOf(SnowFlake.nextId());
                try {
                    redisLock.lock(id);
                    count++;
                }finally {
                    redisLock.unlock(id);
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        long end = System.currentTimeMillis();
        System.out.println("执行线程数:{},总耗时:{},count数为:{}"+clientcount+"===="+(end-start)+"===="+count);
        return "Hello";
    }
}
