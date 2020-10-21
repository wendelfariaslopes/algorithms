package com.organon.oms.pricing;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.function.Predicate;

import com.google.gson.Gson;

public class RSI extends PriceBean {

	private double change;
	private double gain;
	private double loss;
	private double averageGain;
	private double averageLoss;
	private double RS;
	private double RSI;
	private List<RSI> listRSI;

	
	public RSI(double change, double gain, double loss, double averageGain, double averageLoss, double rS, double rSI) {
		super();
		this.change = change;
		this.gain = gain;
		this.loss = loss;
		this.averageGain = averageGain;
		this.averageLoss = averageLoss;
		RS = rS;
		RSI = rSI;
	}

	public RSI(List<PriceBean> listPriceBean, int period) {
		
		List<PriceBean> firstList = firstList(listPriceBean, period);
		List<PriceBean> secondList = secondList(listPriceBean, period);

		double sumUP = 0.0;
		double sumDown = 0.0;
		double change  = 0.0;

		for (int i = 1; i < firstList.size(); i++) {
			double previousPrice = firstList.get(i - 1).getAdjClose();
			double price = firstList.get(i).getAdjClose();
			change = price - previousPrice;
			if (change > 0) {
				sumUP+=change;
			} else {
				sumDown+=change;
			}
		}

		double gainAverage = sumUP/period;
		double lossAverage = sumDown/period;
		double RS = gainAverage / (-1*lossAverage);
		double RSI = 100 - 100 / (1 + RS);
		
		this.listRSI = new ArrayList<>();
		double gain = change > 0.0?change:0.0;
		double loss = change < 0.0?change:0.0;

		RSI firstRSI = new RSI(change, gain, loss, gainAverage, lossAverage, RS, RSI);
		firstRSI.setTimeline(firstList.get(firstList.size()-1).getTimeline());
		firstRSI.setDate(firstList.get(firstList.size()-1).getDate());
		
		this.listRSI.add(firstRSI);

		double gainAverage2 = firstRSI.averageGain;
		double lossAverage2 = (-1*firstRSI.averageLoss);
		
		int factor = period - 1;
		
		for (int i = 1; i < secondList.size(); i++) {
			double previousPrice = secondList.get(i - 1).getAdjClose();
			double price = secondList.get(i).getAdjClose();
			double changeS = price - previousPrice;
			double lossChange = 0.0;
			if (changeS > 0) {
				gainAverage2 = (gainAverage2 * factor + changeS) / period;
				lossAverage2 = (lossAverage2 * factor) / period;
			} else {
				lossChange = (-1) * changeS;
				gainAverage2 = (gainAverage2 * factor) / period;
				lossAverage2 = ((lossAverage2 * factor) + lossChange) / period;
			}
			
			double RS2 = gainAverage2 / lossAverage2;
			double RSI2 = 100 - 100 / (RS2 + 1);
		
			RSI rsi = new RSI(changeS, changeS, lossChange, gainAverage2, lossAverage2, RS2, RSI2);
			rsi.setTimeline(secondList.get(i).getTimeline());
			rsi.setDate((secondList.get(i).getDate()));
			
			this.listRSI.add(rsi);
		}
		
	}
	
	public static <T> List<T> firstList(List<T> list, int period) {
		List<T> firstList = new ArrayList<>();
		for (int i = 0; i < period + 1; i++) {
			firstList.add(list.get(i));
		}
		
		return firstList;
	}
	
	public static <T> List<T> secondList(List<T> list, int period) {
		List<T> sList = new ArrayList<>();
		for (int i = period; i < list.size(); i++) {
			sList.add(list.get(i));
		}
		return sList;
	}

	public List<RSI> getListRSI() {
		return listRSI;
	}


	public static Predicate<PriceBean> between(long start, long end) {
		return n -> n.getTimeline() > start && n.getTimeline() < end;
	}

	public static Predicate<PriceBean> between(Date start, Date end) {
		return between(start.getTime(), end.getTime());
	}

	public static Predicate<PriceBean> between(String start, String end) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Date dateStart = null, dateEnd = null;
		try {
			dateStart = sdf.parse(start);
			dateEnd = sdf.parse(end);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return between(dateStart, dateEnd);
	}

	

	public double getRS() {
		return RS;
	}

	public void setRS(double rS) {
		RS = rS;
	}

	public double getRSI() {
		return RSI;
	}

	public void setRSI(double rSI) {
		RSI = rSI;
	}

	public double getChange() {
		return change;
	}

	public void setChange(double change) {
		this.change = change;
	}

	public double getGain() {
		return gain;
	}

	public void setGain(double gain) {
		this.gain = gain;
	}

	public double getLoss() {
		return loss;
	}

	public void setLoss(double loss) {
		this.loss = loss;
	}

	public double getAverageGain() {
		return averageGain;
	}

	public void setAverageGain(double averageGain) {
		this.averageGain = averageGain;
	}

	public double getAverageLoss() {
		return averageLoss;
	}

	public void setAverageLoss(double averageLoss) {
		this.averageLoss = averageLoss;
	}

	public void setListRSI(List<RSI> listRSI) {
		this.listRSI = listRSI;
	}

	public String toString() {
		return new Gson().toJson(this);
	}

}
