package com.syz.cloud.simpleprovideruser.ctl;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.syz.cloud.simpleprovideruser.dao.UserDao;
import com.syz.cloud.simpleprovideruser.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Administrator on 2017/8/9 0009.
 * 例子比较简单所以service层省去
 */
@RestController //@RestController注解相当于@ResponseBody ＋ @Controller合在一起的作用。
public class UserController {
    @Autowired
    private UserDao userDao;
    /*@Autowired
    private EurekaClient eruekaClient;*/

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/simple/{id}")
    //GetMapping组合注解，相当于@GetMapping是一个组合注解，是@RequestMapping(method = RequestMethod.GET)的缩写。该注解将HTTP Get 映射到 特定的处理方法上。
    public User findById(@PathVariable Long id) {
        return this.userDao.findOne(id);
    }

    //一旦您拥有@EnableDiscoveryClient（或@EnableEurekaClient）的应用程序，您就可以使用它来从Eureka服务器发现服务实例。
    //通过这个可以查看服务的地址ip和端口
 /*   @GetMapping("/eureka-instance")
    public String serviceUrl() {
        //MS-PROVIDER-USER就是虚拟化名称，默认就是Erueka中的Application对应的名称
        InstanceInfo instance = eruekaClient.getNextServerFromEureka("MS-PROVIDER-USER", false);
        return instance.getHomePageUrl();
    }*/
    /**
     *Description:获取本地实例信息  可以查看这台机器ip多少，端口多少，metadata多少，用户微服务的url是什么等信息
     *Created by syz on 2017/9/6 0006 16:08
     */
    @GetMapping("/instance-info")
    public ServiceInstance showInfo() {
        //MS-PROVIDER-USER就是虚拟化名称，默认就是Erueka中的Application对应的名称
        ServiceInstance localhostServiceInstance = discoveryClient.getLocalServiceInstance();
        return localhostServiceInstance;
    }
}
