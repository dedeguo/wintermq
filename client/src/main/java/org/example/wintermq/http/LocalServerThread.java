package org.example.wintermq.http;

public class LocalServerThread extends Thread {

    int prot;

    public LocalServerThread(int port){
        this.prot=port;
    }


    @Override
    public void run() {
        try {
            new HttpClientMsgAcceptServer(prot).start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
