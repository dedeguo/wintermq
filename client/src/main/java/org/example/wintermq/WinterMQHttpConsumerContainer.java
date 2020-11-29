package org.example.wintermq;

import org.example.wintermq.constant.UriConstant;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


//TODO 监听。。。
@RestController
public class WinterMQHttpConsumerContainer {

//消息队列
    Map<String, Queue<String>> msgQueues=new HashMap<>();

    @RequestMapping(value = UriConstant.CONSUME_URI,method = {RequestMethod.POST})
    public void  consumeMsg(String topic,String msg){
        Queue<String> queue=msgQueues.get(topic);
        queue.add(msg);

    }


    public String getMsg(String topic){
        Queue<String> queue=msgQueues.get(topic);

        String msg=queue.poll();
        return msg;
    }


}
