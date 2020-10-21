package core.junit;


import static org.junit.Assert.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class NumberTest {
	
	@ParameterizedTest
	@ValueSource(ints= {1,2,3,5-3123, Integer.MAX_VALUE})
	void isOdd_ShouldReturnTrueForOddNumbers(int number) {
		assertTrue(Numbers.isOdd(number));
	}
	

}
