package org.example.wintermq.connect.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ProducerReqDTO implements Serializable {

    String msg;

    String topic;

}
