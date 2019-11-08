package demo.java.project.socket.udp.similarqq;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.MulticastSocket;

/**@author happy
 * @message about udp send message
 * **/
public class ReceiveService implements Runnable{

    /**群发socket 也是基于udp**/
    MulticastSocket socket=null;

    ReceiveService(MulticastSocket socket){
        this.socket=socket;
    }

    @Override
    public void run() {
         while(true){
             //定义数据包，用于存储数据
             byte[] buffers=new byte[1024];

             DatagramPacket dp=new DatagramPacket(buffers,buffers.length);

             try {
                 socket.receive(dp);
             } catch (IOException e) {
                 e.printStackTrace();
             }

             //获取接收到的消息里面的数据

            System.out.println(dp.getAddress().getHostAddress()+":"+dp.getPort()+
                    "send a message:"+""+new String(dp.getData(),0,dp.getLength()));

         }
    }
}
