package algorithms.math;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class IdentifyingGroups {
	
	public static void main(String [] args) {
		
		Random r = new Random();
		
		double min = 0.0;
		double max = 100.0;
		int amostragem = 10000;
		double list[] = new double[amostragem];
		
		for(int i =0; i < amostragem; i++) {
			//max=r.nextInt()<0?max:max*2;
			list[i]=generate(min,max);
		}
		
		double a = amplitude(list);
		double fatias[] = fatias(list,a, (int) Math.sqrt(amostragem));
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
		return (max - min);
	}
	
	public static double[] fatias(double objetosReais[], double amplitude, int categoriasAbstratas){

		double fatias[] = new double[categoriasAbstratas];
		double f = (amplitude/categoriasAbstratas);
		for(int i = 0; i < categoriasAbstratas; i++){
			//f+=f;
			fatias[i] = i*f+f;
			System.out.println("fatia "+(i+1)+" = "+fatias[i]);
		}
		
		return fatias;
	}
	
	public static void definePertinenciaGroups(double list[], double fatias[]){
	
		
		Map<String,List<Double>> map = new HashMap<>();
		
		for(int k = 0; k < fatias.length; k++){

			List<Double> listDouble = new ArrayList<>(); // each fatia there is one group
			
			for(int j = 0; j < list.length; j++){
				if(k==0){
					if((list[j] <= fatias[k]) && (list[j] >= 0)){
						listDouble.add(list[j]);
					}
				}else{
					if((list[j] <= fatias[k]) && (list[j] >= fatias[k-1])){
						listDouble.add(list[j]);
	
					}		
				}
				map.put("group["+k+"]", listDouble);
			}
		}
		
		DecimalFormat df = new DecimalFormat(".##");
		
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
		System.out.println("Average = "+s.calculateAverage(list));
	
	}

}
