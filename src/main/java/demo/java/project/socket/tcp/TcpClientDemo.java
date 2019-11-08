package demo.java.project.socket.tcp;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author happy
 * @message tcp exchange message
 */
public class TcpClientDemo {

    public static void main(String[] args) throws IOException {
        String clientIp="10.2.26.129";
        int port=1002;
        Socket socket=new Socket(clientIp,port);
        //传输内容
        String context="this is an example for tcp client!";
        //转字符
        byte [] buf=context.getBytes("GBK");
        //获取输出流
        OutputStream io=socket.getOutputStream();
        io.write(buf);
        socket.close();
    }
}
