package com.organon.oms.pricing;

import java.util.List;

public class MovingAverage {
	
	private double SMA;
	private double EMA;
	private double MACD;
	
	private List<MovingAverage> listMovimentAverage;
	
	public MovingAverage(double sMA, double eMA, double mACD) {
		super();
		SMA = sMA;
		EMA = eMA;
		MACD = mACD;
	}


	public MovingAverage(List<PriceBean> listPriceBean, int period) {
		
		List<PriceBean> firstList = ListHandler.firstList(listPriceBean, period);
		List<PriceBean> secondList = ListHandler.secondList(listPriceBean, period);
		double first_SMA = 0.0;
		double first_EMA = 0.0;
		double first_MACD = 0.0;
				
		for(PriceBean priceBean : firstList) {
			first_SMA+=priceBean.getAdjClose();
		}
		first_SMA = first_SMA / period;
		first_EMA = first_SMA;
		
		
		
		
		double next_SMA = first_SMA;
		for(PriceBean priceBean : secondList) {
			next_SMA = (next_SMA * (period-1) + priceBean.getAdjClose())/period;
		}
		

	}


	public double getSMA() {
		return SMA;
	}

	public void setSMA(double sMA) {
		SMA = sMA;
	}


	public double getEMA() {
		return EMA;
	}


	public void setEMA(double eMA) {
		EMA = eMA;
	}


	public double getMACD() {
		return MACD;
	}


	public void setMACD(double mACD) {
		MACD = mACD;
	}

}
