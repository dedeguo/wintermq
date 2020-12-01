package org.example.wintermq;



import java.util.HashMap;
import java.util.Map;
import java.util.Queue;




public class WinterMQHttpConsumerContainer {

//消息队列
    Map<String, Queue<String>> msgQueues=new HashMap<>();

    //locallistener

   // @RequestMapping(value = UriConstant.CONSUME_URI,method = {RequestMethod.POST})
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
