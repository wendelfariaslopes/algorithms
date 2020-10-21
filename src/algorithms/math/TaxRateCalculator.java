package algorithms.math;

/**
 * 
 * 	1 to 100 units – Rs. 10/unit
	100 to 200 units – Rs. 15/unit
	200 to 300 units – Rs. 20/unit
	above 300 units – Rs. 25/unit
 * 
 * Examples:

	Input: U = 250
	Output: 3500
	Explanation:
	Charge for the first 100 units – 10*100 = 1000
	Charge for the 100 to 200 units – 15*100 = 1500
	Charge for the 200 to 250 units – 20*50 = 1000
	Total Electricity Bill = 1000 + 1500 + 1000 = 3500
	
	Input: U = 95
	Output: 950
	Explanation:
	Charge for the first 100 units – 10*95 = 950
	Total Electricity Bill = 950
 * 
 * @author Utilisateur
 *
 */
public class TaxRateCalculator {
	// Function to calculate the  electricity bill 
public static int calculateBill(int units) { 

    // Condition to find the charges bar in which the units consumed  is fall 
    if (units <= 100) { 
        return units * 10; 
    } else if (units <= 200) { 
        return (100 * 10) + (units - 100) * 15; 
    } else if (units <= 300) { 
        return (100 * 10)  + (100 * 15) + (units - 200)  * 20; 
    } else if (units > 300) { 
        return (100 * 10) + (100 * 15) + (100 * 20) + (units - 300) * 25; 
    } 
    
    return 0; 
} 

// Driver Code 
public static void main(String args[]) 
{ 
    int units = 250; 

    System.out.println( 
        calculateBill(units)); 
} 
}