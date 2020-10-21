package com.organon.oms.pricing;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class Indicator {

	public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
		return list.stream().filter(predicate).collect(Collectors.toList());
	}
	
	public static <T> boolean validatePeriod(List<T> list, int period) {
		return period < list.size() - 1;
	}
	
	public static <T> List<T> firstList(List<T> list, int period) {
		List<T> firstList = new ArrayList<>();
		for (int i = 0; i < period + 1; i++) {
			firstList.add(list.get(i));
		}
		
		return firstList;
	}
	
	public static <T> List<T> secondList(List<T> list, int period) {
		List<T> sList = new ArrayList<>();
		for (int i = period; i < list.size(); i++) {
			sList.add(list.get(i));
		}
		return sList;
	}

}
