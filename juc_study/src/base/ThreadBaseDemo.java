package base;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/15 20:27
 * @Description:
 */
public class ThreadBaseDemo {
    public static void main(String[] args) {
        Thread thread = new Thread(() -> {

        }, "t1");
        thread.start();
    }
}
