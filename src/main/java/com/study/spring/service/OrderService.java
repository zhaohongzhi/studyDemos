package com.study.spring.service;

import com.study.spring.comment.Autowired;
import com.study.spring.comment.Component;

/**
 * @author zhaohz
 * @Date 2020-08-25
 * @Discribtion 描述
 */
@Component("orderService")
public class OrderService {
    @Autowired
    private UserService userService;

    public UserService getUserService(){
        return userService;
    }
}
