package base;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/21 22:25
 * @Description:
 */
public class VolatileSeeDemo {
    static volatile boolean flag = true;

    public static void main(String[] args) {
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "\t ---come in");
            while (flag) {

            }
            System.out.println(Thread.currentThread().getName() + "\t ---end");
        }, "t1").start();

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        flag = false;

        System.out.println(Thread.currentThread().getName() + "\t ---flag被设置为false，程序停止");
    }
}
