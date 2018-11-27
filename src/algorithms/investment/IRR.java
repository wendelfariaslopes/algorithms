package algorithms.investment;

/**
 * 
 * IRR (Internal Rate of Return) is the most widely used financial indicator
 * while assessing return on an investment or a project. It is defined as the
 * discount rate which makes the net present value of the cash flows from the
 * investment equal to zero. In Excel, we have IRR formula to compute the
 * discount rate based on the cashflows for an investment/project. This program
 * provided the IRR rate to an accuracy of upto 6 decimal points.
 * 
 * For IRR calculation, we need the following inputs:
 * 
 * The cash flows with which we need to compute the IRR The period for which we
 * are computing the IRR for the above cashflows With these inputs, we compute
 * the NPV value and check if it is zero or to the nearest precision possible.
 * The following parameters are used for the computation:
 * 
 * LOW_RATE: The initial rate with which we compute the NPV. This is # defined
 * as 0.01 (1%) HIGH_RATE: The highest rate upto which we should consider for
 * computing NPV. This is # defined as .5 (50%) MAX_ITERATION: There is always a
 * possibility of not being able to arrive at the rate for certain cashflows.
 * This variable acts as a stopper for the number of iterations the code should
 * check for NPV so as to ensure that the program doesn't go for an infinite
 * loop. PRECISION_REQ: NPV value will not normally hit zero. We can find the
 * NPV value with a precision upto a certain value. This is set as 0.00000001.
 * Hence when the computed NPV is below this value, the calculation stops and
 * the rate used will be the IRR. Every time the new rate is arrived as an
 * average rate of LOW and HIGH rate. Depending upon the NPV value, the LOW and
 * HIGH rate are updated every time to drill down to IRR rate.
 * 
 * 
 * @author wendellopes
 *
 */

public class IRR {

	private static final double LOW_RATE = 0.01;
	private static final double HIGH_RATE = 0.5;
	private static final double MAX_ITERATION = 1000;
	private static final double PRECISION_REQ = 0.00000001;

	public static double computeIRR(double cf[], int numOfFlows) {

		int i = 0, j = 0;
		double m = 0.0;
		double old = 0.00;
		double _new = 0.00;
		double oldguessRate = LOW_RATE;
		double newguessRate = LOW_RATE;
		double guessRate = LOW_RATE;
		double lowGuessRate = LOW_RATE;
		double highGuessRate = HIGH_RATE;
		double npv = 0.0;
		double denom = 0.0;

		for (i = 0; i < MAX_ITERATION; i++) {
			npv = 0.00;
			for (j = 0; j < numOfFlows; j++) {
				denom = Math.pow((1 + guessRate), j);
				npv = npv + (cf[j] / denom);
			}

			/* Stop checking once the required precision is achieved */
			if ((npv > 0) && (npv < PRECISION_REQ))
				break;
			if (old == 0)
				old = npv;
			else
				old = _new;
			_new = npv;
			if (i > 0) {
				if (old < _new) {
					if (old < 0 && _new < 0)
						highGuessRate = newguessRate;
					else
						lowGuessRate = newguessRate;
				} else {
					if (old > 0 && _new > 0)
						lowGuessRate = newguessRate;
					else
						highGuessRate = newguessRate;
				}
			}
			oldguessRate = guessRate;
			guessRate = (lowGuessRate + highGuessRate) / 2;
			newguessRate = guessRate;
		}
		return guessRate;
	}

	public static double getIRR(final double[] cashFlows) {
		final int MAX_ITER = 20;
		double EXCEL_EPSILON = 0.0000001;

		double x = 0.1;
		int iter = 0;
		while (iter++ < MAX_ITER) {

			final double x1 = 1.0 + x;
			double fx = 0.0;
			double dfx = 0.0;
			for (int i = 0; i < cashFlows.length; i++) {
				final double v = cashFlows[i];
				final double x1_i = Math.pow(x1, i);
				fx += v / x1_i;
				final double x1_i1 = x1_i * x1;
				dfx += -i * v / x1_i1;
			}
			final double new_x = x - fx / dfx;
			final double epsilon = Math.abs(new_x - x);

			if (epsilon <= EXCEL_EPSILON) {
				if (x == 0.0 && Math.abs(new_x) <= EXCEL_EPSILON) {
					return 0.0; // OpenOffice calc does this
				} else {
					return new_x;
				}
			}
			x = new_x;
		}
		return x;
	}
	
	/**
     * Computes the internal rate of return using an estimated irr of 10 percent.
     *
     * @param income the income values.
     * @return the irr.
     */
    public static  double irr(double[] income) {
        return irr(income, 0.1d);
    }
    
    /**
     * Calculates IRR using the Newton-Raphson Method.
     * <p>
     * Starting with the guess, the method cycles through the calculation until the result
     * is accurate within 0.00001 percent. If IRR can't find a result that works
     * after 20 tries, the Double.NaN<> is returned.
     * </p>
     * <p>
     *   The implementation is inspired by the NewtonSolver from the Apache Commons-Math library,
     *   @see <a href="http://commons.apache.org">http://commons.apache.org</a>
     * </p>
     *
     * @param values        the income values.
     * @param guess         the initial guess of irr.
     * @return the irr value. The method returns <code>Double.NaN</code>
     *  if the maximum iteration count is exceeded
     *
     * @see <a href="http://en.wikipedia.org/wiki/Internal_rate_of_return#Numerical_solution">
     *     http://en.wikipedia.org/wiki/Internal_rate_of_return#Numerical_solution</a>
     * @see <a href="http://en.wikipedia.org/wiki/Newton%27s_method">
     *     http://en.wikipedia.org/wiki/Newton%27s_method</a>
     */
	
	  public static  double irr(double[] values, double guess) {
	        final int maxIterationCount = 20;
	        final double absoluteAccuracy = 1E-7;

	        double x0 = guess;
	        double x1;

	        int i = 0;
	        while (i < maxIterationCount) {

	            // the value of the function (NPV) and its derivate can be calculated in the same loop
	            final double factor = 1.0 + x0;
	            int k = 0;
	            double fValue = values[k];
	            double fDerivative = 0;
	            for (double denominator = factor; ++k < values.length; ) {
	                final double value = values[k];
	                fValue += value / denominator;
	                denominator *= factor;
	                fDerivative -= k * value / denominator;
	            }

	            // the essense of the Newton-Raphson Method
	            x1 = x0 - fValue/fDerivative;

	            if (Math.abs(x1 - x0) <= absoluteAccuracy) {
	                return x1;
	            }

	            x0 = x1;
	            ++i;
	        }
	        // maximum number of iterations is exceeded
	        return Double.NaN;
	    }

}
