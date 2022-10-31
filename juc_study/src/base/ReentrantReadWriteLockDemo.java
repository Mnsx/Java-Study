package base;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/31 15:20
 * @Description:
 */
class MyResource {
    Map<String, String> map = new HashMap<>();
    Lock lock = new ReentrantLock();
    ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public void write(String key, String value) {
        rwLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在写入");
            map.put(key, value);
            try {
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "完成写入");
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public void read(String key) {
        rwLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + "正在读取");
            String result = map.get(key);
            try {
                TimeUnit.MILLISECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "读取完成" + result);
        } finally {
            rwLock.readLock().unlock();
        }
    }
}
public class ReentrantReadWriteLockDemo {
    public static void main(String[] args) {
        MyResource myResource = new MyResource();

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI + "", finalI + "");
            }, String.valueOf(i)).start();
        }

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.read(finalI + "");
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < 3; i++) {
            int finalI = i;
            new Thread(() -> {
                myResource.write(finalI + "", finalI + "");
            }, "new" + i).start();
        }
    }
}
