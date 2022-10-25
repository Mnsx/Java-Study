package base;

import java.lang.ref.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/25 20:23
 * @Description:
 */
class MyObject {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("---invoke finalize method");
    }
}
public class ReferenceDemo {
    public static void main(String[] args) {
//        strongReference();
//        softReference();
//        WeakReference();
        MyObject myObject = new MyObject();
        ReferenceQueue<MyObject> referenceQueue = new ReferenceQueue<>();
        PhantomReference<MyObject> myObjectPhantomReference = new PhantomReference<>(myObject, referenceQueue);
        myObject = null;
        System.gc();

//        System.out.println(myObjectPhantomReference.get()); // null

        List<byte[]> list = new ArrayList<>();

        new Thread(() -> {
            while (true) {
                list.add(new byte[1 * 1024 * 1024]);
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(myObjectPhantomReference.get());
            }
        }, "t1").start();

        new Thread(() -> {
            while (true) {
                Reference<? extends MyObject> reference = referenceQueue.poll();
                if (reference != null) {
                    System.out.println("---有虚引用对象加入队列");
                    break;
                }
            }
        }, "t2").start();
    }

    private static void WeakReference() {
        WeakReference<MyObject> myObjectWeakReference = new WeakReference<>(new MyObject());
        System.out.println("---before gc" + myObjectWeakReference);
        System.gc();
        System.out.println("---after gc" + myObjectWeakReference);
    }

    private static void softReference() {
        SoftReference<MyObject> myObjectSoftReference = new SoftReference<>(new MyObject());
        try {
            TimeUnit.MILLISECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//        System.out.println("---gc after内存够用: " + myObjectSoftReference.get());

        try {
            byte[] bytes = new byte[20 * 1024 * 1024];
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("---gc after内存不够用: " + myObjectSoftReference.get());
        }
    }

    private static void strongReference() {
        MyObject myObject = new MyObject();
        System.out.println("gc before: " + myObject);

        myObject = null;
        System.gc();

        try {
            TimeUnit.MILLISECONDS.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("gc after: " + myObject);
    }
}
