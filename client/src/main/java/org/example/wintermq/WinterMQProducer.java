package org.example.wintermq;


/**
 * 新建与服务器间的连接
 */
public interface WinterMQProducer {

    public void send(String msg);
}
