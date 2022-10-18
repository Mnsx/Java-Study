package io.bio.three;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @BelongsProject: juc_study
 * @User: Mnsx_x
 * @CreateTime: 2022/10/17 11:28
 * @Description:
 */
public class HandlerSocketServerPool {
    // 创建一个线程池成员变量存储一个线程池对象
    private ExecutorService executorService;

    /**
     * 创建这个类对象的时候就需要初始化线程池对象
     */
    public HandlerSocketServerPool(int maxTreadNum, int queueSize) {
        executorService = new ThreadPoolExecutor(3, maxTreadNum, 120,
                TimeUnit.MINUTES, new ArrayBlockingQueue<Runnable>(queueSize));
    }

    /**
     * 提供一个方法来提交任务给线程池的任务队列来缓存，等待线程池来处理
     */
    public void execute(Runnable runnable) {
        executorService.execute(runnable);
    }
}
