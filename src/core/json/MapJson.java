package core.json;

import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import com.google.gson.Gson;

public class MapJson {

	public static void main(String[] args) {
//		Map<String,Double> map = new TreeMap<>();
//		
//		for(int i=0; i< 10 ; i++) {
//			Double d = (double) i * 10 * new Random().nextDouble();
//			map.put("sma"+i, d);
//		}
//		
		Gson g = new Gson();

		
	}
	
	public static class SMA {
		private Map<String,Double> sma;
		public SMA() {
			sma = new TreeMap<>();
			for(int i=0; i< 10 ; i++) {
				Double d = (double) i * 10 * new Random().nextDouble();
				sma.put("sma"+i, d);
			}
		}
		public Map<String, Double> getSma() {

			return sma;
		}
		public void setSma(Map<String, Double> sma) {
			this.sma = sma;
		}
	}

}
