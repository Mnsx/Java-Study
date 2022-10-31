package base;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/31 15:52
 * @Description:
 */
public class LockDownGradingDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();

/*        writeLock.lock();
        System.out.println("---写入");
        writeLock.unlock();

        readLock.lock();
        System.out.println("---读取");
        readLock.unlock();*/


        readLock.lock();
        System.out.println("---读入");
        writeLock.lock();
        System.out.println("---写入");
        writeLock.unlock();
        readLock.unlock();
    }
}
