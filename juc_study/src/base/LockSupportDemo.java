package base;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/19 21:58
 * @Description:
 */
public class LockSupportDemo {
    public static void main(String[] args) {
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
