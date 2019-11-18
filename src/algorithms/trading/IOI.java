package algorithms.trading;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class IOI implements Runnable {

	private static String[] listCompany = { "Google", "Apple", "Microsoft", "Amazon", "Facebook" };

	List<Order> listAvailable;

	public void run() {
		for (int i = 0; i < 20; i++) {

			//System.out.println(Thread.currentThread().getName() + "  " + i);
			//generateSupplyAsk();
			Order o = generateSupplyAsk();
			System.out.println(o.getInstrument().getRic() +":"+ o.getQuantity()+"@"+o.getPrice()+" Type: "+o.getType());
			try {
				// thread to sleep for 1000 milliseconds
				Thread.sleep(500);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
	}

	public static void main(String[] args) throws Exception {

		Thread t = new Thread(new IOI());
		// this will call run() function
		t.start();

//		Thread t2 = new Thread(new IOI());
//		// this will call run() function
//		t2.start();
//		
//		Thread t3 = new Thread(new IOI());
//		// this will call run() function
//		t3.start();
	}

	private static Order generateSupplyAsk(){
		
		Random r = new Random();
		Instrument i = new Instrument("", listCompany[r.nextInt(listCompany.length)], 0.0);
		
		if(r.nextBoolean()) {
			return new Order(UUID.randomUUID().toString(), "BUY", simulatePrice(i), i, r.nextInt(100));
		}else {
			return new Order(UUID.randomUUID().toString(), "SELL", simulatePrice(i), i, r.nextInt(100));
		}
	}
	
	private static double simulatePrice(Instrument instrument) {
		
		if(instrument.getRic().equals("Google")) {
			return 10 + Math.random();
		}else if(instrument.getRic().equals("Apple")) {
			return 15 + Math.random();
		}else if(instrument.getRic().equals("Microsoft")) {
			return 20 + Math.random()*2;
		}else if(instrument.getRic().equals("Amazon")) {
			return 34 + Math.random();
		}else {
			return 17 + Math.random();
		}
	}
}
