package core.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;

public class ListTest {

	public static void main(String[] args) {
		
		/*
		 * for (Iterator it = items.iterator(); it.hasNext();) { 
		 * 	if(predicate(it.next())) { 
		 * 		it.remove(); 
		 * 	} 
		 * }
		 * 
		 */
		
		Predicate<Integer> negative = n -> (n < 0) ;
		System.out.println(negative.test(-10));
		System.out.println(negative.test(10));
		
		Predicate<Integer> multipleOf2 = num -> num%2 == 0;
        Predicate<Integer> multipleOf3 = num -> num%3 == 0;


		
		List<Integer> ints = new ArrayList<>();
		
		List<Obj> listObj = new ArrayList<>();
		
		
		for (int i = 0; i < 10; i++) {
			ints.add(i);
			Random r = new Random();
			int k = r.nextInt(100);
			
			listObj.add(new Obj(k,k+"","v"+i));
		}
		
		// items.removeIf(o -> predicate(o));
		ints.removeIf(i -> i%2==0);
		
		ints.removeIf(multipleOf2.and(multipleOf3));
		ints.removeIf(multipleOf2.or(multipleOf3));
		
		
		Predicate<Obj> objectNaoPodeSerIgual7 = o -> o.getId()==7;
		listObj.removeIf(o -> o.getId()==7); // listObj.removeIf(objectNaoPodeSerIgual7); sao funcoes equivalentes
		
	/*	listObj.removeIf(o -> o.getKey().endsWith("2"));
		
		for (Integer i : ints) {
			System.out.println(i);
		}
	*/	
		listObj.forEach(objs -> System.out.println(objs.getKey()));
		
		listObj.sort((o1,o2)-> o1.getKey().compareTo(o2.getKey()));

		listObj.sort((o1,o2)-> o1.getKey().compareTo(o2.getKey()));
		
		System.out.println("------------------ sort -----------");
		listObj.forEach(o -> System.out.println(o.getId()));
		
	}
	
	

}
