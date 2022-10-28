package base;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/28 22:18
 * @Description:
 */
class Ticket {
    private int number = 50;

    Object lockObject = new Object();

    public void sale() {
        synchronized (lockObject) {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "\t" + (number--) + "\t" + number);
            }
        }
    }
}
public class LockSaleTicketDemo {
    public static void main(String[] args) {
//        noLock();
        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Object o = new Object();

/*        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }*/

        new Thread(() -> {
            synchronized (o) {
                System.out.println(ClassLayout.parseInstance(o).toPrintable());
            }
        }, "t1").start();
    }

    private static void noLock() {
        Ticket ticket = new Ticket();

        new Thread(() -> {
            for (int i = 0; i < 55; ++i) {
                ticket.sale();
            }
        }, "a").start();

        new Thread(() -> {
            for (int i = 0; i < 55; ++i) {
                ticket.sale();
            }
        }, "b").start();

        new Thread(() -> {
            for (int i = 0; i < 55; ++i) {
                ticket.sale();
            }
        }, "c").start();
    }
}
