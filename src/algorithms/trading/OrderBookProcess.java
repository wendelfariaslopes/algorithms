package algorithms.trading;

public class OrderBookProcess {

	public static void main(String[] args) throws InterruptedException {
		
		OrderBook book = new OrderBook();
		
		Thread producerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					book.produce();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread consumerThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					book.consume();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		Thread matchThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					book.match();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		
		producerThread.start();
		consumerThread.start();
		matchThread.start();
		
		
		producerThread.join();
		consumerThread.join();
		matchThread.join();
	}
	
	
}
