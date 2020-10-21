package core.junit;

import static org.junit.Assert.assertTrue;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

public class StringsTest {
	
	@ParameterizedTest
	@ValueSource(strings= {""," ","   "})
	void isBlank_ShouldReturnTrueForNullOrBlank(String input) {
		assertTrue(Strings.isBlank(input));
	}
	
	@ParameterizedTest
	@NullSource
	void isBlank_ShouldReturnTrueForNullInputs(String input) {
		assertTrue(Strings.isBlank(input));
	}
	
	@ParameterizedTest
	@EmptySource
	void isBlank_ShouldReturnTrueForEmptyInputs(String input) {
		assertTrue(Strings.isBlank(input));
	}
	
	@ParameterizedTest
	@NullAndEmptySource
	void isBlank_ShouldReturnTrueForNullOrBlankNewTest(String input) {
		assertTrue(Strings.isBlank(input));
	}

}
