package core.thread.list;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class OrderGrouping {
	
	public static void main(String []args) {
		
		
		List<Order> items = Arrays.asList(
				new Order("apple", 5, new BigDecimal("9.99")),
				new Order("apple", 10, new BigDecimal("9.97")),
				new Order("apple", 12, new BigDecimal("9.92")),
				new Order("apple", 10, new BigDecimal("9.99")),
				new Order("apple", 11, new BigDecimal("9.93")),
				new Order("apple", 10, new BigDecimal("9.99")),
				new Order("apple", 10, new BigDecimal("9.99")),
				new Order("apple", 23, new BigDecimal("9.91")),
				new Order("apple", 5, new BigDecimal("9.99")),
				new Order("MTFL", 10, new BigDecimal("9.97")),
				new Order("MTFL", 12, new BigDecimal("9.92")),
				new Order("MTFL", 10, new BigDecimal("9.99")),
				new Order("apple", 11, new BigDecimal("9.93")),
				new Order("apple 2", 10, new BigDecimal("9.99")),
				new Order("apple 2", 10, new BigDecimal("9.99")),
				new Order("apple 3", 23, new BigDecimal("9.91"))
				);
		

		Map<String, Long> counting = items.stream().collect(
                Collectors.groupingBy(Order::getStock, Collectors.counting()));

        System.out.println(counting);

        Map<String, Integer> sum = items.stream().collect(
                Collectors.groupingBy(Order::getStock, Collectors.summingInt(Order::getQuantity)));

        System.out.println(sum);
        
        
      //group by price
        Map<BigDecimal, List<Order>> groupByPriceMap = 
			items.stream().collect(Collectors.groupingBy(Order::getPrice));

        System.out.println(groupByPriceMap);

		// group by price, uses 'mapping' to convert List<Order> to Set<String>
        Map<BigDecimal, Set<String>> result =
                items.stream().collect(
                        Collectors.groupingBy(Order::getPrice,
                                Collectors.mapping(Order::getStock, Collectors.toSet())
                        )
                );

        System.out.println(result);
	}

}


