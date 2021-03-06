package org.example.wintermq;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class WinterMQServer
{
    public static void main(String[] args) {
        SpringApplication.run(WinterMQServer.class, args);
    }
}

