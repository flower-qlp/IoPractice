package demo.java.project.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 使用：先启动 receive 在点击发送会受到数据
 * **/
public class UdpSendSecond {

    public static void main(String[] args) throws IOException {
         //创建发送端
        DatagramSocket ds=new DatagramSocket();
        //创建数据
        String s="I am second !";
        byte[] data=s.getBytes();
        int length=data.length;
        InetAddress address=InetAddress.getByName("10.41.28.20");
        int port=5556;
        //打包数据  内容  长度 地址  端口号
        DatagramPacket dp=new DatagramPacket(data,length,address,port);

        ds.send(dp);
        ds.close();

    }

}
