package org.example.wintermq.connect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientHolder {

    private static Map<String, List<ClientInfo>> holder= new HashMap<>();

    public static void addClient(String topic, ClientInfo clientInfo){
        List<ClientInfo> clientInfos=holder.get(topic);
        if (clientInfos==null || clientInfos.size()==0){
            clientInfos=new ArrayList<>();
        }
        clientInfos.add(clientInfo);
        holder.put(topic,clientInfos);
    }

    public static  List<ClientInfo> get(String topic){
        List<ClientInfo> clientInfos=holder.get(topic);
        return clientInfos;
    }

    public static  void addTopicList(String topic,List<ClientInfo> list){
        holder.put(topic,list);
        return ;
    }
}
