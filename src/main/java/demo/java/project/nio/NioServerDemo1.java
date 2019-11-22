package demo.java.project.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 简单的测试demo
 *
 * @author happy
 * <p>
 * NIO服务端
 **/
public class NioServerDemo1 {

    private int port;
    private Selector selector;
    private ExecutorService service = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {
        new NioServerDemo1(8082).start();
    }

    public NioServerDemo1(int port) {
        this.port = port;
    }

    /**
     * 执行一次
     **/
    public void init() {
        ServerSocketChannel ssc = null;
        try {
            ssc = ServerSocketChannel.open();
            //设置通道阻塞模式 false为非阻塞模式
            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(port));
            selector = Selector.open();
            /**绑定selector和ServerSocketChannel**/
            //SelectionKey.OP_ACCEPT  接收事件标识    服务端可以接受连接了
            ssc.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("NioServer started ......");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() {
        this.init();
        while (true) {
            try {
                //选择已经准备就绪的通道
                int events = selector.select();
                if (events > 0) {
                    Iterator<SelectionKey> selectionKeys = selector.selectedKeys().iterator();
                    while (selectionKeys.hasNext()) {
                        SelectionKey key = selectionKeys.next();
                        selectionKeys.remove();
                        //是否接受就绪
                        if (key.isAcceptable()) {
                            this.accept(key);
                        } else {
                            service.submit(new NIOServerHandler(key));
                        }
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void accept(SelectionKey key) {
        try {
            ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
            SocketChannel sc = ssc.accept();
            sc.configureBlocking(false);
            //接受连接 改编为读就绪状态
            sc.register(selector, SelectionKey.OP_READ);
            System.out.println("accept a client : " + sc.socket().getInetAddress().getHostName());
            System.out.println("accept连接信息--时间:-->" + System.currentTimeMillis());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class NIOServerHandler implements Runnable {
        private SelectionKey skey;

        private NIOServerHandler(SelectionKey key) {
            this.skey = key;
        }

        @Override
        public void run() {
            try {
                SocketChannel socketChannel = (SocketChannel) skey.channel();
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                socketChannel.read(byteBuffer);
                /**
                 * buffer 中flip()
                 * 在buffer中存在三个字段 capital 缓冲区容量  position 指针 当前写入/读取位置 limit 读：标识当前buffer中的最大读取容量,写:标识最大可以写容量
                 * 改变读写模式 修改这个三个参数
                 * **/
                byteBuffer.flip();
                System.out.println("收到客户端" + socketChannel.socket().getInetAddress().getHostName() + "的数据：" + new String(byteBuffer.array()));
                ByteBuffer outBuffer = ByteBuffer.wrap(byteBuffer.array());
                socketChannel.write(outBuffer);
                skey.cancel();
                System.out.println("run连接信息--时间:-->" + System.currentTimeMillis());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
