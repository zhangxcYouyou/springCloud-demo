package com.zxc.study.springcloud.serviceconsumer.controller;

import com.zxc.study.springcloud.serviceconsumer.feign.UserFeignClient;
import com.zxc.study.springcloud.serviceconsumer.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:zhangxianchao
 * @Description: 使用feign 进行客户端的调用
 * @Date:Created in 16:20 2018/6/28 0028
 * @Modified By:
 */
@RestController
public class ConsumerFeignController {

    private static final Logger logger = LoggerFactory.getLogger(ConsumerFeignController.class);

    @Autowired
    private UserFeignClient userFeignClient;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    /**
     * @author zhangxianchao
     * @Descriptiion  通过feign 方式进行用户请求
     * @Date 2018/6/28 0028 16:27
     * @Param [id]
     * @return com.zxc.study.springcloud.serviceconsumer.model.User
     */
    @GetMapping("/feign/user/{id}")
    public User findById(@PathVariable Long id){
        return this.userFeignClient.findById(id);
    }

    @GetMapping("/feign/log-user-instance")
    public void logUserInstance(){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservce-service-provider");
        logger.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());

    }
}
