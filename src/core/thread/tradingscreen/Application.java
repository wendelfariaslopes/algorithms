package core.thread.tradingscreen;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Application implements ThreadFactory {

	/**
	 * Creates a new ThreadPoolExecutor with the given initial parameters and default
	 * thread factory and rejected execution handler.It may be more convenient to
	 * use one of the Executors factory methods instead of this general purpose
	 * constructor. 
	 * Parameters:
	 * corePoolSize - the number of threads to keep in the pool, even if they are idle, unless allowCoreThreadTimeOut is set
	 * maximumPoolSize - the maximum number of threads to allow in the pool
	 * keepAliveTime - when the number of threads is greater than the core, this is the maximum time that excess idle threads will wait for new tasks before terminating unit - the time unit for the keepAliveTime argument
	 * workQueue - the queue to use for holding tasks before they are
	 * executed. This queue will hold only the Runnable tasks submitted by the execute method.
	 */

	private Executor executor = new ThreadPoolExecutor(5, 20, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(), this);

	private ScheduledExecutorService singleThreadScheduledExecutor = Executors.newSingleThreadScheduledExecutor(this);

	public static void main(String[] args) throws InterruptedException {
		repeat();
	}

	public void executeOnPooledThread(Runnable runnable) {
		executor.execute(runnable);
	}

	private AtomicInteger threadCounter = new AtomicInteger(0);

	/**
	 * This come from ThreadFactory Interface
	 */

	@Override
	public Thread newThread(Runnable runnable) {
		Thread thread = Executors.defaultThreadFactory().newThread(runnable);
		thread.setName("Application-" + threadCounter.getAndIncrement());
		return thread;
	}
	
	public static void repeat() throws InterruptedException {
		CountDownLatch lock = new CountDownLatch(3);
		 
		ScheduledExecutorService executor = Executors.newScheduledThreadPool(5);
		ScheduledFuture<?> future = executor.scheduleAtFixedRate(() -> {
		    System.out.println("Hello World");
		    lock.countDown();
		}, 500, 100, TimeUnit.MILLISECONDS);
		 
		lock.await(1000, TimeUnit.MILLISECONDS);
		future.cancel(true);
	}

}
