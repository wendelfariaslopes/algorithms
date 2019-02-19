package interview.questions.fragments;
/**
 * PiThread.  A thread that computes a section of the area defining the
 * approximation of Pi.
 */
class PiThread extends Thread {
	private int N;			// the total number of samples/iterations 
	private int start;		// where this thread should start (included)
	private int stop;		// where this thread should stop iterating (excluded)
	private double result;	// the part of the approximation computed.
	
	
	/**
	 * Constructor.
	 * @param n:	the total number of samples used by the main program.
	 * @param sta	the starting point for the iterations
	 * @param sto	the stopping point for the iterations
	 */
	public PiThread( int N, int start, int stop ) {
		super( "Thread-"+start+"-"+stop ); // give a name to the thread
		this.N 		= N;
		this.start 	= start;
		this.stop 	= stop;
	}
	
	/**
	 * inspector. 
	 * @return result computed.
	 */
	public double getResult() {
		return result;
	}

	/**
	 * the function that computes the elementary term
	 * @param x
	 * @return
	 */
	double f( double x ) {
		return 4.0 / ( 1 + x*x );
	}
	
	/**
	 * the main RUN() method of the thread.  It will be automatically called
	 * by start().  Does all the work, iterating from start to stop and putting
	 * the finished slice of the sum in result.
	 */
	@Override
	public void run() {
		//System.out.println( getName() + " starting..." );
		result = 0;
		double deltaX = 1.0/N;
		for ( int i=start; i<stop; i++ ) 
			result += f( i*deltaX );
				
		result *= deltaX;
		//System.out.println( getName() + " done: partial result = " + result );
	}

}