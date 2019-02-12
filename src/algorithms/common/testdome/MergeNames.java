package algorithms.common.testdome;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Implement the uniqueNames method. When passed two arrays of names, it will
 * return an array containing the names that appear in either or both arrays.
 * The returned array should have no duplicates.
 * 
 * For example, calling MergeNames.uniqueNames(new String[]{'Ava', 'Emma',
 * 'Olivia'}, new String[]{'Olivia', 'Sophia', 'Emma'}) should return an array
 * containing Ava, Emma, Olivia, and Sophia in any order.
 * 
 * 
 * @author wendellopes
 * 
 *         Hint 1: The Stream.concat will merge two streams.
 *
 */
public class MergeNames {

	public static String[] uniqueNames(String[] names1, String[] names2) {
		
		
	
		return Stream.concat(Stream.of(names1), Stream.of(names2)).toArray(String []::new);
		// throw new UnsupportedOperationException("Waiting to be implemented.");

		
		
		// java 8
		// return Stream.of(names1,
		// names2).flatMap(Stream::of).distinct().toArray(String[]::new);

		// java 8
		// return Stream.concat(Arrays.stream(names1),
		// Arrays.stream(names2)).distinct().toArray(String[]::new);

		// java 7
		//return combine(names1, names2, true);

	}

	public static void main(String[] args) {
		String[] names1 = new String[] { "Ava", "Emma", "Olivia" };
		String[] names2 = new String[] { "Olivia", "Sophia", "Emma" };
		System.out.println(String.join(", ", MergeNames.uniqueNames(names1, names2))); // should print Ava, Emma,
																						// Olivia, Sophia
	}

	private static String[] combine(String[] names1, String[] names2) {
		String[] result = new String[names1.length + names2.length];
		System.arraycopy(names1, 0, result, 0, names1.length);
		System.arraycopy(names2, 0, result, names1.length, names2.length);
		return result;
	}

	public static String[] combine(String[] names1, String[] names2, boolean noRepetition) {

		if (noRepetition) {
			Set<String> set = new HashSet<>();
			for (String r : combine(names1, names2)) {
				set.add(r);
			}
			String[] result = new String[set.size()];
			result = set.toArray(new String[set.size()]);

			return result;
		} else {
			return combine(names1, names2);
		}

	}

}
