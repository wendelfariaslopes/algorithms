package com.organon.oms.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.google.common.base.Predicate;

public class OrderBook {

	public static void main(String[] args) {
		
		int minBid = 5;
		int maxAsk = 10;
		int spreadBidAsk = maxAsk - minBid;

		List<Order> listBids = getOrdersBid(100, 8, 0);
		List<Order> listAsks = getOrdersAsk(110, 11, 3);

		listBids.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
		listAsks.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));

		// Match Engine
		System.out.println();
		System.out.println("Pariedades encontradas: ");

		for (int i = 0; i < listBids.size(); i++) {
			for (int j = 0; j < listAsks.size(); j++) {
				Order bid = listBids.get(i);
				Order ask = listAsks.get(j);
				if (!bid.isMatched() && !ask.isMatched()
						&& (bid.getPrice().doubleValue() == ask.getPrice().doubleValue())) {
					System.out.println(bid + " <-> " + ask);
					bid.setMatched(true);
					ask.setMatched(true);
				}
			}
		}

		Predicate<Order> matchedNo = o -> !o.isMatched();
		Predicate<Order> matchedYes = o -> o.isMatched();

		long sizeBidNoMatched = listBids.stream().filter(matchedNo).count();
		long sizeAskNoMathced = listAsks.stream().filter(matchedNo).count();

		long sizeBidMatchedYes = listBids.stream().filter(matchedYes).count();
		long sizeAskMathcedYes = listAsks.stream().filter(matchedYes).count();

		System.out.println();

		System.out.println("Size bids   = " + listBids.size() + " Size Asks = " + listAsks.size());
		System.out.println("Yes Matched = " + sizeBidMatchedYes + " For Asks = " + sizeAskMathcedYes);
		System.out.println("No Matched  = " + sizeBidNoMatched + " For Asks = " + sizeAskNoMathced);

	}

	private static List<Order> getOrdersBid(int size, int high, int low) {

		List<Order> list = new ArrayList<Order>();

		for (int i = 0; i < size; i++) {

			String id = i + "bid";

			list.add(new Order(id, "ABC", new BigDecimal(10 + volatility(high, low)), volume(100, 5)));
		}

		return list;

	}

	private static List<Order> getOrdersAsk(int size, int high, int low) {
		List<Order> list = new ArrayList<Order>();

		for (int i = 0; i < size; i++) {
			String id = i + "ask";
			list.add(new Order(id, "ABC", new BigDecimal(10 + volatility(high, low)), volume(100, 20)));
		}

		return list;

	}

	private static int volatility(int high, int low) {
		Random r = new Random();
		return (r.nextInt(high - low) + low);
	}

	private static int volume(int base, int variation) {
		Random r = new Random();
		return base + r.nextInt(variation);
	}

}
