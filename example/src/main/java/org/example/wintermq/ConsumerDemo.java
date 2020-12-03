package org.example.wintermq;

import org.example.wintermq.http.HttpClientMsgAcceptServer;

import java.util.Date;

public class ConsumerDemo {
    public static void main(String[] args) {
        WinterMQHttpConsumer winterMQHttpConsumer=new WinterMQHttpConsumer("127.0.0.1",18081,"test");

        while(true){
          //  System.out.println("test!!");
            try {
                Thread.sleep(4*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            String msg= winterMQHttpConsumer.consumeMsg();
            System.out.println("ConsumerDemo"+new Date() +"  "+msg);
        }
        //线程阻塞 到不了这里来

//        HttpClientMsgAcceptServer acceptServer=new HttpClientMsgAcceptServer(8088);
//        try {
//            acceptServer.start();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        while(true){
//            String msg= winterMQHttpConsumer.consumeMsg();
//            System.out.println("ConsumerDemo"+new Date() +"  "+msg);
//        }


    }
}
