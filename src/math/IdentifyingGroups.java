package math;

import java.util.Arrays;

public class IdentifyingGroups {
	
	public double amplitude(double list[]){
		Arrays.sort(list);
		double min = list[0];
		double max = list[list.length - 1];
		System.out.println("Valeur min = "+min);
		System.out.println("Valeur max = "+max);
		System.out.println("Amplitude = "+(max - min));
		return (max - min);
	}
	
	public double[] fatias(double objetosReais[], double amplitude, int categoriasAbstratas){

		double fatias[] = new double[categoriasAbstratas];
		double f = (amplitude/categoriasAbstratas);
		for(int i = 0; i < categoriasAbstratas; i++){
			//f+=f;
			fatias[i] = i*f+f;
			System.out.println("fatia "+(i+1)+" = "+fatias[i]);
		}
		
		return fatias;
	}
	
	public void definePertinenciaGroups(double list[], double fatias[]){
	
		int grupo[] = new int[fatias.length];
		for(int j = 0; j < list.length; j++){
			for(int i = 0; i < fatias.length; i++){
				if(i==0){
					if((list[j] <= fatias[i]) && (list[j] >= 0)){
						grupo[i] = grupo[i]+1;
					}
				}else{
					if((list[j] <= fatias[i]) && (list[j] >= fatias[i-1])){
						grupo[i] = grupo[i]+1;
					}		
				}	
			}
		}
		
		for(int i = 0; i < fatias.length; i++){
			System.out.println("Grupo ["+i+"] = "+grupo[i]);
		}
	
	}

}
