package algorithms.trading;

public class Order {
	
	private String id;
	private String type;
	private double price;
	private Instrument instrument;
	private int quantity;

	public Order(String id, String type, double price, Instrument instrument, int quantity) {
		super();
		this.id = id;
		this.type = type;
		this.price = price;
		this.instrument = instrument;
		this.quantity = quantity;
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
