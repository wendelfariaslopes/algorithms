package com.organon.oms.pricing;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.google.gson.Gson;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

public class Price {

	static List<HistoricalQuote> histQuotes;

	public static void main(String[] args) {

		Gson g = new Gson();

		Calendar from = Calendar.getInstance();
		Calendar to = Calendar.getInstance();
		from.add(Calendar.YEAR, -1); // from 1 year ago

		Stock stock = null;
		try {
			stock = YahooFinance.get("GOOG");
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

			System.out.println(pb.getTimeline() + "=" + g.toJson(pb, PriceBean.class));
		}
	}

}
