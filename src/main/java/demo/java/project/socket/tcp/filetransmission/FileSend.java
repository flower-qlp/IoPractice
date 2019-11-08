package demo.java.project.socket.tcp.filetransmission;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.Socket;

public class FileSend {

    private String filePath;

    private String host;

    private int port;

    public static void main(String[] args) {
        FileSend fu = new FileSend();
        fu.host="10.2.26.129";
        fu.port=9055;
        fu.filePath="D:\\work\\download\\ceshi\\";
        fu.uploadFile("ceshi.jpg");
    }

    public void uploadFile(String fileName){
        Socket socket=null;
        try{
            //开启socket
            socket=new Socket(host,port);
            //io读取本地文件 生成流数据
            File file=new File(filePath+fileName);
            DataInputStream fis=new DataInputStream(
                    new FileInputStream(filePath+fileName));
            System.out.println("文件名："+fileName+" 文件路径："+filePath);

            //创建输出流
            DataOutputStream ps=new DataOutputStream(socket.getOutputStream());
            ps.writeUTF(file.getName());
            ps.flush();
            ps.writeLong((long)file.length());
            ps.flush();

            byte[] buf=new byte[8192];

            while(true){
                 int read=0;
                 if(fis!=null){
                    read=fis.read(buf);
                 }
                 if(read ==-1){
                     break;
                 }
                 //ps写入数据
                 ps.write(buf,0,read);
            }
            ps.flush();
            fis.close();
            ps.close();
            socket.close();
            System.out.println("文件传输完成！");

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
