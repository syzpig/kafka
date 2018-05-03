package com.syz.cloud.simpleconssumermovie.ctl;


import com.syz.cloud.simpleconssumermovie.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Administrator on 2017/8/10 0010.
 */
@RestController
public class MovieController {
    //使用RestTemplate进行调用user微服务
    @Autowired
    private RestTemplate restTemplate;
    /**
     *Description:描述
     *Created by syz on 2017/8/10 0010 20:31
     * @param id  用户主键
     */
    @GetMapping("/movie/{id}")//这时候请求这个路径，就会根据下面请求user那个微服务，由他进行返回 。
    public User findById(@PathVariable Long id) {
        //通过这个也就是movie中api消费user微服务中api  也就是消费了user服务中 @GetMapping("/simple/{id}")对应的接口
        return this.restTemplate.getForObject("http://localhost:7900/simple/"+id, User.class);
        //事实上这个地址是硬编码，行不通的，如果是云服务，这里的端口是动态的，但一般都是把他们配置在配置文件里也是行不通的，提供者端口改变，消费者也要跟着改
    }
}
