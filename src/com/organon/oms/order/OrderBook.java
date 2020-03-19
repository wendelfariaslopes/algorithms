package com.organon.oms.order;

import java.util.ArrayList;
import java.util.List;

/**
 * Order Book Conditions
 * 
 * 
 * Duration types define how long an order exists in the order book for
 * matching. The electronic ordering systems also use this information to decide
 * when an order should be matched against other orders.
 * 
 * 1- Immediate �the order exists in the order book only for a moment 2 - Open
 * �the order exists in the order book only during the open of the market 3 -
 * Close �the order exists in the order book only near or at the close of the
 * market 4 - Session �the order exists in the order book during the current
 * sesssion of the market, typically either morning or afternoon session 5 -
 * Market �the order exists in the order book during the current market 6 - Day
 * � the order exists in the order book during the current day; possibly
 * spanning multiple sessions and markets 7 - Week �the order exists in the
 * order book during the current week; spanning multiple sessions, markets, and
 * days 8 - Month �the order exists in the order book during the current month;
 * spanning multiple sessions, markets, and days 9 - Until Time � the order
 * exists in the order book until the given time; possibly spanning multiple
 * sessions and markets 10 -Until Date � the order exists in the order book
 * until the given date; possibly spanning multiple sessions, markets, and days
 * 11 -Until Crossed � the order exists in the order book until crossed;
 * possibly spanning multiple sessions, markets, and days 12 -Until Cancelled �
 * the order exists in the order book until cancelled, essentially allowing the
 * order to be remain forever
 * 
 * 
 * Pricing Types
 * 
 * 1 - Open The order should be priced at the calculated opening price of the
 * market. Also see Duration Conditions, AtOpen. 2 - Market The order should be
 * priced to execute. This condition implies that the customer is willing to
 * accept any price in order to execute the order on the market. (See Best) 3 -
 * Best The order should be priced to execute at the best possible price. This
 * condition implies that the customer is willing to wait for a period of time
 * while the best price is negotiated. This type implies that the customer is
 * willing to accept any price in order to execute the order on the market. (See
 * Market). 4 - Limit The order should be priced at the given price. 5 - Limit
 * or Better The order should be priced at the given price or better. This type
 * implies that the customer is willing to wait for a period a time while the
 * price is negotiated. 6 - Funari The order should be priced at the given price
 * until the close of the market, at which time the type is converted to Market
 * type. (See Market). 7 - Discretion The order should be priced within a
 * limited range of prices based upon the given price and a given number of
 * price ticks. The following formula can be applied when matching this order:
 * (given price + given price tick) >= matching price >= (given price - given
 * price tick). Price=LimitPrice, TriggerPrice=Range (price ticks). Market If
 * Touched the type should be converted to Market type when the market reaches
 * the given price. (See Stop) 8 - Stop The type should be converted to Market
 * type when the given stop price is bid / asked, or a trade occurs at or above
 * / below the given stop price. (See Market If Touched). Price=null,
 * TriggerPrice=Stop Price 9 - Stop Limit The type should be converted to Limit
 * type at the given price when the given stop price is bid / asked, or a trade
 * occurs at or above / below the given stop price. (See Stop). Price=LimitPrice
 * to place order at 10 - Quoted The order should be priced at the previously
 * quoted price. This type implies that the order price was previously
 * negotiated. 11 - Last The order should be priced at the last sale price. This
 * type implies that the customer is willing to accept a price outside the
 * current market due to market movements, i.e. the market may move away from
 * the last sale price. 12 - Mid The order should be priced at the mid-price of
 * the inside quote. 13 - Current The order should be priced at the current
 * market price. This type implies that the customer is willing to accept a
 * price outside the current market due to market movements, i.e. the market may
 * move away from the current market price. (See also Market.) 14 - Bid Offer
 * The order should be priced at the current bid / offer. (See also Priority.)
 * 15 - Priority The order should be priced to become the best bid / offer, i.e.
 * highest priority order in the market. (See also Best.)
 * 
 * Margin
 * 
 * 
 * 
 * Execution Conditions
 * 
 * Define when and how an order should be executed by the electronic ordering
 * system. Electronic ordering systems use these conditions to select orders
 * during matching, to control the execution of trades, and to removing orders
 * from the order books.
 * 
 * Execution Types Execution types define how an order quantity is matched and
 * executed.
 * 
 * Partial � any order quantity can be matched; partially or completely Complete
 * � only the complete order quantity can be matched Minimum � any order
 * quantity can be matched which is greater than the given minimum quantity
 * Rentention Types Rentention types define what the electronic ordering system
 * should do with any remaining (unmatched) quantities. An order will have a
 * remaining quantity if it is not fully executed by the electronic ordering
 * system.
 * 
 * Retain � any remaining order quantity is retained until fully executed,
 * cancelled, or expired. Cancel � any remaining order quantity is cancelled
 * within the electronic ordering system
 * 
 * 
 * @author wlopes
 *
 */
