package top.zadesui.tpool.method4;

import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * @author suizhendong
 * @date 2022/3/14
 */
public class MyFutureTest {

    @Test
    public void test() throws InterruptedException {

        final ThreadPoolExecutor threadPool = new ThreadPoolExecutor(1,
                Runtime.getRuntime().availableProcessors(),
                60,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(2 << 4));

        final Future<String> future = threadPool.submit(() -> {
            throw new RuntimeException("future 中抛出异常");
        });
        try {
            future.get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        threadPool.shutdown();
        threadPool.awaitTermination(1, TimeUnit.SECONDS);
    }
}
