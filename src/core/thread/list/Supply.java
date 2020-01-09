package core.thread.list;

import java.util.Random;

public class Supply extends Thread {
	
	private long sleep;
	private String[] listMarketMaker = {"UBS NYNQ","MORGAN ST","TRADE EX"};
	
	@Override
	public void run() {
		while(true) {
			try {
				
				Thread.sleep(sleep);
				Random r = new Random();
				
				int quantity = 100 + r.nextInt(10);
				
				for(int i = 0; i < quantity; i++) {
					SynchronizedList.listSupply.add(listMarketMaker[r.nextInt(3)]+" | "+Thread.currentThread().getName()+":"+r.nextInt(13));
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
