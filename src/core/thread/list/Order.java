package core.thread.list;

import java.math.BigDecimal;

public class Order {
	
	private String stock;
	private int quantity;
	private BigDecimal price;
	
	
	public Order(String stock, int quantity, BigDecimal price) {
		super();
		this.stock = stock;
		this.quantity = quantity;
		this.price = price;
	}
	
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}

}
