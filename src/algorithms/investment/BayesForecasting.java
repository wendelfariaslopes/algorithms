package algorithms.investment;

public class BayesForecasting {

	public static void main(String[] args) {

		// Let's say we want to know how a change in interest rates would affect the
		// value of a stock market index.

		//A vast trove of historical data is available for all the major stock market indexes,
		//so you should have no problem finding the outcomes for these events. For our example, 
		//we will use the data below to find out how a stock market index will react to a rise in interest rates.
		
		
							//interest rates decline and increase
		double[][] matrix = {
				             {200,950}, //stock price decline
							 {800, 50}  //stock price increase
				             };

//				P(SI) = the probability of the stock index increasing
//				P(SD) = the probability of the stock index decreasing
//				P(ID) = the probability of interest rates decreasing
//				P(II) = the probability of interest rates increasing

		double P_SI = 0.0; // the probability of the stock index increasing
		double P_SD = 0.0; // the probability of the stock index decreasing
		double P_ID = 0.0; // the probability of interest rates decreasing
		double P__II = 0.0; // the probability of interest rates increasing
		double P_SD_II = 0.0; //

	}

}
