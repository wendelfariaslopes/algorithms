package algorithms.trading.orderbook;

import quickfix.field.AvgPx;
import quickfix.field.CumQty;
import quickfix.field.ExecID;
import quickfix.field.ExecType;
import quickfix.field.LeavesQty;
import quickfix.field.OrdStatus;
import quickfix.field.OrderID;
import quickfix.field.Side;
import quickfix.fix44.ExecutionReport;

public class Trade2 extends ExecutionReport {

    private String symbol;
    private String id;
    private double qty;
    private double price;
    private String time;
    private String buyOrdId;
    private String sellOrdId;
    

	public Trade2() {
		super();

	}

	public Trade2(OrderID orderID, ExecID execID, ExecType execType, OrdStatus ordStatus, Side side,
			LeavesQty leavesQty, CumQty cumQty, AvgPx avgPx) {
		super(orderID, execID, execType, ordStatus, side, leavesQty, cumQty, avgPx);
	}


	public Trade2(String symbol, String id, double qty, double price, String time, String buyOrdId, String sellOrdId) {
		super();
		this.symbol = symbol;
		this.id = id;
		this.qty = qty;
		this.price = price;
		this.time = time;
		this.buyOrdId = buyOrdId;
		this.sellOrdId = sellOrdId;
	}



//	public String getSymbol() {
//		return symbol;
//	}
//
//	public void setSymbol(String symbol) {
//		this.symbol = symbol;
//	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public double getQty() {
		return qty;
	}

	public void setQty(double qty) {
		this.qty = qty;
	}

//	public double getPrice() {
//		return price;
//	}
//
//	public void setPrice(double price) {
//		this.price = price;
//	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBuyOrdId() {
		return buyOrdId;
	}

	public void setBuyOrdId(String buyOrdId) {
		this.buyOrdId = buyOrdId;
	}


	public String getSellOrdId() {
		return sellOrdId;
	}

	public void setSellOrdId(String sellOrdId) {
		this.sellOrdId = sellOrdId;
	}
    
}
