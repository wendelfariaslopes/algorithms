package com.organon.ems;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class ExecutionSimulator {

	public static void main(String[] args) {

		Map<String,Double> map = new TreeMap<>();
		double trend = 0.1;
		double increment = 0;
		
		double trendChangeCEO = 0.2;
		double incrementCEOChange = 0;
		
		for(int i=0; i < 100; i++) {
			increment+=trend;
			
			if(i > 40 && i < 100) {
				incrementCEOChange+=trendChangeCEO;
			}
		
			map.put(i+" GOOGLE", getRandom(10 + increment, 11 + increment) );//crescent
			map.put(i+" APPLE", getRandom(20 - increment + incrementCEOChange, 21 - increment+ incrementCEOChange));//decrescent
		
		}
		
		map.forEach((k,v) -> System.out.println(k +" : "+v));
		
	}
	
	public static Double getRandom(double min, double max) {
		Random r = new Random();
		return min + (max - min) * r.nextDouble();
	}

}
