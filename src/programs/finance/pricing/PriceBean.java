package programs.finance.pricing;

import java.util.Date;

public class PriceBean {

	private long timeline;
	private Date date;
	private double open;
	private double low;
	private double high;
	private double close;
	private double adjClose;
	private long volume;

	public PriceBean(long timeline, Date date, double open, double low, double high, double close, double adjClose,
			long volume) {
		super();
		this.timeline = timeline;
		this.date = date;
		this.open = open;
		this.low = low;
		this.high = high;
		this.close = close;
		this.adjClose = adjClose;
		this.volume = volume;
	}

	public long getTimeline() {
		return timeline;
	}

	public void setTimeline(long timeline) {
		this.timeline = timeline;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(double adjClose) {
		this.adjClose = adjClose;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

}
