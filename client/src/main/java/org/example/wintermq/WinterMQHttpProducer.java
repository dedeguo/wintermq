package org.example.wintermq;


import com.alibaba.fastjson.JSON;
import org.example.wintermq.constant.UriConstant;
import org.example.wintermq.util.HttpClient;
import org.example.wintermq.util.HttpUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WinterMQHttpProducer implements WinterMQProducer{

    private String WinterMQHttpServerIp;

    private Integer WinterMQHttpServerPort;

    private String topic;

    public WinterMQHttpProducer(String ip,int port,String topic){
        this.topic=topic;
        this.WinterMQHttpServerIp=ip;
        this.WinterMQHttpServerPort=port;
    }

    @Override
    public void send(String msg) {
        String consumerRegURL="Http://"+WinterMQHttpServerIp+":"+WinterMQHttpServerPort+"/"+ UriConstant.PRODUCE_URI;
        Map<String,Object> data=new HashMap<>();
        data.put("topic",topic);
        data.put("msg",msg);
        String jdata= JSON.toJSONString(data);

        HttpClient.doPost(consumerRegURL,jdata);

    }
}
