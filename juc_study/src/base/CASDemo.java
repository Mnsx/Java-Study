package base;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/22 19:09
 * @Description:
 */
public class CASDemo {
    public static void main(String[] args) {
        AtomicInteger atomicInteger = new AtomicInteger(5);

        System.out.println(atomicInteger.compareAndSet(5, 2048));
        System.out.println(atomicInteger.compareAndSet(5, 2048));

        atomicInteger.getAndIncrement();
    }
}
