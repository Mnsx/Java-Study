package base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.StampedLock;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/31 18:33
 * @Description:
 */
public class StampedLockDemo {
    static int number = 37;
    static StampedLock stampedLock = new StampedLock();

    public void write() {
        long stamp = stampedLock.writeLock();
        System.out.println(Thread.currentThread().getName() + "\t" + "---写线程准备");
        try {
            number = number + 13;
        } finally {
            stampedLock.unlockWrite(stamp);
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "---写线程结束修改");
    }

    public void read() {
        long stamp = stampedLock.readLock();
        System.out.println(Thread.currentThread().getName() + "\t" + "---读线程准备");
        try {
            TimeUnit.MILLISECONDS.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            int result = number;
            System.out.println(Thread.currentThread().getName() + "\t" + "---读线程完成获取" + result);
            System.out.println("写线程没有修改成功");
        } finally {
            stampedLock.unlockRead(stamp);
        }
    }

    public void optimisticRead() {
        long stamp = stampedLock.tryOptimisticRead();
        int result = number;
        System.out.println(Thread.currentThread().getName() + "\t" + "---读取数据" + stampedLock.validate(stamp));
        for (int i = 0;i < 4; ++i) {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t" + "---读取数据" + i + "s\t" + stampedLock.validate(stamp));
        }
        if (!stampedLock.validate(stamp)) {
            System.out.println("error");
            long stamp2 = stampedLock.readLock();
            try {
                System.out.println("锁升级——》悲观");
                result = number;
                System.out.println(result);
            } finally {
                stampedLock.unlockRead(stamp2);
            }
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "---读取数据成功" + "\t" + stampedLock.validate(stamp));
    }

    public static void main(String[] args) {
        StampedLockDemo stampedLockDemo = new StampedLockDemo();

        /*new Thread(stampedLockDemo::read, "read").start();

        new Thread(stampedLockDemo::write, "read").start();*/

        new Thread(stampedLockDemo::optimisticRead, "read").start();

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t写线程进入");
            stampedLockDemo.write();
        }, "write").start();
    }
}
