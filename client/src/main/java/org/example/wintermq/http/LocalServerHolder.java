package org.example.wintermq.http;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 接收消息
 */
public class LocalServerHolder {


    private volatile static LocalServerHolder instance=null;

    Map<String, Queue<String>> msgQueues=new HashMap<>();

 //   HttpClientMsgAcceptServer acceptServer=new HttpClientMsgAcceptServer(8088);

   private LocalServerHolder(){

       try {
         //  this.acceptServer.start();
           new LocalServerThread(8088).start();
       } catch (Exception e) {
           e.printStackTrace();
       }
   }

    public static LocalServerHolder getInstance(){
        if(instance == null){
            synchronized(LocalServerHolder.class){
                if(instance == null){
                    instance = new LocalServerHolder();
                }

            }

        }
        return instance;
    }

    public void putMsg(String topic,String msg){
       HttpMsgContaner.putData(topic,msg);
//        Queue<String> queue=msgQueues.get(topic);
//        if (queue==null){
//            queue=new ConcurrentLinkedQueue<>();
//            msgQueues.put(topic,queue);
//        }
//        queue.add(msg);
    }
    public String getMsg(String topic){
       String msg=HttpMsgContaner.getData(topic);
        return msg;
    }


}
