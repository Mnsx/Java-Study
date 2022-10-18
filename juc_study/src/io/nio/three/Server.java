package io.nio.three;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 15:44
 * @Description:
 */
public class Server {
    public static void main(String[] args) {
        try {
            // 获取通道
            ServerSocketChannel channel = ServerSocketChannel.open();
            // 切换非阻塞模式
            channel.configureBlocking(false);
            // 绑定端口
            channel.bind(new InetSocketAddress(9999));
            // 获取选择器
            Selector selector = Selector.open();
            // 注册选择器
            channel.register(selector, SelectionKey.OP_ACCEPT);
            // 使用selector轮询已经就绪的事件
            while (selector.select() > 0) {
                Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                // 遍历
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    // 判断事件类型
                    if (key.isAcceptable()) {
                        // 获取当前接入的客户端通道
                        SocketChannel socketChannel = channel.accept();
                        // 切换成非阻塞模式
                        socketChannel.configureBlocking(false);
                        // 将通道注册到选择器
                        socketChannel.register(selector, SelectionKey.OP_READ);
                    } else if (key.isReadable()) {
                        // 获取读事件
                        SocketChannel socketChannel = (SocketChannel) key.channel();
                        // 读取数据
                        ByteBuffer buffer = ByteBuffer.allocate(1024);
                        int len = 0;
                        while ((len = socketChannel.read(buffer)) > 0) {
                            buffer.flip();
                            System.out.println(new String(buffer.array(), 0, len));
                            buffer.clear();
                        }
                    }
                    // 处理完毕移除事件
                    iterator.remove();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
