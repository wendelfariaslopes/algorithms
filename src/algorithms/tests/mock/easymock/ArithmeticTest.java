package algorithms.tests.mock.easymock;

import org.easymock.EasyMockRule;
import org.easymock.EasyMockSupport;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.assertEquals;

public class ArithmeticTest extends EasyMockSupport {
	
	@Rule
	public EasyMockRule rule = new EasyMockRule(this);
	
	//@Mock
	private Arithmetics arithmetics; // Step 1 - Create the mock
	
	//@TestSubject
	private ArithmeticImp ai; // Step 2 - Have it set to the tested class
	
	@Before
	public void setUp() {
		ai = partialMockBuilder(ArithmeticImp.class).addMockedMethods("getValue1()","getValue2()").createMock();
	}
	
	@Test
	public void testSum() {
		
		// Step 3 - Record what we expected the mock to do
		expect(ai.getValue1()).andReturn(2);
		expect(ai.getValue2()).andReturn(2);
		//arithmetics.sum(2,2);
		
		// Step 4 - Tell all mocks we are now doing the actual testing
		replayAll();
		
		// Step 5 - The Test itself
		assertEquals(4, ai.sum());
		//ai.sum(2,2);

		// Step 6 - Make sure everything that was supposed to be called was called
		verifyAll();
	}
	
}
