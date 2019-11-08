package demo.java.project.io;

import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileUtil {

    public void RandomAccessFileReder(String readPath,String modeType) throws IOException{
          RandomAccessFile randomAccessFile=new RandomAccessFile(readPath,modeType);

          //指针
          randomAccessFile.seek(0);
           /**非文本格式，读取出来是乱码**/
          //System.out.println("randomAccessFile="+randomAccessFile.readLine());

          Person person=new Person();
          person.read(randomAccessFile);
          System.out.println("name="+person.getName()+" hobbies="+person.getHobbies());
          randomAccessFile.close();
    }

    public void RandomAccessFileWrite(String writePath,String modeType) throws IOException{
           RandomAccessFile randomAccessFile=new RandomAccessFile(writePath,modeType);
           Person person=new Person("张三",21,true,
                   "football，basketball,tennis,run","大梦天国无忧乡问心村");
           person.write(randomAccessFile);
           randomAccessFile.close();
    }

    public static void main(String[] args) {
        try {
           // new RandomAccessFileUtil().RandomAccessFileWrite("D:\\work\\RandomAccessFile.txt","rw");
            new RandomAccessFileUtil().RandomAccessFileReder("D:\\work\\RandomAccessFile.txt","r");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    class Person{
        private String name;
        private Integer age;
        private boolean sex;
        private String hobbies;
        private String address;

        public Person(String name,Integer age,boolean sex,String hobbies,String address){
            this.name=name;
            this.age=age;
            this.sex=sex;
            this.hobbies=hobbies;
            this.address=address;
        }
        public Person(){}

        public void write(RandomAccessFile randomAccessFile) throws IOException{
            randomAccessFile.writeUTF(this.name);
            randomAccessFile.write(this.age);
            randomAccessFile.writeBoolean(this.sex);
            randomAccessFile.writeUTF(this.hobbies);
            randomAccessFile.writeUTF(this.address);
        }

        public void read(RandomAccessFile randomAccessFile) throws IOException{
            this.name=randomAccessFile.readUTF();
            this.age=randomAccessFile.readInt();
            this.sex=randomAccessFile.readBoolean();
            this.hobbies=randomAccessFile.readUTF();
            this.address=randomAccessFile.readUTF();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        public boolean isSex() {
            return sex;
        }

        public void setSex(boolean sex) {
            this.sex = sex;
        }

        public String getHobbies() {
            return hobbies;
        }

        public void setHobbies(String hobbies) {
            this.hobbies = hobbies;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }
}
