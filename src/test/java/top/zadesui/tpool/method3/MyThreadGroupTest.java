package top.zadesui.tpool.method3;

import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author suizhendong
 * @date 2022/3/14
 */
class MyThreadGroupTest {

    private AtomicInteger atomicInteger = new AtomicInteger(1);

    @Test
    public void test() throws InterruptedException {
        final MyThreadGroup threadGroup = new MyThreadGroup("test-group");
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                Runtime.getRuntime().availableProcessors(),
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2 << 4),
                (r) -> new Thread(threadGroup, r, "test-thread-" + atomicInteger.getAndIncrement()),
                new ThreadPoolExecutor.CallerRunsPolicy());

        executor.execute(() -> {
            throw new RuntimeException("运行时异常");
        });

        TimeUnit.SECONDS.sleep(1);
        executor.shutdown();
        executor.awaitTermination(60, TimeUnit.SECONDS);
    }

}