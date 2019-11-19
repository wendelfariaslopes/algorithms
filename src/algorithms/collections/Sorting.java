package algorithms.collections;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;

public class Sorting {

	public static void main(String[] args) {

		List<Order> listBids = getOrders(1000);
		List<Order> listAsks = getOrders(5000);
		
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
		
		List<Order> listMatched =
			    listBids.stream()
			           .filter(bid -> listAsks.stream().map(Order::getPrice).anyMatch(ask -> ask.equals(bid.getPrice())))
			           .collect(Collectors.toList());
		System.out.println(listMatched.size());
		
		//listMatched.forEach(l -> System.out.println(l));

	}
	
	

	
	private static List<Order> getOrdersBid(int size, int high, int low) {
		
		List<Order> list = new ArrayList<Order>();

		for(int i = 0; i < size; i++) {
			
			list.add(new Order("ABC", new BigDecimal(10 + volatility(high, low)), volume(100)));
		}

		return list;

	}
	
	private static List<Order> getOrdersAsk(int size, , int high, int low) {
		Random r = new Random();
		List<Order> list = new ArrayList<Order>();

		for(int i = 0; i < size; i++) {
			
			list.add(new Order("ABC", new BigDecimal(10 + r.nextInt(rangeBidAsk)), volume(100) ));
		}

		return list;

	}
	
	private static int volatility(int high, int low) {
		Random r = new Random();
		return (r.nextInt(high-low) + low);
	}
	
	private static int volume(int base) {
		Random r = new Random();
		return 10 + r.nextInt(20);
	}

}

class Order {

	private String stock;
	private BigDecimal price;
	private int quantity;

	public Order(String stock, BigDecimal price, int quantity) {
		super();
		this.stock = stock;
		this.price = price;
		this.quantity = quantity;
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

	@Override
	public String toString() {
		return stock + " " + quantity + "@" + price;

	}
}