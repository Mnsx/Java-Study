package base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/30 18:35
 * @Description:
 */
public class AQSDemo {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();

        lock.lock();
        try {

        } finally {
            lock.unlock();
        }
    }
}
