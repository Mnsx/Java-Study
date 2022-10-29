package base;

import org.openjdk.jol.info.ClassLayout;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/29 22:17
 * @Description:
 */
public class HashCodeSynchronizedDemo {
    public static void main(String[] args) {
/*        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Object o = new Object();
        System.out.println(ClassLayout.parseInstance(o).toPrintable());

        o.hashCode();

        synchronized (o) {
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }*/

        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Object o = new Object();

        synchronized (o) {
            o.hashCode();
            System.out.println(ClassLayout.parseInstance(o).toPrintable());
        }
    }
}
