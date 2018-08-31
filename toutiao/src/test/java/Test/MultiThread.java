package Test;

import jdk.nashorn.internal.ir.Block;
import org.omg.CORBA.PERSIST_STORE;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * summary
 * descripttions
 *
 * @author Junqson
 * @date 2018/8/31 13:41
 */
public class MultiThread {

    public static void main(String[] args) throws InterruptedException {
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<String>(1000);
        Producer producer = new Producer(blockingQueue);
        Consumer consumer = new Consumer(blockingQueue);
        Consumer consumer1 = new Consumer(blockingQueue);
        new Thread(producer).start();
        new Thread(consumer,"consumer1").start();
        new Thread(consumer1,"consumer2").start();

    }

}

class Producer implements Runnable {

    //阻塞队列
    private BlockingQueue<String> queue;

    public Producer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override

    public void run() {
        try {
            for (int i = 0; i < 40; ++i) {
                System.out.println("event" + String.valueOf(i) + " has entered the queue");
                queue.put("event" + String.valueOf(i));
                //Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Consumer implements Runnable {
    private BlockingQueue<String> queue;

    public Consumer(BlockingQueue<String> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            while (true) {
                String event = queue.take();
                System.out.println(event + " has been processed by " + Thread.currentThread().getName());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}