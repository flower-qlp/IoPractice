package demo.java.project.socket.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author
 **/
public class SocketManager {

    public static void main(String[] args) {
        SocketManager socketManager = new SocketManager();
        socketManager.doListen();
    }

    private void doListen() {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(8600);
            while (true) {
                Socket socket = serverSocket.accept();
                new Thread(new SSocket(socket)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class SSocket implements Runnable {
        private Socket socket;

        public SSocket(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                String listMsg=dataInputStream.readUTF();
                dataOutputStream.writeUTF("Receive:  " + listMsg + "    \r\n Thx...");
                System.out.println("Receive: "+listMsg);
                listMsg=dataInputStream.readUTF();
                dataOutputStream.writeUTF("Receive Second:  " + listMsg + "    \r\n Thx...");
                System.out.println("Receive Second: "+listMsg);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
