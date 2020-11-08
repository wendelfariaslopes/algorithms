package core.java8;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Convert;

import edu.emory.mathcs.backport.java.util.Arrays;

public class Lists {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<>();
		list.add("item 1");
		list.add("item 2");
		
		String[] strings = convert(list);
		for (String string : strings) {
			System.out.println(string);
		}
	}
	
	public static String[] convert(List<String> strings) {
		return strings.stream().toArray(String[]::new);
	}

}
