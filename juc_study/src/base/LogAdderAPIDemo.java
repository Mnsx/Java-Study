package base;

import java.util.concurrent.atomic.LongAccumulator;
import java.util.concurrent.atomic.LongAdder;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/23 22:46
 * @Description:
 */
public class LogAdderAPIDemo {
    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();

        longAdder.increment();
        longAdder.increment();
        longAdder.increment();

        System.out.println(longAdder.sum());

        LongAccumulator longAccumulator = new LongAccumulator((x, y) -> x + y, 0);

        longAccumulator.accumulate(1);
        longAccumulator.accumulate(3);

        System.out.println(longAccumulator.get());
    }
}
