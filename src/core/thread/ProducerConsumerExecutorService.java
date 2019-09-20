package core.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * The difference here is that, instead of manually creating and running the
 * consumer and producer threads, we build a thread pool, and it will receive
 * two tasks, a producer and a consumer task. The producer and consumer tasks
 * are actually the same runnables that were used in the previous example. Now,
 * the executor (the thread pool implementation) receives the tasks, and its
 * working threads will execute them.
 * 
 * @author wendellopes
 *
 */

public class ProducerConsumerExecutorService {
	
	public static void main(String[] args) {
		
		BlockingQueue<Integer> blockingQueue = new LinkedBlockingDeque<>(2);
		ExecutorService executor = Executors.newFixedThreadPool(2);
		Runnable producerTask = () -> {
			try {
				int value = 0;
				while (true) {
					blockingQueue.put(value);
					System.out.println("Produced " + value);
					value++;
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		Runnable consumerTask = () -> {
			try {
				while (true) {
					int value = blockingQueue.take();
					System.out.println("Consume " + value);
					Thread.sleep(1000);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		executor.execute(producerTask);
		executor.execute(consumerTask);
		executor.shutdown();
	}
}
