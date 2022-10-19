package base;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/19 19:54
 * @Description:
 */
public class InterruptDemo {

//     static volatile boolean isStop = false;
//     static AtomicBoolean isStop = new AtomicBoolean(false);

    public static void main(String[] args) {
//        interrupt1();
//        interrupt2();
        Thread t1 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true，线程停止");
                    break;
                }
                System.out.println("---hello volatile");
            }
        }, "t1");
        t1.start();

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(t1::interrupt, "t2").start();
    }

    /*private static void interrupt2()() {
        new Thread(() -> {
            while (true) {
                if (isStop.get()) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true，线程停止");
                    break;
                }
                System.out.println("---hello volatile");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            isStop.set(true);
        }, "t2").start();
    }*/

    /*private static void interrupt1() {
        new Thread(() -> {
            while (true) {
                if (isStop) {
                    System.out.println(Thread.currentThread().getName() + "\t isStop被修改为true，线程停止");
                    break;
                }
                System.out.println("---hello volatile");
            }
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            isStop = true;
        }, "t2").start();
    }*/
}
