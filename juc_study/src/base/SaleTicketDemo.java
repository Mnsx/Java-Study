package base;

import sun.security.krb5.internal.Ticket;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/18 21:53
 * @Description:
 */
class TicketDemo {
    private int number = 50;
    ReentrantLock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + "卖出第: \t" + (number--) + "\t 还剩: " + number);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class SaleTicketDemo {
    public static void main(String[] args) {
        TicketDemo ticket = new TicketDemo();

        new Thread(() -> {
            for (int i = 0; i < 55; ++i) {
                ticket.sale();
            }
        }, "a").start();;

        new Thread(() -> {
            for (int i = 0; i < 55; ++i) {
                ticket.sale();
            }
        }, "b").start();;

        new Thread(() -> {
            for (int i = 0; i < 55; ++i) {
                ticket.sale();
            }
        }, "c").start();;
    }
}

