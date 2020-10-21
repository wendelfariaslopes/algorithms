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
	
//	public static double getRSI2(List<PriceBean> listPriceBean){
//
//		AverageGainLoss AVG = new AverageGainLoss(listPriceBean);
//		
//		double RS = AVG.getFirstGainAverage()/AVG.getFirstLossAverage(); 
//		
//		double rsi = 100 - 100/(RS+1);
//		
//		return rsi;
//	}
//	
//	public static double getRSI2(List<PriceBean> listPriceBean, int period){
//
//		AverageGainLoss AVG = new AverageGainLoss(listPriceBean, period);
//		
//		double RS = AVG.getFirstGainAverage()/AVG.getFirstLossAverage(); 
//		
//		double rsi = 100 - 100/(RS+1);
//		
//		return rsi;
//	}
	
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
	
	


	public static double getVWAP(List<PriceBean> listPriceBean) {
		
		//step 1) Choose your time frame (tick chart, 1 minute, 5 minutes, etc.)
		
		//step 2) Calculate the typical price for the first period (and all periods in the day following). 
		//		  Typical price is attained by taking adding the high, low and close, and dividing by three: (H+L+C)/3
		
		//step 3) Multiply this typical price by the volume for that period. This will give you a value called TPV.
		
		//step 4) Keep a running total of the TPV values, called cumulative-TPV. This is attained by continually adding 
		//the most recent TPV to the prior values (except for the first period, since there will be no prior value). 
		// This figure should get larger as the day progresses.
		
		
		//step 5) Keep a running total of cumulative volume. Do this by continually adding the most recent volume to the prior volume. 
		//This number should also get larger as the day progresses.
		
		//step 6) Calculate VWAP with your information: [cumulative TPV ÷ cumulative volume]. This will provide 
		//a volume weighted average price for each period and will provide the data to create the flowing line that 
		//overlays the price data on the chart.
		
	
		double cumulativeTPV = 0.0;
		double cumulativeVolume = 0.0;
		
		for (PriceBean priceBean : listPriceBean) {
			double typicalPrice = (priceBean.getHigh()+priceBean.getLow()+priceBean.getClose())/3;
			double volume = priceBean.getVolume();
			double TPV = typicalPrice*volume;
			cumulativeTPV+=TPV;
			cumulativeVolume+=volume;
			
		}
		
		return cumulativeTPV/cumulativeVolume;
	}
}
