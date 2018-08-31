package ThreadTest;

import sun.awt.windows.ThemeReader;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/31 14:08
 */
public class AtomicIntegerTest {
    private static int count = 0;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);



    public static void testAtomicInteger() {
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int ii = 0; ii < 10; ii++) {
                    System.out.println(atomicInteger.incrementAndGet());
                }
            }).start();
        }
    }

    public static void testUnsafeInteger() {

        for (int i = 0; i < 25; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                for (int ii = 0; ii < 4; ii++) {
                    System.out.println(count++);
                }
            }).start();
        }
    }



    public static void main(String[] args) {
        testUnsafeInteger();
       //testAtomicInteger();
    }
}
