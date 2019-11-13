package algorithms.collections;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.ArrayList;
import java.util.Collections;

public class FasterMethod {

	public static void main(String[] args) {

		long start = System.currentTimeMillis();
		List<String> list = listString(10000000);
		long end = System.currentTimeMillis();
		long time = end - start;
		System.out.println("Time to product list: " + time);

		System.out.println("Sorted list ");

		start = System.currentTimeMillis();
		Collections.sort(list);
		end = System.currentTimeMillis();
		time = end - start;
		System.out.println("Time to sort list by Collections.sort(list): " + time);

		Collections.shuffle(list);

		start = System.currentTimeMillis();
		list.sort(null);
		end = System.currentTimeMillis();
		time = end - start;
		System.out.println("Time to sort list by sort(null): " + time);

		Collections.shuffle(list);

		start = System.currentTimeMillis();
		list.parallelStream().sorted().collect(Collectors.toList());
		end = System.currentTimeMillis();
		time = end - start;
		System.out.println("Time to sort list by parallelStream().sorted(): " + time);

	}

	public static List<String> listString(int listSize) {
		List<String> list = new ArrayList<>();

		for (int i = 0; i < listSize; i++) {
			list.add(i + "-" + UUID.randomUUID().toString());
		}

		Collections.shuffle(list);

		return list;
	}

	/**
	 * Arrays.sort() method 
	 * 1) The Arrays class has sort() method which sorts the
	 * provides darray in natural order or according to the specified Comparator. 
	 * 2)
	 * The sort() method can be implemented using any sorting algorithm, provided
	 * that it is stable. 
	 * 3) Typically the algorithm is implemented using a
	 * Dual-Pivot Quicksort or Comparable TimSort which offers O(nlog(n))
	 * performance 
	 * 4) If two-pivot Quiksort is used, sort() performs faster than
	 * naive Quicksort algorihm and never results in quadratic performance. 
	 * 5) It can be used to sort objects and primitive arrays. 
	 * 6) The sortin is done sequentially. i.e. the whole array is sorted using single thread.
	 * 
	 */
	public static void testArraySort() {

	}

	/**
	 * Arrays.parallelSort() 
	 * 1) Like sort() method, Arrays.parallelSort() also sorts
	 * an array into ascending order or according to the specified Comparator. 
	 * 2) It
	 * is recent addition to the Arrays class with Java 8. 
	 * 3) It uses a sort-merge
	 * technique which divides the specified array into chunks of some size and sort
	 * each chunk individually. Finally the sorted chunks are merged using logic
	 * from Merge-sort algorithm 
	 * 4) Unlike sort() method, the sorting is done in
	 * parallel. i.e. several thread execute in parallel to sorts the chunk of the
	 * array. 
	 * 5) The algorithm uses the ForkJoin common pool to leverage
	 * concurrency.
	 * 6) The parallelSort() method is usually implemented using TimSort, Comparable TimSort, and DualPivotQuicksort.
	 * 7) It internally calls the Arrays.sort method typically when the size of a input reaches a minimum threshold.
	 * 8) The auxiliary space used by the algorithm is linear with respect to the size of the specified array.
	 * 9) It performas very fast than the sort() method on large input sizes. For small inputs, the overhead for parallelizations ins huge
	 * 10) Like sort() method, it can be used to sort objects and primitive arrays.
	 */

	/**
	 * ForkJoinPool
	 * 
	 * A ForkJoinPool provides the entry point for submissions from non-ForkJoinTask
	 * clients, as well as management and monitoring operations. A ForkJoinPool
	 * differs from others kinds of ExecutorService mainly by virtue of employing
	 * work-stealing: all threads in the pool attempt to find and execute tasks
	 * submitted to the pool and/or created by other active tasks
	 */

}
