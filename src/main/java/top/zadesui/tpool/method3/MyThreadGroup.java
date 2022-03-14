package top.zadesui.tpool.method3;

/**
 * @author suizhendong
 * @date 2022/3/14
 */
public class MyThreadGroup extends ThreadGroup {
    public MyThreadGroup(String name) {
        super(name);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        System.err.println("thread group: " + getName() +
                " caught exception in thread: " + t.getName() +
                " detail: " + e.getMessage());
        e.printStackTrace();
    }
}
