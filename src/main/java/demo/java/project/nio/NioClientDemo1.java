package demo.java.project.nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutionException;

/**
 * @author happy
 * 客户端
 **/
public class NioClientDemo1 {

    private static final String host = "127.0.0.1";

    private static final int port = 8082;

    private Selector selector;

    public static void main(String[] args) {
        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                NioClientDemo1 demo = new NioClientDemo1();
                demo.connect(host, port);
                demo.listen();
            }).start();
        }
    }

    public void connect(String host, int port) {
        try {
            SocketChannel socketChannel = SocketChannel.open();
            socketChannel.configureBlocking(false);
            this.selector = Selector.open();
            socketChannel.register(selector, SelectionKey.OP_CONNECT);
            socketChannel.connect(new InetSocketAddress(host, port));
            System.out.println("connect连接信息：-->"+Thread.currentThread().getName()+"--时间:-->"+System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listen() {
        while (true) {
            try {
                int events = selector.select();
                if (events > 0) {
                    Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
                    while (keys.hasNext()) {
                        SelectionKey key = keys.next();
                        keys.remove();
                        /**连接事件**/
                        if (key.isConnectable()) {
                            SocketChannel socketChannel = (SocketChannel) key.channel();
                            if (socketChannel.isConnectionPending()) {
                                socketChannel.finishConnect();
                            }
                            socketChannel.configureBlocking(false);
                            socketChannel.register(selector, SelectionKey.OP_READ);
                            socketChannel.write(ByteBuffer.wrap(("Here is " + Thread.currentThread().getName()).getBytes()));
                            System.out.println("isConnectable连接信息：-->"+Thread.currentThread().getName()+"--时间:-->"+System.currentTimeMillis());
                        } else if (key.isReadable()) {
                            //读就绪
                            SocketChannel sc = (SocketChannel) key.channel();
                            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                            sc.read(byteBuffer);
                            byteBuffer.flip();
                            System.out.println("isReadable收到服务器信息:" + new String(byteBuffer.array())+"--时间--"+System.currentTimeMillis());
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
