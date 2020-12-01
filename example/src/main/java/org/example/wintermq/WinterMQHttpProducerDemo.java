package org.example.wintermq;

public class WinterMQHttpProducerDemo {
    public static void main(String[] args) {
        WinterMQHttpProducer producer=new WinterMQHttpProducer("127.0.0.1",8081,"test");
        while(true)
        producer.send("hello");
    }
}
