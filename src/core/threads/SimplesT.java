package core.threads;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.*;

public class SimplesT {

	public static void main(String[] args) {
		
		Predicate<String> p = o -> o.startsWith("w");
		
		
		double expectedValue = 1.0;
		double tolerance = 0.01;
		int learningCiclos = 0;
		List<Neuron> neurons = new ArrayList<>();
		//Neuron n = new Neuron("n1",0.001,null);
		int i=0;
		do {
			i++;
			Map<String, Neuron> map = new HashMap<>();
		//	neurons.add(new Neuron(""+i,0.001,map));
		}while(i>0.00006);
	
	}
	
	public static double soma(double... value) {
		double soma=0.000000001;
		for (double d : value) {
			soma+=d;
		}
		
		return soma;
	}
	
	public static boolean neuronSynapse(Neuron... n) {
		
		try {
			//System.out.println("Start "+);
			Thread.sleep(1000);
			System.out.println("Finished Load");

		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		return false;
	}
	
class Neuron{
	String key;
	double value;
	Map<String,Neuron> conn;
	public Neuron(String key, double value, Map<String,Neuron> conn) {
		this.key=key;
		this.value=value;
		this.conn=conn;
	}
}

}
