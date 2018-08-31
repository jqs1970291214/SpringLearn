package ThreadTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Executor多线程执行框架测试
 *
 * 任务框架
 * 解耦任务的执行和发布
 * 提供线程池和定时任务
 *
 * @author Junqson
 * @date 2018/8/31 14:49
 */
public class ExecutorTest {
    public static void sleep(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void testExecutor() {
//        ExecutorService service = Executors.newSingleThreadExecutor(); //单线程执行环境
        ExecutorService service = Executors.newFixedThreadPool(2);//多线程(2)线程池

        service.submit(() -> {
            for (int i = 0; i < 10; i++) {
                sleep(100);
                System.out.println("execute " + i);
            }
        });
        service.submit(() -> {
            for (int i = 0; i < 10; i++) {
                sleep(100);
                System.out.println("execute2 " + i);
            }
        });
        service.shutdown(); //请求关闭
        while (!service.isTerminated()) {
            sleep(50);
            System.out.println("wait for termination");
        }

    }

    public static void main(String[] args) {
        testExecutor();
    }
}
