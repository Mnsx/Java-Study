package base;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/23 20:37
 * @Description:
 */
public class AtomicIntegerArrayDemo {
    public static void main(String[] args) {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(new int[5]);

        for (int i = 0;  i < atomicIntegerArray.length(); ++i) {
            System.out.println(atomicIntegerArray.get(i));
        }

        int tmpInt = 0;

        tmpInt = atomicIntegerArray.getAndSet(0, 1122);
        System.out.println(atomicIntegerArray.get(0));
        tmpInt = atomicIntegerArray.getAndSet(1, 1123);
        System.out.println(atomicIntegerArray.get(1));
    }
}
