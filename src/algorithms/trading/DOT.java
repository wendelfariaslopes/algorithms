package algorithms.trading;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

/**
 * DOT - Designated order turnaround is an electronic system that increases efficiency
 * by routing orders for listed securities directly to a specialist on the
 * trading floor instead of through a broker. Designated order turnaround is
 * also known as DOT or SuperDOT.
 * 
 * 
 * @author wlopes
 *
 */
public class DOT {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	
		int days = 10;
		
		for(int i = 0; i < days; i++) {
			System.out.println(LocalDate.now().minus(i, ChronoUnit.DAYS));
		}

	}
	
	public static double VWAP_minimalPrice (List<Position> list) {
		return list.parallelStream().mapToDouble(x -> x.getPrice()).summaryStatistics().getAverage();
	}

}

class Position {
	
	private String client;
	private String ticker;
	private LocalDate date;
	private LocalTime time;
	private double price;
	private int quantity;
	
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getTicker() {
		return ticker;
	}
	public void setTicker(String ticker) {
		this.ticker = ticker;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getTime() {
		return time;
	}
	public void setTime(LocalTime time) {
		this.time = time;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	
	
}