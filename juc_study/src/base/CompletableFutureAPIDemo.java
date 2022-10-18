package base;

import jdk.nashorn.api.scripting.ScriptObjectMirror;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 19:27
 * @Description:
 */
public class CompletableFutureAPIDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "abc";
        });

        // 获得结果
//        System.out.println(completableFuture.get()); // abc
//        System.out.println(completableFuture.get(2, TimeUnit.MILLISECONDS)); // abc
//        System.out.println(completableFuture.join()); // abc
//        System.out.println(completableFuture.getNow("还没有结果")); // 还没有结果
        // 主动触发计算
/*        System.out.println(completableFuture.complete("打断计算，立即返回该值")); // true
        System.out.println(completableFuture.get()); // 打断计算，立即返回该值*/
    }
}
