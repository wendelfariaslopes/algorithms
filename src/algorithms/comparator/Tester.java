package algorithms.comparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tester {

	public static void main(String[] args) {
		List<Bean> listBeans = new ArrayList<>();
		
		for(int i=1; i< 20; i++) {
			Bean b = new Bean();
			b.setName("Bean "+i);
			b.setReturnValue((double) i*1.1);
			
			b.setRiskValue((double) i*0.5);
		
			
			
			if(b.getRiskValue() <  4) {

				listBeans.add(b);
			}
			
			
		}
		
	
		
		Collections.sort(listBeans, Bean.COMPARE_BY_NAME);
		
		listBeans.forEach(x-> System.out.println(x.getName() +" risk = "+x.getRiskValue()+" return = "+x.getReturnValue()));
		
		System.out.println("------- List Risk ------");
		
		Collections.sort(listBeans, Bean.COMPARE_BY_RISK);
		
		listBeans.forEach(x-> System.out.println(x.getName() +" risk = "+x.getRiskValue()+" return = "+x.getReturnValue()));
		
		System.out.println("------- List RETURN ------");
		
		Collections.sort(listBeans, Bean.COMPARE_BY_RETURN);
		
		listBeans.forEach(x-> System.out.println(x.getName() +" risk = "+x.getRiskValue()+" return = "+x.getReturnValue()));

	}

}
