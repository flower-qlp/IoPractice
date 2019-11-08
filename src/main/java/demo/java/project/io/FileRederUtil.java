package demo.java.project.io;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileRederUtil {

    public void FileReader(String readerPath,String writePath) throws IOException {
        //读取中文的话会出现乱码
        FileReader fileReader=new FileReader(readerPath);
        char[] buffer=new char[512];
        StringBuffer stringBuffer=new StringBuffer();
        while(fileReader.read(buffer)>0){
            stringBuffer.append(buffer);
        }
        System.out.println(stringBuffer.toString());
        FileWriter fileWriter=new FileWriter(writePath);
        fileWriter.write(stringBuffer.toString().trim());
        fileWriter.close();
        System.out.println("写入成功");
    }

    public static void main(String[] args) {
        try {
            new FileRederUtil().FileReader("D:\\work\\reader.txt","D:\\work\\write.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
