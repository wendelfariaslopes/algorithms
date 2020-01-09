package algorithms.collections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Sorting {

	public static void main(String[] args) {

		List<Order> listBids = getOrdersBid(700000, 8, 0);
		List<Order> listAsks = getOrdersAsk(700000, 11, 3);
		
		System.out.println("Ask Grouping");
		long start = System.currentTimeMillis();
		Grouping.sortedMapUsingParallel(Grouping.groupByPriceUsingParallel(listAsks)).forEach((price,list) -> System.out.println(list.size()+"@"+price+" "+list));
		long end = System.currentTimeMillis();
		
		System.out.println(end - start);
		
		System.out.println("Bid Grouping");
		start = System.currentTimeMillis();
		Grouping.sortedMap(Grouping.groupByPrice(listBids)).forEach((price,list) -> System.out.println(list.size()+"@"+price+" "+list));
		end = System.currentTimeMillis();
		System.out.println(end - start);

		// lambda here!
		listBids.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
		// lambda here!
		listAsks.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
		
		List<String> list = new ArrayList<>();

		for (int i = 0; i < listBids.size(); i++) {
			for (int j = 0; j < listAsks.size(); j++) {			
					Order bid = listBids.get(i);
					Order ask = listAsks.get(j);
					
					if (!bid.isMatched() && !ask.isMatched() && bid.getPrice().doubleValue() == ask.getPrice().doubleValue()) {
						listBids.get(i).setMatched(true);
						listAsks.get(j).setMatched(true);
						//book.put(bid, ask);
						System.out.println(bid+" <-> "+ask);
						list.add(bid+" <-> "+ask);

//						listAsks.remove(j);
//						listAsks = new ArrayList<>(listAsks);
//
//						listBids.remove(i);
//						listBids = new ArrayList<>(listBids);
				}
			}
		}


//		listBids.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
//		listAsks.sort((o1, o2) -> o1.getPrice().compareTo(o2.getPrice()));
//
//		// Match Engine
//		System.out.println();
//		System.out.println("Pariedades encontradas: ");
//
//		for (int i = 0; i < listBids.size(); i++) {
//			for (int j = 0; j < listAsks.size(); j++) {
//				Order bid = listBids.get(i);
//				Order ask = listAsks.get(j);
//				if (!bid.isMatched() && !ask.isMatched()
//						&& (bid.getPrice().doubleValue() == ask.getPrice().doubleValue())) {
//					System.out.println(bid + " <-> " + ask);
//					bid.setMatched(true);
//					ask.setMatched(true);
//				}
//			}
//		}

//		Predicate<Order> matchedNo = o -> !o.isMatched();
//		Predicate<Order> matchedYes = o -> o.isMatched();
//
//		long sizeBidNoMatched = listBids.stream().filter(matchedNo).count();
//		long sizeAskNoMathced = listAsks.stream().filter(matchedNo).count();
//
//		long sizeBidMatchedYes = listBids.stream().filter(matchedYes).count();
//		long sizeAskMathcedYes = listAsks.stream().filter(matchedYes).count();




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

