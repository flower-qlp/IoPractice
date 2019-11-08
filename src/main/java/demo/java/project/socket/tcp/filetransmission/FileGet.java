package demo.java.project.socket.tcp.filetransmission;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author happy
 */
public class FileGet extends Thread {

    private String fileDir;

    private int port;

    private boolean stop=false;

    public static void main(String[] args) {
        FileGet fileSend=new FileGet();
        fileSend.fileDir="D:\\work\\download\\";
        fileSend.port=9055;
        fileSend.start();
    }

    @Override
    public void run(){
        System.out.println("*************服务器开启**************");
        Socket socket=null;

        try {
            ServerSocket serverSocket=new ServerSocket(port);
            do{
                System.out.println("创建socket连接");
                socket=serverSocket.accept();

                //获取输入的流数据
                DataInputStream inputStream=new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));

                //本地保存路径，文件名会自动从服务器端继承而来
                byte[] buf=new byte[8192];
                long passLength=0;
                long len=0;
                //文件保存位置
                String file=fileDir+inputStream.readUTF();
                //创建输出流
                DataOutputStream fileOut=new DataOutputStream(
                        new BufferedOutputStream(new FileOutputStream(file))
                );

                len=inputStream.readLong();
                System.out.println("文件长度="+len);
                System.out.println("******开始接受文件！******");

                 while(true){
                     int read=0;
                     if(inputStream!=null){
                         read=inputStream.read(buf);
                     }
                     passLength+=read;
                     if(read==-1){
                         break;
                     }
                     //写出文件
                     fileOut.write(buf,0,read);
                     fileOut.flush();
                 }
                 fileOut.close();
            }while(!stop);
            System.out.println("*********************服务器关闭*********************");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
