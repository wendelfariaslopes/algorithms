package com.organon.oms.pricing;

import java.util.ArrayList;
import java.util.List;

public class SMA extends PriceBean {

	private double value;
	private List<SMA> timeSeries = new ArrayList<>();

	public SMA(double value) {
		super();
		this.value = value;
	}

	public SMA(List<PriceBean> listPriceBean, int period) {

		List<PriceBean> firstList = ListHandler.firstList(listPriceBean, period);
		List<PriceBean> secondList = ListHandler.secondList(listPriceBean, period);

		double first_SMA = 0.0;
		for (PriceBean priceBean : firstList) {
			first_SMA += priceBean.getAdjClose();
		}
		first_SMA = first_SMA / period;
		
		this.timeSeries.add(new SMA(first_SMA));
		
		double next_SMA = first_SMA;
		for (PriceBean priceBean : secondList) {
			next_SMA = (next_SMA * (period - 1) + priceBean.getAdjClose()) / period;
			this.timeSeries.add(new SMA(next_SMA));
		}

	}

	public List<SMA> getTimeSeries() {
		return timeSeries;
	}

	public double getValue() {
		return value;
	}

}
