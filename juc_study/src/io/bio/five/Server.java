package io.bio.five;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 12:25
 * @Description:
 */
public class Server {
    // 静态集合
    public static List<Socket> allSocket = new ArrayList<>();

    // BIO端口转发思想
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(9999);
            while (true) {
                Socket socket = serverSocket.accept();
                // 把登录客户端socket存储到在线集合中
                allSocket.add(socket);
                // 为所有登录成功的集合分配一个独立线程进行通信
                new Thread(() -> {
                    try {
                        // 从Socket中获取当客户端的输入流
                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        String msg = null;
                        while ((msg = bufferedReader.readLine()) != null) {
                            String message = msg;
                            System.out.println(socket.toString() + "发送消息：" + msg);
                            System.out.println(allSocket);
                            // 服务端接收客户端消息后，推送给所有的在线socket
                            allSocket.stream().filter(socketItem -> socketItem != socket).forEach(socketItem -> {
                                try {
                                    System.out.println("发送消息到客户端——" + socketItem);
                                    PrintWriter ps = new PrintWriter(socketItem.getOutputStream());
                                    ps.println(message);
                                    ps.flush();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    } catch (IOException e) {
                        System.out.println("有人下线了");
                        allSocket.remove(socket);
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
