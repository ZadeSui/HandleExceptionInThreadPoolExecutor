package top.zadesui.tpool.method1;

import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

/**
 * @author suizhendong
 * @date 2022/3/14
 */
class CustomThreadPoolTest {
    @Test
    public void test() throws InterruptedException {
        final CustomThreadPool threadPool = new CustomThreadPool(1,
                Runtime.getRuntime().availableProcessors(),
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2 << 4));

        threadPool.execute(() -> {
            System.out.println("执行任务");
            throw new IllegalArgumentException("Runtime 异常");
        });

        threadPool.shutdown();
        threadPool.awaitTermination(10, TimeUnit.SECONDS);
    }
}