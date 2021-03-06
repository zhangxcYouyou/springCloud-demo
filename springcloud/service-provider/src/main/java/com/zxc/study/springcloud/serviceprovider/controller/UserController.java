package com.zxc.study.springcloud.serviceprovider.controller;

import com.zxc.study.springcloud.serviceprovider.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 *
 * @author zhangxianchao
 * @Descriptiion 用户信息管理功能
 * @date 2018/6/27 0027 14:53
 * @param
 * @return
 */
@RestController
public class UserController {

    @Autowired
    private DiscoveryClient discoveryClient;
    /**
     * @author zhangxianchao
     * @Descriptiion  获取用户信息
     * @Date 2018/6/27 0027 15:06
     * @Param [id]
     * @return User
     */
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        User demoUser = new User();
        demoUser.setId(1l);
        demoUser.setAge(20);
        demoUser.setName("张三");
        demoUser.setUsername("zhangxianchao");
        demoUser.setBalance(new BigDecimal(200000));
        return demoUser;
    }

    @GetMapping("/user-instance")
    public List<ServiceInstance> showInfo(){
        return discoveryClient.getInstances("microservce-service-provider");
    }



}
