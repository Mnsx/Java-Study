package base;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 20:03
 * @Description:
 */
public class CompletableFutureAPI3Demo {
    public static void main(String[] args) {
        CompletableFuture.supplyAsync(() -> 1).thenApply(v -> v + 2).thenApply(v -> v + 3).thenAccept(System.out::println);
    }
}
