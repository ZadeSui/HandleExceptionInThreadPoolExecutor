package top.zadesui.tpool.method2;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author suizhendong
 * @date 2022/3/14
 */
public class MyNamedExceptionThreadFactory implements ThreadFactory {
    public static final String SUFFIX = "-";

    private final AtomicInteger atomicInteger = new AtomicInteger(1);
    private final String name;

    public MyNamedExceptionThreadFactory(String name) {
        if (!name.endsWith(SUFFIX)) {
            name = name + SUFFIX;
        }
        this.name = name;
    }

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r, name + atomicInteger.getAndIncrement());
        // 此处实现 UncaughtExceptionHandler 接口
        thread.setUncaughtExceptionHandler((t, e) -> {
            System.err.println("thread " + t.getName() + " throw exception " + e.getMessage());
        });
        thread.setDaemon(false);
        return thread;
    }
}
