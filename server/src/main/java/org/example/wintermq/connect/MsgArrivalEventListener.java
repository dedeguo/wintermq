package org.example.wintermq.connect;

import com.alibaba.fastjson.JSON;
import org.example.wintermq.constant.UriConstant;
import org.example.wintermq.util.HttpClient;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class MsgArrivalEventListener {



    @Async
    @EventListener
    public void onAppEvent(MsgArrivalEvent event){
       /** 查询订阅topic 的消费者*/
       List<ClientInfo>  clients = ClientHolder.get(event.getTopic());
       String msg=event.getMsg();

       if (clients!=null && clients.size()!=0){
           for (ClientInfo clientInfo:clients){
               //   clientInfo
               String consumeUrl= clientInfo.getServer()+UriConstant.CONSUME_URI;
               Map<String,Object> data=new HashMap<>();
               data.put("topic",event.getTopic());
               data.put("msg",event.getMsg());
               /** 发送消息 **/
               String jdata=JSON.toJSONString(data);
               System.out.println("给订阅者发送消息"+consumeUrl+" "+jdata);
               HttpClient.doPost(consumeUrl,jdata);

           }
       }


    }
}
