package algorithms.math;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

public class Classifier {

	public static void main(String[] args) {

		//Defina uma amostragem da população. Ex: 1000 pacientes de uma clínica.
		//Define a sampling of the population. Ex: 1000 patients from a clinic.
		//Définir un échantillon de la population. Ex: 1000 patients d'une clinique.
		List<Integer>  population= new ArrayList<>();
		//Ex: 1000 pacientes de uma clínica.
		//Ex: 1000 patients from a clinic.
		//Ex: 1000 patients d'une clinique.
		IntStream haveCharacteristic = Generator.betweenMinMax(10, 1, 2); // less than 50 true //Defina a doença ou característica de interesse. Ex: sífilis.
		IntStream haveNotCharacteristic = Generator.betweenMinMax(90, 4, 10); // great tha 50 and less than 100 true
		//Tenha um teste padrão ouro bem estabelecido para determinar a prevalência da doença ou característica.
		
		haveCharacteristic.forEach(l -> population.add(l));    //Para ilustração, 100 pessoas têm a caracteristic.
		haveNotCharacteristic.forEach(g -> population.add(g)); //Para ilustração, 900 não tem a caracteristic.
		
		Collections.shuffle(population);
		
		int k = 0;
		for (Integer i : population) {
			
			if(i<10) {
				 System.out.print(" ");
			}
			
			 System.out.print(i+" ");
			 k++;
			 if(k%20 == 0) {
				 System.out.println();
			 }
			
		}
		
		Map<String,Integer> R = new TreeMap<>();
		
		List<Integer> listInts = new ArrayList<>();
		
		//IntStream tests = Generator.betweenMinMax(10, 1, 100);
		//tests.forEach(x-> listInts.add(x));
		
		
		//Precision of my test
		// My theory testes = 5 
		// Meu calibre 1 ate 5 
		//Quantos numeros eu tenho dentro desse calibre?
		for(int o=0;o<990;o++) {
			listInts.add(1);
		}
		for(int o=0;o<10;o++) {
			listInts.add(Generator.betweenMinMax(5, 100));
		}
		//TP = 3 
		
		int numberOfTests = 10; // 10 CASOS DE TESTES
		
		
		for (int i = 0; i < numberOfTests; i++) {
			int searchCaracteristic = 1; // a caracteristica procurada pela pesquisa - um valor deve ser determinado para a caracteristia Tipo tem cancer = 1
			int valorDoTeste = listInts.get(Generator.betweenMinMax(0, listInts.size()-1));   // sendo a precisao do teste 99%, 
			// neste caso detecta Tipo cancer = 1 em 90 porcento dos casos (esta funcao pega aleatoriamente um valor de teste tipo laboratorio)
			int elementoDaPopulacao = population.get(Generator.betweenMinMax(0, population.size()-1));
			//pega aleatoriamente um elemento da populacao gerada anteriormente - aqui podemos dar um foco na pesquisa visto que podemos selecionar elemento em calibres diferente.
			
			System.out.print("Valor do teste = "+valorDoTeste+"  ");
			System.out.print("Elemento da Populacao = "+elementoDaPopulacao+"  ");
			
			if(valorDoTeste==searchCaracteristic) { // experimento funciona
				
				if(valorDoTeste == elementoDaPopulacao) { // Teste funcionou True e o elemento selecionado foi Positivo
					 
					R.put("TP", R.get("TP")==null?1:R.get("TP") + 1);
					System.out.println("TP");
				
				}else { 			// Teste funcionou True, mas para esse elemento selecionado foi Negativo
					
					R.put("TN", R.get("TN")==null?1:R.get("TN") + 1);
					System.out.println("TN");
		
				}
				
			}else {
				
				if(valorDoTeste == elementoDaPopulacao) { // O teste eh Falso e o elemento selecionado foi Positivo
					 
					R.put("FP", R.get("FP")==null?1:R.get("FP") + 1);
					System.out.println("FP");
				
				}else {
					System.out.println("FN");
					R.put("FN", R.get("FN")==null?1:R.get("FN") + 1);
				}
				
			}
			System.out.println();
			
			
		}
		

		int valorProcurado = 1;
		int ER = 0;
		for (Integer i : population) {
			if(i==valorProcurado) {
				 ER++;
			}
		}
		
		double error = 0.0001;
		
		double TP = (double) (R.get("TP")==null?error:R.get("TP"));
		double FP = (double) (R.get("FP")==null?error:R.get("FP"));
		double TN = (double) (R.get("TN")==null?error:R.get("TN"));
		double FN = (double) (R.get("FN")==null?error:R.get("FN"));
		
		
		
		double TPR = TP/(TP+FN);
		double TNR = TN/(TN+FP);
		double PPV = TP/(TP+FP);
		
		double AAC = (TP+TN)/(TP+TN+FP+FN);
		
		System.out.println("Sensitivity ="+ TPR);
		System.out.println("Specifity ="+ TNR);
		System.out.println("PPV or Precision ="+ PPV);  // Positive predictive value -- Precision
		
		
		double probH = (FP*TPR)/((FP*TPR)+(1-TPR)*(1-TN)); // probabilidade da hipotese
		
		System.out.println("bayes prob = "+probH*100);
		
		//System.out.println("True Positive Rate ="+ TPR);
		//System.out.println("Elementos Relevantes = "+ER);
//		
//		for (String key : R.keySet()) {
//			System.out.println(key+" "+R.get(key));
//		}
//		DecimalFormat df = new DecimalFormat("0.00");
//		double sensitivity = (double) R.get("TP")/((double)R.get("TP")+(double)R.get("FN"));
//		System.out.println("Sensitivity  = "+df.format(sensitivity*100)+"%");

	
	}

}
