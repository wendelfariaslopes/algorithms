package algorithms.trading;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Order {
	
	private String id;
	private String company;
	private String type;
	private double price;
	private int quantity;
	private Instrument instrument;
	private Map<Integer,Instrument> mapShareOrdered;
	private Map<Integer,Instrument> mapShareExecuted;

	public Order(String id, String type, double price, Instrument instrument, int quantity) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
		this.quantity = quantity;
		this.instrument=instrument;
		
//		if(this.quantity > 0) {
//			mapShareOrdered = new HashMap<>();
//			for (int i = 0; i < this.quantity; i++) {
//				mapShareOrdered.put(i, new Instrument(id, this.instrument.getRic(), this.price));
//			}
//			mapShareExecuted = new HashMap<>();
//		}
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}

	public Instrument getInstrument() {
		return instrument;
	}

	public void setInstrument(Instrument instrument) {
		this.instrument = instrument;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

}
