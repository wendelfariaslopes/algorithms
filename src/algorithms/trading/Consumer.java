package algorithms.trading;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;
import java.util.UUID;


// Consumer sao como os Trades
public class Consumer {
	
	private static String[] listCompany = { "Google", "Apple", "Microsoft", "Amazon", "Facebook" };

	// Generate Bid Buy and Bid Sell
	public static Order generateBid() {

		Random r = new Random();
	//	Instrument i = new Instrument("", listCompany[r.nextInt(listCompany.length)], 0.0);
		Instrument i = new Instrument("", listCompany[0], 0.0);
		
		return new Order(UUID.randomUUID().toString(), "BUY", simulatePrice(i), i, 1);

//		if (r.nextBoolean()) {
//			return new Order(UUID.randomUUID().toString(), "BUY", simulatePrice(i), i, 1);
//		} else {
//			return new Order(UUID.randomUUID().toString(), "SELL", simulatePrice(i), i, 1);
//		}
	}

	private static double simulatePrice(Instrument instrument) {

		if (instrument.getRic().equals("Google")) {
			return 10 + round((Math.random()));
		} else if (instrument.getRic().equals("Apple")) {
			return 15 + round((Math.random()));
		} else if (instrument.getRic().equals("Microsoft")) {
			return 20 + + round((Math.random()));
		} else if (instrument.getRic().equals("Amazon")) {
			return 34 + + round((Math.random()));
		} else {
			return 17 + + round((Math.random()));
		}
	}
	
	private static double round(double d) {
		return new BigDecimal(d).setScale(1, RoundingMode.HALF_UP).doubleValue();
	}

}
