package base;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/29 22:33
 * @Description:
 */
public class LockBigDemo {
    static Object objectLock = new Object();

    public static void main(String[] args) {
        new Thread(() -> {
            synchronized (objectLock) {
                System.out.println("111");
            }
            synchronized (objectLock) {
                System.out.println("222");
            }
            synchronized (objectLock) {
                System.out.println("333");
            }

            synchronized (objectLock) {
                System.out.println("111");
                System.out.println("222");
                System.out.println("333");
            }
        }, "t1").start();
    }
}
