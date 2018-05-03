package com.syz.cloud.simpleconssumermovie.ctl;

import com.syz.cloud.simpleconssumermovie.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerController {

    @Autowired
    private KafkaSender kafkaSender;

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void send(){
        kafkaSender.sendMessage();
    }

}
