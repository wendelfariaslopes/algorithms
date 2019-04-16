package algorithms.math;

import java.time.LocalDate;
import java.util.*;

import org.apache.commons.math3.distribution.NormalDistribution;

import static java.lang.Math.sqrt;
import static java.lang.Math.exp;

public class BrownianWienerProcess {
	
	/**
	 * Run the Wiener process for a given period and initial amount with a monthly
	 * value that is added every month. The code calculates the projection of the
	 * value, a set of quantiles and the brownian geometric motion based on a random
	 * walk.
	 *
	 * @param mu
	 *            mean value (annualized)
	 * @param sigma
	 *            standard deviation (annualized)
	 * @param years
	 *            projection duration in years
	 * @param initialValue
	 *            the initial value
	 * @param monthlyValue
	 *            the value that is added per month
	 * @param breaks
	 *            quantile breaks
	 * @return a List of double arrays containing the values per month for the given
	 *         quantile breaks
	 * 
	 *         Applying the values:
	 * 
	 *         mu: 0.05 (or 5%) 
	 *         sigma: 0.1 (or 10%) 
	 *         initial value: 7000 
	 *         monthly increase: 100 time period: 6 years
	 * 
	 */
	public static List<double[]> getProjection(double mu, double sigma, int years, int initialValue, int monthlyValue,
			double[] breaks) {
		
		double periodizedMu = mu / 12;
		double periodizedSigma = sigma / Math.sqrt(12);
		int periods = years * 12;

		List<double[]> result = new ArrayList<double[]>();

		
		// Calculate value and quantiles
		for (int i = 0; i < periods; i++) {
			double value = initialValue + (monthlyValue * i);
			NormalDistribution normalDistribution = new NormalDistribution(periodizedMu * (i + 1),
					periodizedSigma * sqrt(i + 1));
			double bounds[] = new double[breaks.length];
			for (int j = 0; j < breaks.length; j++) {
				double normInv = normalDistribution.inverseCumulativeProbability(breaks[j]);
				bounds[j] = value * exp(normInv);
			}

			result.add(bounds);
		}
		return result;
	}
	
	
}
