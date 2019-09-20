package core.thread;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class VisibilityTest {
	

	private volatile boolean fieldVolatile;
	private boolean field;
	
	//a normal field (no final or volatile) without any kind of synchronization
	//don't pass in test because the data visibility by another thread is not guaranteed by normal field
	@Test
	public void testNormalField() throws Exception {
	    new Thread(() -> {
	        while (!field);
	        System.out.println("Done");
	    }).start();
	    
	    TimeUnit.SECONDS.sleep(1);
	    
	    field = true;
	}
	
	@Test
	public void testVolatileField() {
	    new Thread(() -> {
	        while (!fieldVolatile);
	        System.out.println("Done with Volatile");
	    }).start();
	    fieldVolatile = true;
	}

}
