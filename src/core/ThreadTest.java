package core;

import java.util.function.Predicate;


public class ThreadTest {
	
	private double x = 0.005;
	
	private Predicate<Portfolio> lowRisk = p -> p.valueRisk < x;
	private Predicate<Portfolio> highReturn = p -> p.valueReturn >= x;
	
	
	public static void main(String args[]) {
		ThreadTest t = new ThreadTest();
		Portfolio p = new Portfolio();
		p.name="w";
		p.valueRisk=0.01;
		
		System.out.println(t.understanding().test(p));
		System.out.println(t.lowRisk.test(p));
		
	}
	
	public Predicate<Portfolio> understanding(){
		return lowRisk;
	}

	public Predicate<Portfolio> getLowRisk() {
		return lowRisk;
	}

	public void setLowRisk(Predicate<Portfolio> lowRisk) {
		this.lowRisk = lowRisk;
	}

	public Predicate<Portfolio> getHighReturn() {
		return highReturn;
	}

	public void setHighReturn(Predicate<Portfolio> highReturn) {
		this.highReturn = highReturn;
	}
	
	

}
class Portfolio{
	String name;
	double valueRisk;
	double valueReturn;
}