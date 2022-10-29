package base;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/29 22:28
 * @Description:
 */
public class LockClearUpDemo {
    static Object objectLock = new Object();

    public static void main(String[] args) {
        LockClearUpDemo lockClearUpDemo = new LockClearUpDemo();

        for (int i = 1; i <= 10; ++i) {
            new Thread(() -> {
                m1();
            }, String.valueOf(i)).start();
        }
    }

    private static void m1() {
        /*synchronized (objectLock) {
            System.out.println("---hello");
        }*/

        Object o = new Object();
        synchronized (o) {
            System.out.println("---hello" + "\t" + o.hashCode() + "\t" + objectLock.hashCode());
        }
    }
}
