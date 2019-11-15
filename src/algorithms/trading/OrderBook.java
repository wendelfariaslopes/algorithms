package algorithms.trading;

import java.util.ArrayList;
import java.util.List;

public class OrderBook {

	// static variable single_instance of type Singleton
	private static OrderBook instance = null;

	private List<Order> listBuy;
	private List<Order> listSell;

	private OrderBook() {
		listBuy = new ArrayList<>();
		listSell = new ArrayList<>();
	}

	// static method to create instance of Singleton class
	public static OrderBook getInstance() {
		if (instance == null) {
			instance = new OrderBook();
		}	
		return instance;
	}

	public List<Order> getListBuy() {
		return listBuy;
	}

	public void setListBuy(List<Order> listBuy) {
		this.listBuy = listBuy;
	}

	public List<Order> getListSell() {
		return listSell;
	}

	public void setListSell(List<Order> listSell) {
		this.listSell = listSell;
	}

}
