package demo.java.project.demo.Utils;

import demo.java.project.demo.Eneity.DemoTest;

import java.util.ArrayList;
import java.util.List;

public class Math {
    public static void main(String[] args) {
        double a=(double) java.lang.Math.round(110.073236/50 * 10000) / 10000*100;
        Double b=Double.valueOf(((int)(110.073236/253*10000))/100);
        System.out.println("a==>"+a+" b==>"+b);

        List<DemoTest> demoTests=new ArrayList<DemoTest>();
        for(int i=0;i<5;i++){
            DemoTest demo=new DemoTest();
            demo.setId(i);
            demo.setName("dasdad");

            if(!demoTests.contains(demo)){
                demoTests.add(demo);
            }
        }
        System.out.print("------------demo------"+demoTests.size());
        for(int i=0;i<6;i++){
            DemoTest demo=new DemoTest();
            demo.setId(i);
            demo.setName("dasdad");

            if(!demoTests.contains(demo)){
                demoTests.add(demo);
            }
        }
        System.out.print("------------demo------"+demoTests.size());
    }
}
