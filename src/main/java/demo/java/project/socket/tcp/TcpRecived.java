package demo.java.project.socket.tcp;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpRecived {

    private DataInputStream dataInputStream;

    public void startServer(){
        Socket socket=null;
        try {
            //创建socket
            socket=new ServerSocket(1002).accept();

            //创建输入流
            dataInputStream=new DataInputStream(socket.getInputStream());
            getMessageFromClient();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getMessageFromClient(){
        try {
            //创建输入容器
            int length=dataInputStream.read();
            byte[] body=new byte[length];
            //获取流数据
            dataInputStream.read(body);
            String message=new String(body);
            System.out.println("客户端说："+message);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new TcpRecived().startServer();
    }
}
