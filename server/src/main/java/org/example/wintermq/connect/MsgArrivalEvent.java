package org.example.wintermq.connect;

import org.springframework.context.ApplicationEvent;

public class MsgArrivalEvent extends ApplicationEvent {

    private String msg;

    private String topic;

    public MsgArrivalEvent(String topic,String msg) {
        super(topic);
        this.topic=topic;
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}
