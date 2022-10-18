package io.bio.one;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 10:24
 * @Description:
 */
public class Client {
    public static void main(String[] args) throws IOException {
        // 创建一个Socket对象，请求服务端连接
        Socket socket = new Socket("127.0.0.1", 9999);
        // 从Socket对象获取一个字节输出流
        OutputStream os = socket.getOutputStream();
        // 把字节输出流包装成一个打印流
        PrintStream ps = new PrintStream(os);
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("请说: ");
            String msg = scanner.nextLine();
            ps.println(msg);
            ps.flush();
        }
    }
}
