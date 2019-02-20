package math;

import java.util.Arrays;

public class Statistic{
	
	public double calculateAverage(double list[]){
		
		double sum = 0;
		double num = list.length;
		double average = 0;
		
		for(int i = 0; i < num; i++ ){
			sum += list[i]; 
		}
		
		average = sum/num;
		
		return average;
	}
	
	public double[] calculateDesviation(double list[], double average){

		double listD[] = new double[list.length];
		
		for(int i=0; i< list.length; i++){
			listD[i] = list[i] - average;
		}
		
		return listD;
	}
	
	public double calculateSumOfSquared(double list[]){
		
		double sos = 0;   // sum of squared
		
		for(int i = 0; i < list.length; i++ ){
			sos += Math.pow(list[i], 2); 
		}
		
		return sos;
	}
	
	public double calculateVariace(double list[]){
		
		double average = calculateAverage(list);
	
        return calculateSumOfSquared(calculateDesviation(list, average)) / (list.length - 1);
	}

	public double calculateStandardDeviation(double list[]){
		
		return Math.sqrt(calculateVariace(list));
	}
	
	
	 /**
     * Returns the average value in the specified array.
     *
     * @param  a the array
     * @return the average value in the array {@code a[]};
     *         {@code Double.NaN} if no such value
     */
    public static double mean(int[] a) {
       
        if (a.length == 0) return Double.NaN;
        int sum = sum(a);
        return 1.0 * sum / a.length;
    }
    
    /**
     * Returns the average value in the specified array.
     *
     * @param  a the array
     * @return the average value in the array {@code a[]};
     *         {@code Double.NaN} if no such value
     */
    public static double mean(double[] a) {
        

        if (a.length == 0) return Double.NaN;
        double sum = sum(a);
        return sum / a.length;
    }
    
    /**
     * Returns the sample variance in the specified array.
     *
     * @param  a the array
     * @return the sample variance in the array {@code a[]};
     *         {@code Double.NaN} if no such value
     */
    public static double var(int[] a) {

        if (a.length == 0) return Double.NaN;
        double avg = mean(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }
        return sum / (a.length - 1);
    }
	
	/**
     * Returns the sample variance in the specified array.
     *
     * @param  a the array
     * @return the sample variance in the array {@code a[]};
     *         {@code Double.NaN} if no such value
     */
    public static double var(double[] a) {
        

        if (a.length == 0) return Double.NaN;
        double avg = mean(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }
        return sum / (a.length - 1);
    }
    
    
    /**
     * Returns the population variance in the specified array.
     *
     * @param  a the array
     * @return the population variance in the array {@code a[]};
     *         {@code Double.NaN} if no such value
     */
    public static double varp(double[] a) {
   
        if (a.length == 0) return Double.NaN;
        double avg = mean(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += (a[i] - avg) * (a[i] - avg);
        }
        return sum / a.length;
    }
	
    
    /**
     * Returns the sample standard deviation in the specified array.
     *
     * @param  a the array
     * @return the sample standard deviation in the array {@code a[]};
     *         {@code Double.NaN} if no such value
     */
    public static double stddev(double[] a) {
      
        return Math.sqrt(var(a));
    }

    /**
     * Returns the sample standard deviation in the specified array.
     *
     * @param  a the array
     * @return the sample standard deviation in the array {@code a[]};
     *         {@code Double.NaN} if no such value
     */
    public static double stddev(int[] a) {

        return Math.sqrt(var(a));
    }
    
    
    /**
     * Returns the population standard deviation in the specified array.
     *
     * @param  a the array
     * @return the population standard deviation in the array;
     *         {@code Double.NaN} if no such value
     */
    public static double stddevp(double[] a) {
        
        return Math.sqrt(varp(a));
    }
    
    /**
     * Returns the sum of all values in the specified array.
     *
     * @param  a the array
     * @return the sum of all values in the array {@code a[]};
     *         {@code 0.0} if no such value
     */
    private static double sum(double[] a) {
     
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }

    
    /**
     * Returns the sum of all values in the specified array.
     *
     * @param  a the array
     * @return the sum of all values in the array {@code a[]};
     *         {@code 0.0} if no such value
     */
    private static int sum(int[] a) {
        
        int sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i];
        }
        return sum;
    }
    
	/*
	 * Algumas publicações estabelecem critérios para classificação do coeficiente de variação, 
	 * de acordo com dados de trabalhos com as variáveis estudadas, muitas vezes expressando essa 
	 * classificação em tabelas onde determinam-se valores de CV considerados: Baixo, Médio, Alto e 
	 * Muito Alto (quanto menor o CV, maior a precisão dos dados)
	 */
	public double calculateCoefficientVariation(double sd, double average){
		
		 double cv = sd/average;
		if(cv <= 0.15){ //For menor ou igual a 15% → baixa dispersão: dados homogêneos
			System.out.println("baixa dispersao");
		} else if(cv <= 0.30){ //For entre 15 e 30% → média dispersão
			System.out.println("média dispersão");
		} else{  //For maior que 30% → alta dispersão: dados heterogêneos
			System.out.println("alta dispersão");
		}
	
		return cv;
	}
	/*
	 * the quality or state of being correct or precise.
	 */
	public double calculateAccuracy(double sd, double average){
		
		return sd/average;
	}
	
	
	

}
