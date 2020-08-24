package com.study.controller;

import com.study.model.JsonResult;
import com.study.service.RedisService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaohz
 * @Date 2020-08-21
 * @Discribtion 描述
 */

@RestController
@RequestMapping("/redis/")
@Api(tags = "Redis相关操作")
public class RedisTestController {

    @Autowired
    private RedisService redisService;

    @ApiOperation(value = "创建和获取redis中的值",notes = "创建和获取redis中的值")
    @PostMapping("setAndGetKey")
    public JsonResult<String> setAndGetKey(@ApiParam(value = "缓存键",name = "redisKey")
                                            @RequestParam(name = "redisKey") String redisKey,
                                            @ApiParam(value = "缓存值",name = "redisValue")
                                            @RequestParam(name = "redisValue") String redisValue){
        return redisService.setAndGetKey(redisKey,redisValue);
    }
}
