package algorithms.ai.ml;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;

public class DataLoader {

	public static void main(String[] args) {

		Gson g = new Gson();
		Map<String, String> map = Property.dataSerie();
		List<PriceBean> list = new ArrayList<>();
	
		map.forEach((k,v) -> {
			list.add(g.fromJson(v, PriceBean.class));
		});

		for(int i = 0; i < list.size(); i++) {
			PriceBean priceBean = list.get(i);
			System.out.print(priceBean.getTimeline()+ " ");
			if(i+1 < list.size()) {
				PriceBean priceBeanNext = list.get(i+1);
				
				double roi = roi(priceBean.getAdjClose(), priceBeanNext.getAdjClose());
				
				System.out.println(priceBean.getAdjClose()+ " " + priceBeanNext.getAdjClose()+" roi: "+roi+ " "+ (roi < 0.0 ? -1: 1));
			}
		}
	}
	
	public static double roi(double valuePresent, double valueFuture) {
		return ((valueFuture-valuePresent)/valuePresent) * 100;
	}

	

}
