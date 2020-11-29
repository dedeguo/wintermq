package org.example.wintermq;

import org.example.wintermq.constant.UriConstant;
import org.example.wintermq.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * http如何穿透内网？
 */

public class WinterMQHttpConsumer implements WinterMQConsumer{

    /** WinterMQHttpServer服务端IP */
    private String WinterMQHttpServerIp;

    private Integer WinterMQHttpServerPort;

    private String topic;

    @Autowired
    WinterMQHttpConsumerContainer container;
    /**
     * 消费者注册
     */
    public WinterMQHttpConsumer(String ip,int port,String topic)  {

        this.WinterMQHttpServerIp=ip;
        this.WinterMQHttpServerPort=port;
        this.topic=topic;
        String consumerRegURL="Http://"+ip+":"+port+"/"+ UriConstant.SUBSCRIBE_URI;
        Map<String,Object> data=new HashMap<>();
        data.put("topic",topic);
        try {
            HttpUtil.connect(consumerRegURL).setMethod("POST").setPostData(data).execute();
        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }
    }

    /**接受消息*/
    public String consumeMsg(){
        return container.getMsg(topic);
    }


    public void close(){
        String consumerRegURL="Http://"+WinterMQHttpServerIp+":"+WinterMQHttpServerPort+"/"+ UriConstant.UNSUBSCRIBE_URI;
        Map<String,Object> data=new HashMap<>();
        data.put("topic",topic);
        try {
            HttpUtil.connect(consumerRegURL).setMethod("POST").setPostData(data).execute();
        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }
    }

}
