package top.zadesui.tpool.method2;

import org.junit.jupiter.api.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author suizhendong
 * @date 2022/3/14
 */
class MyNamedExceptionThreadFactoryTest {
    
    @Test
    public void test() throws InterruptedException {
        final ThreadPoolExecutor executor = new ThreadPoolExecutor(1,
                Runtime.getRuntime().availableProcessors(),
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2 << 4),
                new MyNamedExceptionThreadFactory("test-thread"),
                new ThreadPoolExecutor.CallerRunsPolicy());

        executor.execute(() -> {
            throw new RuntimeException("运行时异常");
        });

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}