package base;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/19 21:01
 * @Description:
 */
public class InterruptDemo2 {
    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            for (int i = 1; i <= 300; ++i) {
                System.out.println("-----" + i);
            }
            System.out.println("2" + Thread.currentThread().isInterrupted());
        }, "t1");
        t1.start();
        try {
            TimeUnit.MILLISECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t1.interrupt();
        System.out.println("1" + t1.isInterrupted());
        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("3" + t1.isInterrupted());
    }
}
