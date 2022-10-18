package io.bio.three;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 11:24
 * @Description:
 */
public class Server {
    // 开发实现伪异步通信架构
    public static void main(String[] args) {
        try {
            // 注册端口
            ServerSocket serverSocket = new ServerSocket(9999);
            // 定义一个循环接收客户端的Socket连接请求
            // 初始化线程池对象
            HandlerSocketServerPool pool = new HandlerSocketServerPool(3, 10);
            while (true) {
                Socket socket = serverSocket.accept();
                // 把socket交给线程池进行处理
                // 将Socket封装成一个任务对象传给pool
                pool.execute(() -> {
                    // 从Socket管段中得到一个字节输入流对象
                    InputStream is = null;
                    try {
                        is = socket.getInputStream();
                        // 把字节输入流包装成一个缓冲输入流
                        BufferedReader br = new BufferedReader(new InputStreamReader(is));
                        String msg = null;
                        while ((msg = br.readLine()) != null) {
                            System.out.println("服务端收到：" + msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
