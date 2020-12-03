package org.example.wintermq.http;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class HttpMsgContaner {

    static Map<String,Queue<String>> topicData=new HashMap<>();
 //  static Queue<String> queue = new LinkedList<String>();

   public static void putData(String topic,String msg){
       Queue<String>  data=  topicData.get(topic);
       if (data==null){
           data=new LinkedList<>();
       }
       data.offer(msg);
       topicData.put(topic,data);
   }

   public static String getData(String topic){
       Queue<String>  data=  topicData.get(topic);
      if (data==null) return null;
      String msg=data.poll();
      return msg;
   }

}
