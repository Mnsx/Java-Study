package base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/19 21:58
 * @Description:
 */
public class LockSupportDemo {
    public static void main(String[] args) {
//        syncWaitNotify();
//        syncAwaitSignal();
        Thread t1 = new Thread(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "\t ---come in");
            LockSupport.park();
            LockSupport.park();
            System.out.println(Thread.currentThread().getName() + "\t ---wake up");
        }, "t1");
        t1.start();

        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---notice");
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
            LockSupport.unpark(t1);
        }, "t2").start();
    }

    private static void syncAwaitSignal() {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();

        new Thread(() -> {
            lock.lock();
            try {
                condition.signal();
                System.out.println(Thread.currentThread().getName() + "\t ---发出通知");
            } finally {
                lock.unlock();
            }
        }, "t2").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "\t ---come in");
                condition.await();
                System.out.println(Thread.currentThread().getName() + "\t ---被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }, "t1").start();
    }

    private static void syncWaitNotify() {
        Object objLock = new Object();

        new Thread(() -> {
            synchronized (objLock) {
                System.out.println(Thread.currentThread().getName() + "\t ---come it");
                objLock.notify();
                System.out.println(Thread.currentThread().getName() + "\t ---wake up");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            synchronized (objLock) {
                try {
                    objLock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "\t ---notify");
            }
        },"t2").start();
    }
}
