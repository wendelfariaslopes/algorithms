package com.organon.oms.pricing;

import java.util.ArrayList;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class AverageGainLoss {


	private double gainAverage;
	private double lossAverage;


	public AverageGainLoss(List<PriceBean> listPriceBean, int period) {

		List<Double> listUpwardMov = new ArrayList<>();
		List<Double> listDownwardMov = new ArrayList<>();

		for (int i = 1; i < period + 1; i++) {
			double previousPrice = listPriceBean.get(i - 1).getAdjClose();
			double price = listPriceBean.get(i).getAdjClose();
			double change = price - previousPrice;
			if (change > 0) {
				listUpwardMov.add(change);
			} else {
				listDownwardMov.add(-1 * change);
			}
		}

		DoubleSummaryStatistics statsUp = listUpwardMov.stream().mapToDouble((x) -> x).summaryStatistics();
		DoubleSummaryStatistics statsDown = listDownwardMov.stream().mapToDouble((x) -> x).summaryStatistics();

		this.gainAverage = statsUp.getSum() / period;
		this.lossAverage = statsDown.getSum() / period;
	}
	
	


	public AverageGainLoss(List<PriceBean> listPriceBean, double gainAverage, double lossAverage, int period) {
		super();
		
		for (int i = period; i < listPriceBean.size(); i++) {			
			double previousPrice = listPriceBean.get(i - 1).getAdjClose();
			double price = listPriceBean.get(i).getAdjClose();
			double change = price - previousPrice;
		
		}
		
		this.gainAverage = gainAverage * (period - 1);
		this.lossAverage = lossAverage * (period - 1);
	}




	public double getGainAverage() {
		return gainAverage;
	}

	public double getLossAverage() {
		return lossAverage;
	}



}
