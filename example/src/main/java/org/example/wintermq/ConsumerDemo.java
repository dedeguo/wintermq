package org.example.wintermq;

import java.util.Date;

public class ConsumerDemo {
    public static void main(String[] args) {
        WinterMQHttpConsumer winterMQHttpConsumer=new WinterMQHttpConsumer("127.0.0.1",8081,"test");
        while (true){

          String msg=  winterMQHttpConsumer.consumeMsg();
            System.out.println(new Date() +"  "+msg);
        }
    }
}
