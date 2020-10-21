package com.organon.oms.pricing;

import java.util.ArrayList;
import java.util.List;

public class ListHandler {
	
	public static <T> List<T> firstList(List<T> list, int period) {
		List<T> l = new ArrayList<>();
		for (int i = 0; i < period + 1; i++) {
			l.add(list.get(i));
		}
		
		return l;
	}
	
	public static <T> List<T> secondList(List<T> list, int period) {
		List<T> l = new ArrayList<>();
		for (int i = period; i < list.size(); i++) {
			l.add(list.get(i));
		}
		return l;
	}

}
