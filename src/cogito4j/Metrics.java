package cogito4j;

import java.util.Arrays;

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

	
	private int[] union() {
		
	}
}
