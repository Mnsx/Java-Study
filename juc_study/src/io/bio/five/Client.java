package io.bio.five;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 11:13
 * @Description:
 */
public class Client {
    public static void main(String[] args) {
        try {
            // 请求与服务端的Socket连接
            Socket socket = new Socket("127.0.0.1", 9999);
            // 得到一个打印流
            PrintStream printStream = new PrintStream(socket.getOutputStream());
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            new Thread(() -> {
                while (true) {
                    try {
                        String msg = null;
                        if ((msg = bufferedReader.readLine()) != null) {
                            System.out.println("收到消息：" + msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
            // 使用循环不断发送消息
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("请说: ");
                String msg = scanner.nextLine();
                printStream.println(msg);
                printStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
