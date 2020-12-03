package org.example.wintermq;

import java.util.Date;

public class WinterMQHttpProducerDemo {
    public static void main(String[] args) {
        WinterMQHttpProducer producer=new WinterMQHttpProducer("127.0.0.1",18081,"test");
        while(true){
            producer.send("hello"+new Date());
            System.out.println("hello"+new Date());

            try {
                Thread.sleep(2*1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
