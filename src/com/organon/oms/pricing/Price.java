package com.organon.oms.pricing;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class Price {
	
	private static SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

	static List<HistoricalQuote> histQuotes;

	public static void main(String[] args) {

		Gson g = new Gson();

		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.MONTH, -2); // from 1 UNIT ago

		Stock stock = null;
		try {
			stock = YahooFinance.get("TSLA");
			histQuotes = stock.getHistory(from, to, Interval.DAILY);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<PriceBean> listPriceBean = new ArrayList<>();

	
		for (HistoricalQuote hq : histQuotes) {
			PriceBean pb = new PriceBean(
					hq.getDate().getTimeInMillis(), hq.getDate().getTime(), 
					hq.getOpen().doubleValue(), hq.getLow().doubleValue(),
					hq.getHigh().doubleValue(), hq.getClose().doubleValue(), 
					hq.getAdjClose().doubleValue(), hq.getVolume());
			listPriceBean.add(pb);
			
			
			


			//System.out.println(pb.getTimeline() + "=" + g.toJson(pb, PriceBean.class));
		}
		
//		listPriceBean.forEach(pb -> System.out.println(g.toJson(pb.getDate(), Date.class).toString().replaceAll("\"", "").replace(", 12:00:00 AM", "")));
//		System.out.println();
//		listPriceBean.forEach(pb -> System.out.println(pb.getAdjClose()));
		
		//Math.max() takes care of the case when l contains fewer than three elements.
		//
		
		//List<PriceBean> tailListPriceBean = listPriceBean.subList(Math.max(listPriceBean.size() - lastDays, 0), listPriceBean.size());
		//List<PriceBean> tailListPriceBean2 = listPriceBean.subList(Math.max(listPriceBean.size() - lastDays - 1, 0), listPriceBean.size() - 1);
		
		
		
	//	Collections.reverse(listPriceBean);
		
	
		
//		tailListPriceBean.forEach(l-> System.out.println(g.toJson(l, PriceBean.class)));
//		System.out.println();
//		System.out.println("Day "+tailListPriceBean.get(tailListPriceBean.size()-1).getDate() +" RSI = "+IndicatorTechnical.getRSI(tailListPriceBean));
//		System.out.println();
//		tailListPriceBean2.forEach(l-> System.out.println(g.toJson(l, PriceBean.class)));
		
		int lastDays = 15;
		int lastRSI = 20;
		for(int i = 0; i < lastRSI; i++) {
			List<PriceBean> tailListPriceBean = listPriceBean.subList(Math.max(listPriceBean.size() - lastDays - i, 0), listPriceBean.size() - i);
			tailListPriceBean.forEach(e -> System.out.println(g.toJson(e, PriceBean.class)));
			System.out.println(formatter.format(tailListPriceBean.get(tailListPriceBean.size()-1).getDate()) +" RSI = "+round(IndicatorTechnical.getRSI(tailListPriceBean),2));
			System.out.println();
		}
	
	}
	
	public static double round(double value, int places) {
	    if (places < 0) throw new IllegalArgumentException();

	    long factor = (long) Math.pow(10, places);
	    value = value * factor;
	    long tmp = Math.round(value);
	    return (double) tmp / factor;
	}

}
