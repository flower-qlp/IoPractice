package demo.java.project.socket.tcp;

import java.io.*;
import java.net.Socket;

/**
 * @author
 */
public class SocketClient {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 8600);

            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream doc = new DataOutputStream(outputStream);

            DataInputStream input = new DataInputStream(socket.getInputStream());

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            String temp = bufferedReader.readLine();

            while (!temp.equals("wq")) {
                doc.writeUTF(temp);
                String res = input.readUTF();
                System.out.println(res);
                doc.writeUTF("bye");
                res = input.readUTF();
                System.out.println(res);
            }
            bufferedReader.close();
            doc.close();
            input.close();
            outputStream.close();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
