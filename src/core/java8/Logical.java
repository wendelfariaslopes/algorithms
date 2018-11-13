package core.java8;

import java.util.function.*;

public class Logical {

	public static void main(String[] args) {
		Predicate<Integer> isNegative = n -> (n < 0);
		
		Predicate<Boolean> isTrue = b -> (b);
		
		Predicate<Boolean> implication = p -> (p==true);
		
		boolean fb = true;
		
		System.out.println(isTrue.and(isTrue).test(fb));
		System.out.println(isTrue.negate().test(fb));

	}

}
