package core.java8;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;

import edu.emory.mathcs.backport.java.util.Arrays;

public class Lists {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		list.add("item 1");
		list.add("1");
		list.add("item 1");
		list.add("item 1");
		list.add("item 1");
		list.add("item 1");
		list.add("item 1");
		
//		String[] strings = convert(list);
//		for (String string : strings) {
//			System.out.println(string);
//		}
		
		System.out.println(findInList(list));
		
	}
	
	public static String[] convert(List<String> strings) {
		return strings.stream().toArray(String[]::new);
	}
	
	
	public static boolean findInList(List<String> list) {
		for (String string : list) {
			if(string.startsWith("PreTrade-BestFit"))
				return true;
		}
		return false;
	}

}
