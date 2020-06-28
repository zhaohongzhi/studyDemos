package com.study.redis.signlepoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.params.SetParams;

import java.util.Collections;

/**
 * Created by thinkpad on 2020/6/22.
 * <p>
 * 单机下的Redis锁
 */
 @Service
 public class RedisLock {

    @Autowired
    private JedisPool jedisPool;

    private String lock_key = "redis_lock"; //锁键
    protected long internalLockLeaseTime = 30000;//锁过期时间
    private long timeout = 999999; //获取锁的超时时间
    //SET命令的参数
    SetParams params = SetParams.setParams().nx().px(internalLockLeaseTime);


    /**
     * 根据ID进行加锁
     * @param id
     * @return
     */
    public boolean lock(String id) {
        Jedis jedis = jedisPool.getResource();
        Long start = System.currentTimeMillis();
        try {
            for (; ; ) {
                //SET命令返回OK ，则证明获取锁成功
                String lock = jedis.set(lock_key, id, params);
                if ("OK".equals(lock)) {
                    return true;
                }
                //否则循环等待，在timeout时间内仍未获取到锁，则获取失败
                long l = System.currentTimeMillis() - start;
                if (l >= timeout) {
                    return false;
                }
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } finally {
            jedis.close();
        }
    }


    /**
     * 解锁方法
     * @param id
     * @return
     */
    public boolean unlock(String id){
        Jedis jedis = jedisPool.getResource();
        String script =
                "if redis.call('get',KEYS[1]) == ARGV[1] then" +
                        "   return redis.call('del',KEYS[1]) " +
                        "else" +
                        "   return 0 " +
                        "end";
        try {
            Object result = jedis.eval(script, Collections.singletonList(lock_key),
                    Collections.singletonList(id));
            if("1".equals(result.toString())){
                return true;
            }
            return false;
        }finally {
            jedis.close();
        }
    }

}
