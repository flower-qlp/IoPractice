package demo.java.project.io;

import java.io.*;

public class BufferRederUtil {


    public void Bufferreder(String rederpath,String writerPath) throws IOException {
        /** 存在乱码
         * 1.创建reder对象
         * 2.创建buffer字符流
         * 3.新建char[] 通过reder方法读取数据
         * 4.关闭字符流
         **/
        InputStreamReader inputStreamReader=new InputStreamReader(new FileInputStream(new File(rederpath)),"UTF-8");
        BufferedReader bufferedReader=new BufferedReader(inputStreamReader);
        char []buffer=new char[2048];
        int length=bufferedReader.read(buffer);
        bufferedReader.close();
        inputStreamReader.close();

        /**
         1.创建reder对象
         * 2.创建buffer字符流
         * 3.通过write方法读取数据
         * 4.关闭字符流
         * true  是为了每次写入时候末尾追加
         **/
        FileWriter fileWriter=new FileWriter(new File(writerPath),true);

        BufferedWriter bufferedWriter=new BufferedWriter(fileWriter);

        System.out.println(new String(buffer,0,length));
        bufferedWriter.write(new String(buffer,0,length));

        //需要关闭 否则写入不了
        bufferedWriter.close();
        fileWriter.close();
    }


    public void BuffreedReder(String readPath,String writePath) throws IOException{
        File file=new File(writePath);
        FileOutputStream fileOutputStream=new FileOutputStream(file);
        BufferedOutputStream bufferedOutputStream=new BufferedOutputStream(fileOutputStream);
        bufferedOutputStream.write("开始写入数据".getBytes());
        bufferedOutputStream.flush();

        FileInputStream fileInputStream=new FileInputStream(new File(readPath));
        BufferedInputStream bufferedInputStream=new BufferedInputStream(fileInputStream);
        BufferedReader reader=new BufferedReader(new InputStreamReader(bufferedInputStream));

        StringBuffer result=new StringBuffer();
        while(reader.ready()){
            result.append((char)reader.read());
        }
        System.out.println(result.toString());
        reader.close();

        bufferedOutputStream.write(result.toString().getBytes());
        bufferedOutputStream.close();
    }



    public static void main(String[] args) {
        try {
          //  new BufferRederUtil().Bufferreder("D:\\work\\reader.txt","D:\\work\\bufferwrite.txt");
            new BufferRederUtil().BuffreedReder("D:\\work\\reader.txt","D:\\work\\BuffreedReder.txt");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
