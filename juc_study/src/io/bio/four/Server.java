package io.bio.four;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 11:42
 * @Description:
 */
public class Server {
    // 接收客户端任意类型的文件，并保存到服务端
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                // 交给一个独立线程来处理这个客户端通信的Socket
                new Thread(() -> {
                    try {
                        // 得到一个输入流读客户端发送的数据
                        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                        // 读取客户端发送的文件类型
                        String suffix = dataInputStream.readUTF();
                        System.out.println("接收到文件类型" + suffix);
                        // 定义一个字节输出管道负责把客户端输入的数据写出去
                        OutputStream outputStream = new FileOutputStream("D:\\WorkSpace\\JUC-Study\\juc_study\\src" + UUID.randomUUID().toString() + suffix);
                        // 从数据输入流中读入文件数据，写出到字节输出流中
                        byte[] buffer = new byte[1024];
                        int len;
                        while ((len = dataInputStream.read(buffer)) > 0) {
                            outputStream.write(buffer, 0, len);
                        }
                        outputStream.close();
                        dataInputStream.close();
                        System.out.println("接收文件保存成功");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
