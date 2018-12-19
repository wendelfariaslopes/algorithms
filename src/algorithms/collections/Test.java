package algorithms.collections;

public class Test {

	public static void main(String[] args) {
		
		Stack s = new Stack();
		//s.size=10;
	
	
			s.push("one info");
			s.push("two info");
			s.push("one info");
			s.push("two info");
		
		System.out.println("Size = "+s.size);
		
		String[] array = s.array;
		for (String str : array) {
			System.out.println(str);
		}
		
		
	}

}
