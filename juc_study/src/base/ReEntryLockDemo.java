package base;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/18 22:12
 * @Description:
 */
public class ReEntryLockDemo {
    public static synchronized void m1() {
        System.out.println(Thread.currentThread().getName() + "\t---come in");
        m2();
        System.out.println(Thread.currentThread().getName() + "\t---come end");
    }

    public static synchronized void m2() {
        System.out.println(Thread.currentThread().getName() + "\t---come in");
        m3();
        System.out.println(Thread.currentThread().getName() + "\t---come end");
    }

    public static synchronized void m3() {
        System.out.println(Thread.currentThread().getName() + "\t---come in");
        System.out.println(Thread.currentThread().getName() + "\t---come end");
    }

    public static void main(String[] args) {
        m1();
    }

    private static void lock1() {
        final Object obj = new Object();

        new Thread(() -> {
            synchronized (obj) {
                System.out.println(Thread.currentThread().getName() + "\t ---外层调用");
                synchronized (obj) {
                    System.out.println(Thread.currentThread().getName() + "\t ---中层调用");
                    synchronized (obj) {
                        System.out.println(Thread.currentThread().getName() + "\t ---内层调用");
                    }
                }
            }
        }, "a").start();
    }
}
