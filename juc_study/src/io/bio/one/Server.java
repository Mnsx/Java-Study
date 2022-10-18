package io.bio.one;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 10:24
 * @Description:
 */
public class Server {
    public static void main(String[] args) {
        try {
            // 定义一个ServerSocket对象进行服务端的端口注册
            ServerSocket ss = new ServerSocket(9999);
            // 监听客户端的Socket连接请求
            Socket socket = ss.accept();
            // 从Socket管段中得到一个字节输入流对象
            InputStream is = socket.getInputStream();
            // 把字节输入流包装成一个缓冲输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String msg = null;
            while ((msg = br.readLine()) != null) {
                System.out.println("服务端收到：" + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
