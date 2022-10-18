package io.nio.two;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 14:49
 * @Description:
 */
public class ChannelTest {
    public static void main(String[] args) {
//       test01();
//        test02();
//        test03();
//        test04();
        test05();
    }

    public static void test05() {
        try {
            // 定义字节输入管道
            FileInputStream fileInputStream = new FileInputStream("D:\\WorkSpace\\JUC-Study\\juc_study\\src\\io\\data01.txt");
            FileChannel inChannel = fileInputStream.getChannel();
            // 定义字节输出管道
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\WorkSpace\\JUC-Study\\juc_study\\src\\data01.txt");
            FileChannel outChannel = fileOutputStream.getChannel();
            // 复制数据
            outChannel.transferFrom(inChannel, 0, inChannel.size());
            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test04() {
        try {
            // 定义字节输入管道
            FileInputStream fileInputStream = new FileInputStream("D:\\WorkSpace\\JUC-Study\\juc_study\\src\\io\\data01.txt");
            FileChannel inChannel = fileInputStream.getChannel();
            // 定义字节输出管道
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\WorkSpace\\JUC-Study\\juc_study\\src\\data01.txt");
            FileChannel outChannel = fileOutputStream.getChannel();
            // 定义多个缓冲区，数据分散
            ByteBuffer buffer1 = ByteBuffer.allocate(4);
            ByteBuffer buffer2 = ByteBuffer.allocate(1024);
            ByteBuffer[] byteBuffers = new ByteBuffer[]{buffer1, buffer2};
            // 从通道中读取数据到各个缓冲区
            inChannel.read(byteBuffers);
            // 从缓冲区中查询是否存在数据
            Arrays.stream(byteBuffers).forEach((item) -> {
                item.flip();
                System.out.println(new String(item.array(), 0, item.remaining()));
            });
            // 聚集写入
            outChannel.write(byteBuffers);
            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void test03() {
        File srcFile = new File("D:\\WorkSpace\\JUC-Study\\juc_study\\src\\io\\data01.txt");
        try {
            // 得到一个字节输入流
            FileInputStream fileInputStream = new FileInputStream(srcFile);
            // 得到一个字节输出流
            FileOutputStream fileOutputStream = new FileOutputStream("D:\\WorkSpace\\JUC-Study\\juc_study\\src\\data01.txt");
            // 获取文件通道
            FileChannel inChannel = fileInputStream.getChannel();
            FileChannel outChannel = fileOutputStream.getChannel();
            // 分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            while (true) {
                // 清空缓冲区再写入数据
                buffer.clear();
                // 开启读取一次数据
                int flag = inChannel.read(buffer);
                if (flag == -1) {
                    break;
                }
                // 切换可读模式
                buffer.flip();
                // 将数据写出
                outChannel.write(buffer);
            }
            inChannel.close();
            outChannel.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void test02() {
        try {
            // 定义一个文件字节输入流
            FileInputStream fileInputStream = new FileInputStream("D:\\WorkSpace\\JUC-Study\\juc_study\\src\\io\\data01.txt");
            // 得到输入流的文件通道
            FileChannel channel = fileInputStream.getChannel();
            // 定义一个缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // 读取数据到缓冲区
            channel.read(buffer);
            // 读取缓冲区中的数据并输出
            buffer.flip();
            String str = new String(buffer.array(), 0, buffer.remaining());
            System.out.println(str);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void test01() {
        // 得到一个字节输出流，通向目标文件
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream("D:\\WorkSpace\\JUC-Study\\juc_study\\src\\io\\data01.txt");
            // 得到字节输出流的通道
            FileChannel channel = outputStream.getChannel();
            // 分配缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put("hello nio".getBytes(StandardCharsets.UTF_8));
            // 切换写出模式
            buffer.flip();
            channel.write(buffer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
