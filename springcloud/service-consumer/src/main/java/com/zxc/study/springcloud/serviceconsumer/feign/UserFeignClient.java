package com.zxc.study.springcloud.serviceconsumer.feign;

import com.zxc.study.springcloud.serviceconsumer.model.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author:zhangxianchao
 * @Description: //定义用户请求的实现接口
 * @Date:Created in 16:21 2018/6/28 0028
 * @Modified By:
 */
@FeignClient(name = "microservce-service-provider")
public interface UserFeignClient {
//
    @RequestMapping(value = "/user/{id}",method = RequestMethod.GET)
    public User findById(@PathVariable("id") Long id);

    /**
     * @author zhangxianchao
     * @Descriptiion  经过测试，如下方式不可用，会出现：
     * Method findById not annotated with HTTP method type (ex. GET, POST)
     * @Date 2018/6/28 0028 16:57
     * @Param
     * @return
     */
//    @GetMapping(value = "/user/{id}")
//    public User findById(@PathVariable("id") Long id);
}
