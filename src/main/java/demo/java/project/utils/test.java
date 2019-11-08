package demo.java.project.utils;

import demo.java.project.demo.Eneity.DemoTest;
import demo.java.project.demo.Eneity.User;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;


public class test {

    public static void main(String[] args) {

        DemoTest demoTest=new DemoTest();
        demoTest.setId(10);

        User user=new User();

        BeanUtils.copyProperties(demoTest,user);

        System.out.println("id---->"+user.getId());

        List<String> list=new ArrayList<String>();
        list.add("1");
        list.add("2");
        list.add("13");
        list.add("4");
        list.add("15");
        list.add("6");
        list.add("17");
        list.add("8");
        list.forEach(x->{
            if (x.contains("1")){
                System.out.println("包含");
            }else{
                System.out.println("不包含");
            }
        });
    }



}
