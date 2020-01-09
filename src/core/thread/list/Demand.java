package core.thread.list;

import java.util.Random;

public class Demand extends Thread {

	private long sleep;

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(sleep);
				Random r = new Random();
				int quantity = 100 + r.nextInt(20);
				
				
				for (int i = 0; i < quantity; i++) {
					SynchronizedList.listDemand
							.add(r.nextInt(100) + " | " + Thread.currentThread().getName() + ":" + r.nextInt(10));
				}
			}

			catch (Exception ex) {
				System.out.println("Exception has" + " been caught" + ex);
			}

		}
	}

	public long getSleep() {
		return sleep;
	}

	public void setSleep(long sleep) {
		this.sleep = sleep;
	}

}
