package core.thread.list;


public class Trade extends Thread {
	
	private long sleep;

	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(sleep);
				SynchronizedList.listTrade.forEach(t->System.out.println(t));
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

