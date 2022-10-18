package base;

import java.util.concurrent.*;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 20:16
 * @Description:
 */
public class CompletableFutureWithThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService threadPool = Executors.newFixedThreadPool(5);

        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("1号任务" + "\t" + Thread.currentThread().getName());
            return "abc";
        }, threadPool).thenRunAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("2号任务" + "\t" + Thread.currentThread().getName());
        }).thenRunAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("3号任务" + "\t" + Thread.currentThread().getName());
        }).thenRunAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("4号任务" + "\t" + Thread.currentThread().getName());
        });

        completableFuture.get(2L, TimeUnit.SECONDS);

        threadPool.shutdown();
    }
}
