package core.thread.list;

import java.util.ArrayList;
import java.util.List;

public class BookOrder extends Thread {

	List<String> listSupply;
	List<String> listDemand;
	List<String> listTrade;
	
	private static boolean flag = true;
	
	public static void stopProcess() {flag = false;}

	@Override
	public void run() {
		
		int sizeSupply = 0;
		int sizeDemand = 0;
		int sizeTrade = 0;
		
		while (flag) {
			try {

				System.out.println(Thread.currentThread().getName());

				synchronized (SynchronizedList.listSupply) {
					listSupply = new ArrayList<>(SynchronizedList.listSupply);
					sizeSupply = listSupply.size();
				}

				synchronized (SynchronizedList.listDemand) {
					listDemand = new ArrayList<>(SynchronizedList.listDemand);
					sizeDemand = listDemand.size();
				}
				
				synchronized (SynchronizedList.listTrade) {
					listTrade = new ArrayList<>(SynchronizedList.listTrade);
					sizeTrade = listTrade.size();
				}

				System.out.println("Supply size = "+sizeSupply + " Demand size = " + sizeDemand+ " Trade size="+sizeTrade);
				
				for (String s: new ArrayList<>(listSupply)) {
					for (String d: new ArrayList<>(listDemand)) {
						int ask = Integer.parseInt(s.split(":")[1]);
						int bid = Integer.parseInt(d.split(":")[1]);
						
						if (ask == bid) {	
							SynchronizedList.listTrade.add(d + " <-> " + s);
							SynchronizedList.listSupply.remove(s);
							SynchronizedList.listDemand.remove(d);
						} 
						
					}
				}
					
				Thread.sleep(1000);

			}

			catch (Exception ex) {
				System.out.println("Exception has" + " been caught" + ex);
			}

		}
	}
}
