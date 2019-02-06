package algorithms.deeplearning;

public class Sigmoid {
	
	public static void main(String []args) {
		double result = 0.9525741268224334; //Expected Output for 3
		
		if(result==getValue(3.0)) {
			System.out.println("Ok");
		}else {
			System.err.println("Bad");
		}
		
		
	}
	
	public static double getValue(double x) {
		return 1/(1+Math.exp(-(double) x));
	}
	

}

//public class Box<T> {
//	   private T t;
//
//	   public void add(T t) {
//	      this.t = t;
//	   }
//
//	   public T get() {
//	      return t;
//	   }
//
//	   public static void main(String[] args) {
//	      Box<Integer> integerBox = new Box<Integer>();
//	      Box<String> stringBox = new Box<String>();
//	    
//	      integerBox.add(new Integer(10));
//	      stringBox.add(new String("Hello World"));
//
//	      System.out.printf("Integer Value :%d\n\n", integerBox.get());
//	      System.out.printf("String Value :%s\n", stringBox.get());
//	   }
//	}