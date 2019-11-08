package demo.java.project.socket.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UdpReceive {

    public static void main(String[] args) throws IOException {
        //创建连接容器
        DatagramSocket ds=new DatagramSocket(5556);
        byte[] buf=new byte[1024];
        //创建接受数组和长度
        DatagramPacket dp=new DatagramPacket(buf,buf.length);
        ds.receive(dp);

        //从容器里面获取数据
        InetAddress address=dp.getAddress();
        String send_ip=address.getHostAddress();
        int send_poit=dp.getPort();
        byte[] data=dp.getData();
        int length=dp.getLength();

        String str=new String(data,0,length);

        System.out.println("收到了来自"+send_ip+":"+send_poit+"的数据："+str);
        ds.close();
    }
}
