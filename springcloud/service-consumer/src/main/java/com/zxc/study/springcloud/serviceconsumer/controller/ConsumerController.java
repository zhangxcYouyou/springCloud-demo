package com.zxc.study.springcloud.serviceconsumer.controller;

import com.zxc.study.springcloud.serviceconsumer.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @Author:zhangxianchao
 * @Description: 调用用户服务controller
 * @Date:Created in 15:10 2018/6/27 0027
 * @Modified By:
 */
@RestController
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        return restTemplate.getForObject(userServiceUrl+"/user/"+id,User.class);
    }
}