public class OrderBook {

	// static variable single_instance of type Singleton
	//private static OrderBook instance = null;
	
	// List of Supply Order (Ask)
	private List<Order> listBuyAsk;
	private List<Order> listSellAsk;

	// List of Demand Order (Bid)
	private List<Order> listBuyBid;
	private List<Order> listSellBid;

	public OrderBook() {
		listBuyAsk = new ArrayList<>();
		listSellAsk = new ArrayList<>();

		listBuyBid = new ArrayList<>();
		listSellBid = new ArrayList<>();
	}


	//Oferta
	public void produce() throws InterruptedException {
		
		 while (true) {
			synchronized (this) {
				
				double producao = 1 + ( listBuyBid.size() / (double) (listBuyAsk.size() + 1));
				
				while ( listBuyAsk.size() >= 10 || producao > 1.5) {
					// wait for the consumer
					wait();
				}
				
				Order o = Producer.generateAsk();
					
				if(o.getType().equals("BUY")) {
					listBuyAsk.add(o);
				} 
//				else {
//					listSellAsk.add(o);
//				}
				
				System.out.println("++++++ Produce size = "+listBuyAsk.size()+ "----- Consume  size = "+ listBuyBid.size()+" produce = "+producao);
			
//				System.out.println(o.getInstrument().getRic() + "@" + o.getPrice() + " Type: " + o.getType());
				
				// notify the consumer
				notifyAll();
				Thread.sleep(1000);
			}
			
		 }
		
	}

	// Demanda
	public void consume() throws InterruptedException {
		
		while (true) {
			synchronized (this) {
				
				double demanda = listBuyBid.size() / (double) listBuyAsk.size() ;
				
				while (listBuyAsk.size() == 0 || demanda >= 1.2 || listBuyBid.size() >= 15) {
					// wait for the producer
					wait();
				}
				
				Order o = Consumer.generateBid();
				
				if(o.getType().equals("BUY")) {
					listBuyBid.add(o);
				} 
//				else {
//					listSellBid.add(o);
//				}
				
				System.out.println("----- Consume  size = "+ listBuyBid.size()+" ++++++ Produce size = "+listBuyAsk.size()+" Demanda = "+demanda);
			
				//System.out.println(o.getInstrument().getRic() + "@" + o.getPrice() + " Type: " + o.getType());
				
				// notify the producer
				notifyAll();
				Thread.sleep(1000);
			}
			
		 }
		
	}
	
	//Match
	public void match() throws InterruptedException {
		 while (true) {
			synchronized (this) {
				while (listBuyAsk.size() == 0 || listBuyBid.size() == 0) {
					// wait for the producer
					wait();
				}
				
				List<Order> newListA = new ArrayList<>();
				List<Order> newListB = new ArrayList<>();
				
				for(int i =0; i < listBuyAsk.size(); i++) {
					for(int j=0;j<listBuyBid.size(); j++) {
						if(listBuyAsk.get(i).getPrice() == listBuyBid.get(j).getPrice()) {
							System.out.println("Executado: "+listBuyAsk.get(i).getId()+" price = "+listBuyAsk.get(i).getPrice()+ " "+ listBuyAsk.get(i).getInstrument().getRic());
							System.out.println("List size antes "+listBuyAsk.size());
							listBuyAsk.remove(i);
							listBuyBid.remove(j);
							System.out.println("List size Depois "+listBuyAsk.size());
						}
					}
				}
				
				// notify the producer
				notifyAll();
				Thread.sleep(200);
			}
			
		 }
		
	}
	
	
	public List<Order> getListBuyAsk() {
		return listBuyAsk;
	}

	public void setListBuyAsk(List<Order> listBuyAsk) {
		this.listBuyAsk = listBuyAsk;
	}

	public List<Order> getListSellAsk() {
		return listSellAsk;
	}

	public void setListSellAsk(List<Order> listSellAsk) {
		this.listSellAsk = listSellAsk;
	}

	public List<Order> getListBuyBid() {
		return listBuyBid;
	}

	public void setListBuyBid(List<Order> listBuyBid) {
		this.listBuyBid = listBuyBid;
	}

	public List<Order> getListSellBid() {
		return listSellBid;
	}

	public void setListSellBid(List<Order> listSellBid) {
		this.listSellBid = listSellBid;
	}

}
