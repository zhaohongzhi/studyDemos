package com.study.service.impl;

import com.study.model.JsonResult;
import com.study.service.RedisService;
import com.study.util.Constant;
import com.study.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author zhaohz
 * @Date 2020-08-21
 * @Discribtion 描述
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {





    @Resource
    private RedisUtil redisUtil;
    @Resource
    private RedisTemplate redisTemplate;


    @Override
    public JsonResult<String> setAndGetKey(String redisKey, String redisValue) {
        log.info(this.getClass().getName()+"====setAndGetKey==="+redisKey);
        //redisUtil.setValueToDataBase(Constant.RedisKeys.TEST,"test-zhaohongzhi",30, TimeUnit.SECONDS,7);
        //redisUtil.setValueToDataBase("HHAHA","haha",20,TimeUnit.SECONDS,0);
        redisTemplate.opsForValue().set(Constant.RedisKeys.TEST,redisValue,10000, TimeUnit.MILLISECONDS);
        log.info(this.getClass().getName()+"===setAndGetKey===放入值成功");
        String value = redisTemplate.opsForValue().get(Constant.RedisKeys.TEST).toString();
        //String value = redisUtil.getValueFromRedisDatabase(Constant.RedisKeys.TEST,7).toString();
        //String dbValue = redisUtil.getValueFromRedisDatabase("HHAHA",0).toString();
        log.info(this.getClass().getName()+"===从redis中取的值为==="+value);
        return JsonResult.success("创建成功"+value);
    }
}
