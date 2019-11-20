package algorithms.collections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class Sorting {

	public static void main(String[] args) {

		List<Order> listBids = getOrdersBid(10, 5, 0);
		List<Order> listAsks = getOrdersAsk(20, 9, 4);
		
		Map<Order,Order> book = new HashMap<>();

		// lambda here!
		listBids.sort((o1, o2) -> o1.getQuantity() - o2.getQuantity());

		System.out.println();
		System.out.println("After Sort descending");

		// lambda here!
		listAsks.sort((o1, o2) -> o2.getQuantity() - o1.getQuantity());
		
		// java 8 only, lambda also, to print the List
		//listBids.forEach((Order) -> System.out.println(Order));

		// java 8 only, lambda also, to print the List
		//listAsks.forEach((Order) -> System.out.println(Order));
		
		Set<Order> askSet = new HashSet<>();
		
			for(Order bid: listBids) {
				for(Order ask: listAsks) {
					if(!ask.isMatched() && (bid.getPrice().doubleValue()==ask.getPrice().doubleValue())) {
						book.put(bid, ask);
						askSet.add(ask);
						ask.setMatched(true);
					}
				}
			}
			
		
		System.out.println(book.size() );	
		
		book.forEach((b,a) -> System.out.println(b+" <-> "+a));
		
		System.out.println(askSet.size());
		askSet.forEach(x->System.out.println(x));
		


	}
	
	

	
	private static List<Order> getOrdersBid(int size, int high, int low) {
		
		List<Order> list = new ArrayList<Order>();

		for(int i = 0; i < size; i++) {
			
			list.add(new Order(i,"ABC", new BigDecimal(10 + volatility(high, low)), volume(100, 5)));
		}

		return list;

	}
	
	private static List<Order> getOrdersAsk(int size, int high, int low) {
		Random r = new Random();
		List<Order> list = new ArrayList<Order>();

		for(int i = 0; i < size; i++) {
			
			list.add(new Order(i,"ABC", new BigDecimal(10 + volatility(high, low)), volume(100, 20) ));
		}

		return list;

	}
	
	private static int volatility(int high, int low) {
		Random r = new Random();
		return (r.nextInt(high-low) + low);
	}
	
	private static int volume(int base, int variation) {
		Random r = new Random();
		return base + r.nextInt(variation);
	}

}

class Order {

	private int id;
	private String stock;
	private BigDecimal price;
	private int quantity;
	
	private boolean matched;

	public Order(int id, String stock, BigDecimal price, int quantity) {
		super();
		this.id = id;
		this.stock = stock;
		this.price = price;
		this.quantity = quantity;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
		return id + "@" + quantity + "@" + price+ " matched:"+matched;

	}
}