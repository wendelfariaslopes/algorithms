package cogito4j.analysis;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;

public class Metrics {

	public static void main(String[] args) {
		
		double [] actual =   {1, 1, 0, 1, 0, 0, 1, 0, 0, 0}; // Tests made
		double [] expected = {1, 0, 0, 1, 0, 0, 1, 1, 1, 0}; // name can be expected or predicted (Theory) 
		
		// when we refere about Positive cases (P) or Negative cases (N)
		// We talk about observations about some tests applied over something that we need to measure
		// If try classify, for example, some set of fruits can be P -> is an apple or N -> is not an apple
		boolean isTheCase = false; // P or N
		// When we talk about True Positive (TP), we talk about the observation of something that we considere a priori a "theory
		// about" or "paradigma about" or "classificado as", for example, we test this fruit, that we already know is an apple,
		// and we use a test to detect if this give us an apple as a answer
		// So, True or False are about a priori Theory or Conception or Classification already got.
		// and Positive or Negative are about a posteriori Tests made about the theory.
	
	
		int samples = actual.length; // total de samples
		
		double TP = 100; //True Positive (TP) : Observation is positive, and is predicted to be positive.
		//These are cases in which we predicted yes (they have the disease), and they do have the disease.
		
		double TN = 50; //True Negative (TN) : Observation is negative, and is predicted to be negative. 
		//We predicted no, and they don't have the disease.
		
		double FP = 10; //False Positive (FP) : Observation is negative, but is predicted positive. 
		//We predicted yes, but they don't actually have the disease. (Also known as a "Type I error.")
		
		double FN = 5; //False Negative (FN) : Observation is positive, but is predicted negative.
		//We predicted no, but they actually do have the disease. (Also known as a "Type II error.")
	
		
		double[][] confusionMatrix = new double[2][2];
		
//		for(int i=0; i < samples; i++) {
//			if(expected[i]==1 && actual[i]==1) {
//				++TP;
//			}
//			if(expected[i]==0 && actual[i]==1) {
//				++FP;
//			}
//			if(expected[i]==1 && actual[i]==0) {
//				++TN;
//			}
//			
//			if(expected[i]==0 && actual[i]==0) {
//				++FN;
//			}
//			
//		}
		confusionMatrix[1][1]=TP;
		confusionMatrix[0][1]=FP;
		confusionMatrix[0][0]=FN;
		confusionMatrix[1][0]=TN;
		
		System.out.println(Arrays.deepToString(confusionMatrix));
		
		double total = TP+TN+FP+FN;
		double actualNo = TN+FP;
		
		//Accuracy: Overall, how often is the classifier correct?
		double accuracy = (TP + TN)/total; //(TP+TN)/total = (100+50)/165 = 0.91
		//Misclassification Rate: Overall, how often is it wrong? (equivalent to 1 minus Accuracy or also known as "Error Rate")
		double misclassification = (FP + FN)/(TP+TN+FP+FN);// (FP+FN)/total = (10+5)/165 = 0.09
		//True Positive Rate: When it's actually yes, how often does it predict yes? (also known as "Sensitivity" or "Recall")
		double TPR = TP/(FN+TP);// TP/actual yes = 100/105 = 0.95
		//False Positive Rate: When it's actually no, how often does it predict yes?
		double FPR = FP/actualNo;// FP/actual no = 10/60 = 0.17
		//True Negative Rate: When it's actually no, how often does it predict no? (equivalent to 1 minus False Positive Rate, also known as "Specificity")
		double specificity = TN/actualNo;// TN/actual no = 50/60 = 0.83
		//Precision: When it predicts yes, how often is it correct?		
		double precision = TP/(FP+TP); // TP/predicted yes = 100/110 = 0.91
		//Prevalence: How often does the yes condition actually occur in our sample?
		double prevalence = (FN+TP)/total; //actual yes/total = 105/165 = 0.64
		
		System.out.println("Accuracy          = " + accuracy);
		System.out.println("Misclassification = " + misclassification);
		System.out.println("Sensitivity (TPR) = " + TPR);
		System.out.println("FPR = " + FPR);
		System.out.println("Specificity (TNR) = " + specificity);
		System.out.println("Precision = " + precision);
		System.out.println("Prevalence = " + prevalence);
		
	}
	
	public static double pearson(double[] x, double[] y) {
		
		DecimalFormat df = new DecimalFormat("0.000");

		double maX = 0;
		double maY = 0;
		double r = 0;
		int length = 1;

		if (x.length <= y.length) {
			length = x.length;
		} else {
			length = y.length;
		}

		for (int i = 0; i < length; i++) {
			maX += x[i];
			maY += y[i];
		}

		maX = maX / length;
		maY = maY / length;
		
		double [] desvioX = new double[length];
		double [] desvioY = new double[length];
		double dvXdvY = 0;
		
		double [] varX = new double [length];
		double [] varY = new double [length];
		double sdX = 0;
		double sdY = 0;
		
		//System.out.println("Diff X  , Diff Y");
		for (int i = 0; i < length; i++) {
			
			desvioX[i] = x[i] - maX;
			desvioY[i] = y[i] - maY;
			varX[i] = Math.pow(desvioX[i], 2);
			varY[i] = Math.pow(desvioY[i], 2);
			sdX +=varX[i];
			sdY +=varY[i];
			
			dvXdvY += desvioX[i] * desvioY[i];
			//System.out.println(df.format(desvioX[i])+"  ,  "+df.format(desvioY[i]));
		}
		//double c = dvXdvY/length;
		double covariancia = dvXdvY/(length-1);
		sdX = Math.sqrt(sdX/(length-1));
		sdY = Math.sqrt(sdY/(length-1));
		
		double pearson = covariancia/(sdX*sdY);
		
		//System.out.println("Pearson Correlation = "+df.format(pearson));
		
		//System.out.println("Covariance = "+df.format(c));
		
		return pearson;
	}

	////The algorithm works as 1 – ( P(class1)^2 + P(class2)^2 + … + P(classN)^2)
	public static double giniIndex(){
		return 0.0;
	}

	double gini(List<Double> values) {
		double sumOfDifference = values.stream()
				.flatMapToDouble(v1 -> values.stream().mapToDouble(v2 -> Math.abs(v1 - v2))).sum();
		double mean = values.stream().mapToDouble(v -> v).average().getAsDouble();
		return sumOfDifference / (2 * values.size() * values.size() * mean);
	}
}
