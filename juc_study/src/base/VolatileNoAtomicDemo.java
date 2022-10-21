package base;

import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/21 22:32
 * @Description:
 */
class MyNumber {
    int number;

    public void addPlusPlus() {
        number++;
    }
}

public class VolatileNoAtomicDemo {
    public static void main(String[] args) {
        MyNumber myNumber = new MyNumber();

        for (int i = 0; i <= 10; ++i) {
            new Thread(() -> {
                for (int j = 1; j <= 1000; ++j) {
                    myNumber.addPlusPlus();
                }
            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(myNumber.number);
    }
}
