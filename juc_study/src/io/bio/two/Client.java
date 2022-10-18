package io.bio.two;

import java.io.IOException;
import java.io.PrintStream;
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
