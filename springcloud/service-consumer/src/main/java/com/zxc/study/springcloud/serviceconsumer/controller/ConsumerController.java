package com.zxc.study.springcloud.serviceconsumer.controller;

import com.netflix.discovery.converters.Auto;
import com.zxc.study.springcloud.serviceconsumer.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
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

    private static final Logger logger = LoggerFactory.getLogger(ConsumerController.class);

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    /**
     * @author zhangxianchao
     * @Descriptiion  通过常规URL 进行访问
     * @Date 2018/6/28 0028 15:25
     * @Param [id]
     * @return com.zxc.study.springcloud.serviceconsumer.model.User
     */
    @GetMapping("/user/{id}")
    public User findById(@PathVariable Long id){
        return restTemplate.getForObject(userServiceUrl+"/user/"+id,User.class);
    }
    /**
     * @author zhangxianchao
     * @Descriptiion  使用Ribbon 来测试URL 请求
     * @Date 2018/6/28 0028 15:23
     * @Param [id]
     * @return com.zxc.study.springcloud.serviceconsumer.model.User
     */
    @GetMapping("/ribbon/user/{id}")
    public User findByIdOnRibbon(@PathVariable Long id){
        return restTemplate.getForObject("http://microservce-service-provider/user/"+id,User.class);
    }

    @GetMapping("/log-user-instance")
    public void logUserInstance(){
        ServiceInstance serviceInstance = this.loadBalancerClient.choose("microservce-service-provider");
        logger.info("{}:{}:{}",serviceInstance.getServiceId(),serviceInstance.getHost(),serviceInstance.getPort());

    }

}
