package base;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/26 19:20
 * @Description:
 */
public class ObjectHeadDemo {
    public static void main(String[] args) {

        Object o = new Object(); // new一个对象，占多少内存

        System.out.println(o.hashCode()); // 这个hashcode记录在对象的什么地方

        synchronized (o) {
            // 为什么知道这个对象被加锁
        }

        System.gc(); // 手动收集垃圾——如果15次没有被回收，那么就可以从新生代到养老区

        Test test = new Test();
    }
}

class Test {

}
