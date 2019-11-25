package algorithms.collections;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Grouping {

	public static void main(String[] args) {
		// 3 apple, 2 banana, others 1 List<String> items =
		List<String> list = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");

		// Grouping
		System.out.println("Grouping: " +grouping(list));

		// Sort a map and add to finalMap
		System.out.println("Sorting: "+sorting(list));

	}

	public static Map<String, Long> grouping(List<String> list) {
		return list.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
	}

	public static Map<String, Long> sorting(List<String> list) {

		Map<String, Long> finalMap = new LinkedHashMap<String, Long>();

		grouping(list).entrySet().stream().sorted(Map.Entry.<String, Long>comparingByValue().reversed())
				.forEachOrdered(e -> finalMap.put(e.getKey(), e.getValue()));

		return finalMap;
	}
	
	
	public static Map<String,Long> groupByCount(List<Order> list){
		
		return list.stream().collect(
                Collectors.groupingBy(Order::getStock, Collectors.counting()));
		
	}
	
	public static Map<String,Integer> groupByQuantity(List<Order> list){
		
		return list.stream().collect(Collectors.groupingBy(Order::getStock, Collectors.summingInt(Order::getQuantity)));
	}
	
	
	/**
	 * group by price
	 * @param list
	 * @return
	 */
	public static Map<BigDecimal, List<Order>> groupByPrice(List<Order> list){
		return list.stream().collect(Collectors.groupingBy(Order::getPrice));
	}
	
	/**
	 * group by price
	 * @param list
	 * @return
	 */
	public static Map<BigDecimal, List<Order>> groupByPriceUsingParallel(List<Order> list){
		return list.parallelStream().collect(Collectors.groupingBy(Order::getPrice));
	}
	
	
	/**
	 * 	Group by price, uses 'mapping' to convert List<Item> to Set<String>
	 * 
	 * @param list
	 * @return
	 */
	public static Map<BigDecimal, Set<String>> groupByPriceSet(List<Order> list){
		return list.stream().collect(
                Collectors.groupingBy(Order::getPrice,
                        Collectors.mapping(Order::getStock, Collectors.toSet())
                )
        );
	}
	
	
	public static Map<BigDecimal, List<Order>> sortedMap(Map<BigDecimal, List<Order>> map){
		return map.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	}
	
	public static Map<BigDecimal, List<Order>> sortedMapUsingParallel(Map<BigDecimal, List<Order>> map){
		return map.entrySet().parallelStream()
                .sorted(Map.Entry.comparingByKey())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
	}

}
