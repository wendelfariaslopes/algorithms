package com.organon.oms.pricing;

import java.util.ArrayList;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;

public class IndicatorTechnical {

	public static void main(String[] args) {
		
		List<PriceBean> listPriceBean = new ArrayList<>();
		
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.66, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.85, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.93, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.98, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 17.08, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 17.03, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 17.09, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.76, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.67, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.72, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.86, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.85, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.87, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.90, 1));
		listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.92, 1));
		
		double d = getRSI(listPriceBean);
		
		System.out.println(d);

	}
	
	
	public static double getMACD() {
		return 2.0;
	}
	
	public static double getRSI(List<PriceBean> listPriceBean){

		//listPriceBean = new ArrayList<>();
		
		//listPriceBean.add(new PriceBean(1, new Date(), 0.0, 0.0, 0.0, 0.0, 16.66, 1));
	
		
		List<Double> listUpwardMov = new ArrayList<>();
		List<Double> listDownwardMov = new ArrayList<>();
		
		for(int i = 1; i< listPriceBean.size(); i++) {
			double previousPrice = listPriceBean.get(i-1).getAdjClose();
			double price = listPriceBean.get(i).getAdjClose();
			double change = price - previousPrice;
			if(change > 0) {
				listUpwardMov.add(change);
			}else {
				listDownwardMov.add(-1 * change);
			}
		}
		
		DoubleSummaryStatistics statsUp = listUpwardMov.stream().mapToDouble((x)->x).summaryStatistics();
		DoubleSummaryStatistics statsDown = listDownwardMov.stream().mapToDouble((x)->x).summaryStatistics();
		
		//System.out.println(statsUp);
		//System.out.println(statsDown);
		
		
		int size = listPriceBean.size()-1;
		
		double averageUp = statsUp.getSum()/size;
		double averageDown = statsDown.getSum()/size;
		
		double RS = averageUp/averageDown; //Relative Strenght
		
	//	System.out.println("Raw RSI "+RS);
		
		double rsi = 100 - 100/(RS+1);
		
		
		return rsi;
	}

}
