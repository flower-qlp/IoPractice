package demo.java.project.utils;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @author happy
 */
public class BaseUtil {

    public void getHostAdress(){
        //获取的是本地的IP地址
        InetAddress address = null;
        try {
            address = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String hostAddress = address.getHostAddress();

        System.out.println("address-->"+address);
        System.out.println("hostAddress-->"+hostAddress);
    }
}
