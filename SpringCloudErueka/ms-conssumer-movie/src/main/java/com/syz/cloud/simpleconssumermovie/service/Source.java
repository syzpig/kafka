package com.syz.cloud.simpleconssumermovie.service;

import org.springframework.cloud.stream.annotation.Output;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

@Service
public interface Source {

    //发送队列1   OUTPUT_1 = "sourceA" 跟配置文件里面的通道名称 sourceA 保持一致
    String OUTPUT_1 = "sourceA";

    @Output(Source.OUTPUT_1)
    MessageChannel output1();

}