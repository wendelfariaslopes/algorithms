package algorithms.generic;

public class Test {

	public static void main(String[] args) {
		Bean<Portfolio> bp = new Bean<>();
		Portfolio p = new Portfolio();
		p.setId(10);
		p.setName("test portfolio 1");
		
		bp.setT(p);
		
		
		
		System.out.println(bp.getT().getName());
		

	}

}
