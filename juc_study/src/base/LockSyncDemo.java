package base;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/18 21:34
 * @Description:
 */
public class LockSyncDemo {
    Object obj = new Object();

    public void m1() {
        synchronized (obj) {
            System.out.println("---hello synchronized code block");
        }
    }

    public static void main(String[] args) {

    }
}
