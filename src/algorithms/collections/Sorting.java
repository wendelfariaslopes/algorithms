package algorithms.collections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class Sorting {

	public static void main(String[] args) {

		List<Order> listBids = getOrdersBid(5, 5, 0);
		List<Order> listAsks = getOrdersAsk(100, 10, 0);

		Map<Order, Order> book = new HashMap<>();

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

//		System.out.println("Bid-Ask list matched total = " + book.size());
//		System.out.println("");
//		book.forEach((b, a) -> System.out.println(b + " " + b.isMatched() + " <-> " + a + " " + a.isMatched()));
		
		System.out.println("Bid-Ask list matched total = " + list.size());
		System.out.println("");
//		list.forEach(l -> System.out.println(l));

		//System.out.println("");
		//System.out.println("Bid list without matched total = " + listBids.size());
		//System.out.println("");
		
	
		listBids.stream().filter(b-> b.isMatched()==false).forEach(b -> System.out.println(b + " " + b.isMatched()));
		//System.out.println("");
		//System.out.println("Ask list without matched total = " + listAsks.size());
		System.out.println("");
		listAsks.stream().filter(a-> a.isMatched()==false).forEach(a -> System.out.println(a + " " + a.isMatched()));

	}

	private static List<Order> getOrdersBid(int size, int high, int low) {

		List<Order> list = new ArrayList<Order>();

		for (int i = 0; i < size; i++) {

			list.add(new Order(i + "-bid", "ABC", new BigDecimal(10 + volatility(high, low)), volume(100, 5)));
		}

		return list;

	}

	private static List<Order> getOrdersAsk(int size, int high, int low) {
		Random r = new Random();
		List<Order> list = new ArrayList<Order>();

		for (int i = 0; i < size; i++) {

			list.add(new Order(i + "-ask", "ABC", new BigDecimal(10 + volatility(high, low)), volume(100, 20)));
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

class Order {

	private String id;
	private String stock;
	private BigDecimal price;
	private int quantity;

	private boolean matched;

	public Order(String id, String stock, BigDecimal price, int quantity) {
		super();
		this.id = id;
		this.stock = stock;
		this.price = price;
		this.quantity = quantity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public boolean isMatched() {
		return matched;
	}

	public void setMatched(boolean matched) {
		this.matched = matched;
	}

	@Override
	public String toString() {
		return id + "@" + quantity + "@" + price;

	}
}