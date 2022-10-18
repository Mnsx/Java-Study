package io.bio.four;

import java.io.*;
import java.net.Socket;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 11:42
 * @Description:
 */
public class Client {
    // 客户端上传任意类型的文件给服务端保存起来
    public static void main(String[] args) {
        DataOutputStream dataOutputStream = null;
        InputStream inputStream = null;
        try {
            // 请求与服务端的Socket连接
            Socket socket = new Socket("127.0.0.1", 8888);
            // 将字节输出流封装成数据输出流
            dataOutputStream = new DataOutputStream(socket.getOutputStream());
            // 先发送上传文件的后缀给服务端
            dataOutputStream.writeUTF(".txt");
            // 把文件数据发送给服务端，进行接收
            inputStream = new FileInputStream("D:\\Warehouse\\Picture\\test.txt");
            byte[] buffer = new byte[1024];
            int len;
            while ((len = inputStream.read(buffer)) > 0) {
                dataOutputStream.write(buffer, 0, len);
            }
            dataOutputStream.flush();
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
