package org.example.wintermq.connect;



import org.example.wintermq.connect.dto.ProducerReqDTO;
import org.example.wintermq.constant.UriConstant;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Http 协议通信
 */

@RestController
public class RestConection {



    @Resource
    ApplicationContext applicationContext;

    @RequestMapping(value = UriConstant.PRODUCE_URI,method = {RequestMethod.POST})
   // public void acceptMsg(String msg,String topic){
        public void acceptMsg(@RequestBody ProducerReqDTO dto){
        //保存消息 ProducerReqDTO
        System.out.println("服务端接受到消息1111");
        String topic=dto.getTopic();
        String msg=dto.getMsg();
        System.out.println("服务端接受到消息"+topic+"  "+msg);

        //事件
        MsgArrivalEvent event=new MsgArrivalEvent(topic,msg);
        applicationContext.publishEvent(event);
    }

    @RequestMapping(value = UriConstant.SUBSCRIBE_URI,method = {RequestMethod.POST})
    public String  subscribe(HttpServletRequest request,String topic){


        String host =request.getRemoteHost();
       // int port=request.getRemotePort();
        int port=8088;
        ClientInfo clientInfo=new ClientInfo(host,port);
       List<ClientInfo> clientInfos= ClientHolder.get(topic);
       if (clientInfos==null){
           clientInfos=new ArrayList<>();
           ClientHolder.addTopicList(topic,clientInfos);
       }
       //会重复订阅 就会使客户端队列里面有多个重复数据
       clientInfos.add(clientInfo);
        System.out.println(host+"订阅消息"+topic);
       return "success";
    }

    @RequestMapping(value = UriConstant.UNSUBSCRIBE_URI,method = {RequestMethod.POST})
    public void unsubscribe(HttpServletRequest request,String topic){
        String host =request.getRemoteHost();
        int port=request.getRemotePort();
        List<ClientInfo> clientInfos= ClientHolder.get(topic);
        Iterator<ClientInfo> iterator= clientInfos.iterator();
        while (iterator.hasNext()){
            ClientInfo clientInfo=iterator.next();
            if (clientInfo.getIp().equals(host) && port==clientInfo.getPort()){
                iterator.remove();
                break;
            }
        }
    }
}
