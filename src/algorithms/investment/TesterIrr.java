package algorithms.investment;

public class TesterIrr {

	public static void main(String[] args) {
		double cf[] = new double[30];
		double irr = 0.00;
		int numOfFlows;
		
		 cf[0] = -100000;
		 cf[1] = 25000;
		 cf[2] = 25000;
		 cf[3] = 25000;
		 cf[4] = 25000;
		 cf[5] = 25000;
		 
		 numOfFlows = 6;
		 irr = IRR.computeIRR(cf, numOfFlows);
		 System.out.printf("\nFinal IRR: %.8f", irr);
		 
		 irr = IRR.getIRR(cf);
		 System.out.printf("\nFinal IRR: %.8f", irr);
		 
		 double[] monthlyCashFlowWireline = {-4448.56d, 4011.96d, 3878.64d, 3859.35d, 3840.15d, 3821.04d, 3802.03d,
	                3783.12d, 3764.29d, 3745.57d, 3726.93d, 3708.39d, 3578.04d, 3560.23d, 3542.52d, 3524.90d, 3507.36d,
	                3489.91d, 3472.55d, 3455.27d, 3438.08d, 3420.98d, 3403.96d, 9141.36d};

	        double[] cfm = new double[61];
	        cfm[0]=-1000000;
	        double NPV = 0.0;
	        
	        double periodicRate = 0.64/100;
	        
	        
		 for(int i=1;i<cfm.length;i++) {
			 cfm[i]=25000;
			 NPV += 25000/Math.pow((1+periodicRate), i);
		 }
		 
		 irr = IRR.irr(cfm,periodicRate);
		 System.out.printf("\nFinal IRR: %.8f", irr);
		 
		 
		
		 
		 System.out.printf("\nNPV: %.2f",cfm[0] + NPV);

	}

}
