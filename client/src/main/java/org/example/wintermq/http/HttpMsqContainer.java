package org.example.wintermq.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 * 接收消息
 */
public class HttpMsqContainer {


    private volatile static HttpMsqContainer instance=null;

    Map<String, Queue<String>> msgQueues=new HashMap<>();

    HttpClientMsgAcceptServer acceptServer=new HttpClientMsgAcceptServer(8088);

   private HttpMsqContainer(){

       try {
           this.acceptServer.start();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    public static HttpMsqContainer getInstance(){
        if(instance == null){
            synchronized(HttpMsqContainer.class){
                if(instance == null){
                    instance = new HttpMsqContainer();
                }

            }

        }
        return instance;
    }

    public String getMsg(String topic){
        Queue<String> queue=msgQueues.get(topic);

        String msg=queue.poll();
        return msg;
    }


}
