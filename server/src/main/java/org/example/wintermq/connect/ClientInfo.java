package org.example.wintermq.connect;


import lombok.Data;

@Data
public class ClientInfo {


    private String ip;
    private Integer port;

    public ClientInfo(){

    }

    public ClientInfo(String ip,Integer port){
        this.port=port;
        this.ip=ip;
    }
    public String getServer(){
        return "http://"+ip+":"+port+"/";
    }

}
