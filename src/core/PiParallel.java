package core;


public class PiParallel {
	
	public static double calculate( int N ) {
	
		if ( N==0 ) N = 1000000;
	
	    //--- create two threads ---
		PiThread t1 = new PiThread( N, 0, N/3 );
		PiThread t2 = new PiThread( N, N/3, 2*N/3 );
		PiThread t3 = new PiThread( N, 2*N/3, N );

		
		//--- start two threads ---
		t1.start();
		t2.start();
		t3.start();
		//t4.start();
		
		//--- wait till they finish ---
		try {
			t1.join();
			t2.join();
			t3.join();
		//	t4.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//--- get results and sum up ---
		return t1.getResult() + t2.getResult()+t3.getResult() ;
	}
}
