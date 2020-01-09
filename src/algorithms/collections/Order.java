package algorithms.collections;

import java.math.BigDecimal;

public class Order {

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