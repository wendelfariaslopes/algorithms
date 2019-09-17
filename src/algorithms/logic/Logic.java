package algorithms.logic;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class Logic {
	
	
	public boolean doesDo(boolean p, boolean q) {
		return p || q;
	}
	
	@Test
	public void testLogic() {
		
		assertTrue(doesDo(false,false));
		
	}

}
