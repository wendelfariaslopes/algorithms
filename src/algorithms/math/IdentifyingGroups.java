package algorithms.math;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

public class IdentifyingGroups {
	
	static DecimalFormat df = new DecimalFormat(".##");
	
	//static double min = 5.5;
	//static double max = 7.0;
	static int amostragem = 100;
	
	public static void main(String [] args) {
		
		Random r = new Random();
		
		double list[] = new double[amostragem];
		
		double cMin =1000000000.0;
		
		for(int i =0; i < amostragem; i++) {
			//max=r.nextInt()<0?max:max*2;
			//list[i]=generate(min,max);
			if(i%10==0) {
				list[i]=generate(9,10);
			}else {
				list[i]=generate(1,2);
			}
	
			if(list[i]<cMin) {
				cMin=list[i];
			}
		}
		
		double a = amplitude(list);
		double fatias[] = fatias(list,a, (int) Math.sqrt(amostragem), cMin);
		definePertinenciaGroups(list, fatias);
		
		
	}
	
	public static double generate(double min, double max) {
		return min + Math.random() * (max - min);
	}
	
	public static double amplitude(double list[]){
		Arrays.sort(list);
		double min = list[0];
		double max = list[list.length - 1];
		
		System.out.println("Valeur min = "+min);
		System.out.println("Valeur max = "+max);
		System.out.println("Amplitude = "+(max - min));
		System.out.println();
		return (max - min);
	}
	
	public static double[] fatias(double objetosReais[], double amplitude, int categoriasAbstratas,double min){

		double fatias[] = new double[categoriasAbstratas];
		double f = (amplitude/categoriasAbstratas);
		for(int i = 0; i < categoriasAbstratas; i++){
			//f+=f;
			fatias[i] = (i+1)*f + min;
			System.out.println("fatia "+(i+1)+" = "+fatias[i]);
		}
		System.out.println();
		
		return fatias;
	}
	
	public static void definePertinenciaGroups(double list[], double fatias[]){
	
		double maxInterval = 0.0;
		double minInterval = 0.0;
		
		//Map<String,List<Double>> map = new HashMap<>();
		Map<String,List<Double>> map = new TreeMap<>();
		
		for(int k = 0; k < fatias.length; k++){
			
			List<Double> listDouble = new ArrayList<>(); // each fatia there is one group
			
			for(int j = 0; j < list.length; j++){
				
				if(k==0){
					
					maxInterval = fatias[k];
					if((list[j] <= maxInterval)){
						listDouble.add(list[j]);	
					}
					
				}else{
					
					minInterval = fatias[k-1];
					maxInterval = fatias[k];
					
					if((list[j] <= maxInterval) && (list[j] >= minInterval)){
						listDouble.add(list[j]);
					}		
				}
				
				map.put("Group["+k+"] ("+df.format(minInterval)+" , "+df.format(maxInterval)+")", listDouble);
			}
		}
		
		double [] elementsByGroup = new double[map.size()];
		
		int i = 0;
		for (String key : map.keySet()) {
			System.out.println(key+" = "+map.get(key).size());
			elementsByGroup[i] = map.get(key).size();
			i++;
			for (Double d : map.get(key)) {
				System.out.print(df.format(d)+", ");
			}
			System.out.println();
		}
		
		Statistic s = new Statistic();
		s.calculateAverage(list);
		
	
	}

}
