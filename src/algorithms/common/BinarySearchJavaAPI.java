package algorithms.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BinarySearchJavaAPI {
	public static final Logger logger = LoggerFactory.getLogger(BinarySearchJavaAPI.class);

	public static void main(String args[]) { // creating List
		List<Integer> numbers = new ArrayList<Integer>(1000000);
		// List of 1M records
		// initializing List
		for (int i = 0; i < numbers.size(); i++) {
			numbers.add(new Integer(i));
		}
		// performing contains search
		long startTime = System.nanoTime();
		boolean isExist = numbers.contains(new Integer(1000000));
		long totalTime = System.nanoTime() - startTime;
		logger.info("Time to search 1Mth Record using contains() is {} nano seconds", totalTime);
		// performing binary search
		startTime = System.nanoTime();
		Collections.sort(numbers);
		// List needs to be sorted for Binary Search
		Integer number = Collections.binarySearch(numbers, new Integer(1000000));
		totalTime = System.nanoTime() - startTime;
		logger.info("Time to search 1Mth Record using binary search is {} nano seconds", totalTime);
	}

}
