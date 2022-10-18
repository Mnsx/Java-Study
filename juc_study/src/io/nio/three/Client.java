package io.nio.three;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Scanner;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 15:56
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        try {
            // 获取通道
            SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 9999));
            // 切换非阻塞模式
            socketChannel.configureBlocking(false);
            // 分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 发送数据给服务端
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请说: ");
                String msg = scanner.nextLine();
                buffer.put(("[" + LocalDateTime.now() + "]" + msg).getBytes(StandardCharsets.UTF_8));
                buffer.flip();
                socketChannel.write(buffer);
                buffer.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
