package base;

import org.openjdk.jol.info.ClassLayout;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/28 22:00
 * @Description:
 */
public class SynchronizedUpDemo {
    public static void main(String[] args) {
        Object o = new Object();

        System.out.println(o.hashCode());
        System.out.println(Integer.toHexString(o.hashCode()));
        System.out.println(Integer.toBinaryString(o.hashCode()));

        System.out.println(ClassLayout.parseInstance(o).toPrintable());
    }
}
