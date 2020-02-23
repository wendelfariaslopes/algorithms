package algorithms.partition;

import java.util.Arrays;
import java.util.List;

public class Test {
	
	public static void main(String [] args) {
		
		final int chunkSize = 3;
		
		final List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17);

//		System.out.println(Partition.ofSize(numbers, 3));
//		System.out.println(Partition.ofSize(numbers, 2));
//		
//		//method 2 - Using Java 8 Stream API + grouping collector
//		final int chunkSize = 7;
//		final AtomicInteger counter = new AtomicInteger();
//		final Collection<List<Integer>> result = numbers.stream()
//		    .collect(Collectors.groupingBy(it -> counter.getAndIncrement() / chunkSize))
//		    .values();
//		System.out.println(result);
		int cycle = 0;
		for(int i=0; i< numbers.size(); i++) {
			// 
			int limit = chunkSize + i;
			
			if(!(limit >= numbers.size())) {
				++cycle;
				System.out.print("Cycle "+cycle+" -> { ");
				for(int g=i; g < chunkSize+i; g++ ) {
					System.out.print(numbers.get(g)+" ");
				}
				System.out.print("} \n");
			} 
			
		}
	
		
		
		
		
		
	}

}
