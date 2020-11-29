package org.example.wintermq.connect;

import org.example.wintermq.constant.UriConstant;
import org.example.wintermq.util.HttpUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MsgArrivalEventListener {

    @Resource
    ApplicationContext applicationContext;

    @Async
    @EventListener
    public void onAppEvent(MsgArrivalEvent event){
       /** 查询订阅topic 的消费者*/
       List<ClientInfo>  clients = ClientHolder.get(event.getTopic());
       String msg=event.getMsg();

       /** 发送消息 **/
       for (ClientInfo clientInfo:clients){
        //   clientInfo
          String consumeUrl= clientInfo.getServer()+UriConstant.CONSUME_URI;
           Map<String,Object> data=new HashMap<>();
           data.put("topic",event.getTopic());
           data.put("msg",event.getMsg());

           try {
               HttpUtil.connect(consumeUrl).setMethod("POST").setPostData(data).execute();
           } catch (IOException e) {
               //TODO
               e.printStackTrace();
           }

       }
    }
}
