package base;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/23 23:33
 * @Description:
 */
class ClickNumber {
    int number = 0;

    public synchronized void click() {
        number++;
    }

    AtomicLong atomicLong = new AtomicLong(0);

    public void click2() {
        atomicLong.getAndIncrement();
    }

    LongAdder longAdder = new LongAdder();

    public void click3() {
        longAdder.increment();
    }

    LongAccumulator longAccumulator = new LongAccumulator(Long::sum, 0);

    public void click4() {
        longAccumulator.accumulate(1);
    }
}
public class AccumulatorCompareDemo {
    public static final int _1w = 10000;
    public static final int threadNumber = 50;

    public static void main(String[] args) throws InterruptedException {
        ClickNumber clickNumber = new ClickNumber();

        CountDownLatch countDownLatch1 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch2 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch3 = new CountDownLatch(threadNumber);
        CountDownLatch countDownLatch4 = new CountDownLatch(threadNumber);

        long start = 0L;
        long end = 0L;

        start = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; ++i) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < _1w; ++j) {
                        clickNumber.click();
                    }
                } finally {
                    countDownLatch1.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch1.await();
        end = System.currentTimeMillis();
        System.out.println("---const time" + (end - start) + "ms" + "\t" + clickNumber.number);

        start = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; ++i) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < _1w; ++j) {
                        clickNumber.click2();
                    }
                } finally {
                    countDownLatch2.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch2.await();
        end = System.currentTimeMillis();
        System.out.println("---const time" + (end - start) + "ms" + "\t" + clickNumber.atomicLong.get());

        start = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; ++i) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < _1w; ++j) {
                        clickNumber.click3();
                    }
                } finally {
                    countDownLatch3.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch3.await();
        end = System.currentTimeMillis();
        System.out.println("---const time" + (end - start) + "ms" + "\t" + clickNumber.longAdder.sum());

        start = System.currentTimeMillis();
        for (int i = 1; i <= threadNumber; ++i) {
            new Thread(() -> {
                try {
                    for (int j = 0; j < _1w; ++j) {
                        clickNumber.click4();
                    }
                } finally {
                    countDownLatch4.countDown();
                }
            }, String.valueOf(i)).start();
        }
        countDownLatch4.await();
        end = System.currentTimeMillis();
        System.out.println("---const time" + (end - start) + "ms" + "\t" + clickNumber.longAccumulator.get());
    }
}
