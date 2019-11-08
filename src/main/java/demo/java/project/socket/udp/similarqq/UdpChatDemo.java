package demo.java.project.socket.udp.similarqq;

import java.io.IOException;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.UnknownHostException;

public class UdpChatDemo {

    public static MulticastSocket socket=null;
    public static InetAddress address=null;

    public static void main(String[] args) {
        setMuticasSocket();
        new Thread(new ReceiveService(socket)).start();
    }

    private static void setMuticasSocket(){
        try {
            //228.9.6.12  网络地址 一定需要时多播地址 否则会报错
            address=InetAddress.getByName("228.9.6.12");
            socket=new MulticastSocket(5556);
            socket.joinGroup(address);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
