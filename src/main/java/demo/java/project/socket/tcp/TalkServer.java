package demo.java.project.socket.tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author
 **/
public class TalkServer {

    public static void main(String[] args) {


        try {
            ServerSocket serverSocket = new ServerSocket(8500);
            //阻塞等待请求
            //得到请求 产生一个socket
            Socket socket = serverSocket.accept();
            String line;
            //得到输入流
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
            //由系统标准输入设备构造BufferedReader对象
            BufferedReader sys = new BufferedReader(new InputStreamReader(System.in));

            line = sys.readLine();
            while (!line.equals("bye")) {

                printWriter.println(line);
                printWriter.flush();
                System.out.println("server:" + line);
                System.out.println("client:" + reader.readLine());
                line = sys.readLine();
            }
            printWriter.close();
            reader.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
