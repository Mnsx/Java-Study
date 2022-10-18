package io.nio.one;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 13:21
 * @Description:
 */
public class BufferTest {
    // 对缓冲区常用API测试
    public static void main(String[] args) {
//        test01();
//        test02();
        test03();
    }

    public static void test03() {
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);
        System.out.println(buffer.isDirect());
    }

    public static void test02() {
        // 分配一个缓冲区，将容量设置为10
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 10
        System.out.println(buffer.capacity()); // 10
        System.out.println("----------------------------------");
        // put在缓冲区中添加数据
        String name = "mnsx";
        buffer.put(name.getBytes(StandardCharsets.UTF_8));
        System.out.println(buffer.position()); // 4
        System.out.println(buffer.limit()); // 10
        System.out.println(buffer.capacity()); // 10
        System.out.println("----------------------------------");
        // 清除缓冲区中的数据
        buffer.clear();
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 10
        System.out.println(buffer.capacity()); // 10
        System.out.println("----------------------------------");
        // 获取缓冲区中的数据
        char ch = (char) buffer.get();
        System.out.println(ch);
        // 定义新的缓冲区
        buffer = ByteBuffer.allocate(10);
        name = "mnsx";
        buffer.put(name.getBytes(StandardCharsets.UTF_8));
        buffer.flip();
        // 读取数据
        byte[] b = new byte[2];
        buffer.get(b);
        System.out.println(new String(b));
        System.out.println(buffer.position()); // 2
        System.out.println(buffer.limit()); // 4
        System.out.println(buffer.capacity()); // 10
        System.out.println("----------------------------------");
        // 标记此刻的位置
        buffer.mark();
        byte[] bytes = new byte[2];
        buffer.get(bytes);
        System.out.println(new String(bytes));
        System.out.println(buffer.position()); // 4
        System.out.println(buffer.limit()); // 4
        System.out.println(buffer.capacity()); // 10
        System.out.println("----------------------------------");
        // 回到标记位置
        buffer.reset();
        if (buffer.hasRemaining()) {
            System.out.println(buffer.remaining()); // 2
            System.out.println(buffer.position()); // 2
            System.out.println(buffer.limit()); // 4
            System.out.println(buffer.capacity()); // 10
            System.out.println("----------------------------------");
        }
    }

    public static void test01() {
        // 分配一个缓冲区，将容量设置为10
        ByteBuffer buffer = ByteBuffer.allocate(10);
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 10
        System.out.println(buffer.capacity()); // 10
        System.out.println("----------------------------------");
        // put在缓冲区中添加数据
        String name = "mnsx";
        buffer.put(name.getBytes(StandardCharsets.UTF_8));
        System.out.println(buffer.position()); // 4
        System.out.println(buffer.limit()); // 10
        System.out.println(buffer.capacity()); // 10
        System.out.println("----------------------------------");
        // flip方法将缓冲区设置为当前位置，并将当前位置设置为0
        buffer.flip();
        System.out.println(buffer.position()); // 0
        System.out.println(buffer.limit()); // 4
        System.out.println(buffer.capacity()); // 10
        System.out.println("----------------------------------");
        // get数据的读取
        char ch = (char) buffer.get();
        System.out.println(ch);
        System.out.println(buffer.position()); // 1
        System.out.println(buffer.limit()); // 4
        System.out.println(buffer.capacity()); // 10
        System.out.println("----------------------------------");
    }
}
