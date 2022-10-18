package io.bio.two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 11:02
 * @Description:
 */
public class Server {
    // 同时解说多个客户端Socket通信请求
    // 收到客户端Socket请求对象后交给一个独立线程处理客户端数据请求
    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newFixedThreadPool(6);
        try {
            // 注册端口
            ServerSocket serverSocket = new ServerSocket(9999);
            // 定义一个死循环，不断接收请求
            while (true) {
                Socket socket = serverSocket.accept();
                // 创建一个独立的线程来处理这个通信需求
                threadPool.submit(() -> {
                    try {
                        // 从Socket对象中得到一个字节输入流
                        InputStream inputStream = socket.getInputStream();
                        // 使用缓冲字字符输入流包装字节输入流
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                        String msg = null;
                        while ((msg = bufferedReader.readLine()) != null) {
                            System.out.println(msg);
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
