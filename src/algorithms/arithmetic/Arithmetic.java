package algorithms.arithmetic;

import java.util.ArrayList;
import java.util.List;

public class Arithmetic {

	private static List<Integer> listInt = new ArrayList<>();
	private double averageTemp;
	private int n = 1;
	
	public static void main(String[] args) {
		
		Arithmetic a = new Arithmetic();
		
//		List<Integer> list = new ArrayList<>();
//		for(int i=1; i <=10;i++) {
//			list.add(i);
//			for (Integer integer : list) {
//				System.out.print(integer+" + ");
//			}
//			System.out.print(" = "+a.average(list));
//			System.out.println();
//		}
		
		for(int i=1; i <=10;i++) {
			System.out.print(a.average(i)+ " = ");
			for (Integer integer : listInt) {
				System.out.print(integer+" + ");
			}
			
			System.out.println();
		}
		
		
		

	}
	
	public Arithmetic() {
		listInt = new ArrayList<>();
	}
	
	//I want to add a value to an existing average without calculate it back to the total sum.
	public double average(int i) {
		
		listInt.add(i);
	
		//averageTemp = averageTemp+((i-averageTemp)/listInt.size());
		averageTemp = averageTemp+((i-averageTemp)/n);
		n++;
		return averageTemp;
	}
	
	public double average(List<Integer> list) {
		listInt.addAll(list);
		averageTemp = list.parallelStream().mapToInt(x->x.intValue()).average().getAsDouble();
		return averageTemp;
	}

}
