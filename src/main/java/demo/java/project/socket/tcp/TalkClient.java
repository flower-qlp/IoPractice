package demo.java.project.socket.tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author
 **/
public class TalkClient {


    public static void main(String[] args) {

        try {
            Socket socket = new Socket("127.0.0.1", 8500);

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            PrintWriter os = new PrintWriter(socket.getOutputStream());
            BufferedReader is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String line = reader.readLine();
            while (!line.equals("bye")) {
                os.println(line);
                os.flush();
                System.out.println("client:" + line);
                System.out.println("server:" + is.readLine());
                line = reader.readLine();
            }

            os.close();
            is.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
