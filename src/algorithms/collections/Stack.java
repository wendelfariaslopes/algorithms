package algorithms.collections;

public class Stack {
	
	int size;
	int firstIn;
	String array[];

	public Stack() {
//		int n = 10;
//		size = n;
		array = new String[size];
		firstIn = 0;
	}

	public void push(String str) {
		
		if(str!=null) {
			size+=1;
			array = new String[size];
		}
		
		if (firstIn < size) {
			array[firstIn] = str;
			firstIn++;
		} else {
			System.err.println("Error Stack Overflow!!");
		}
	}

	public String pop() {
		if (!isEmpty()) {
			String popped = this.peek();
			array[firstIn - 1] = null;
			firstIn--;
			return popped;
		} else {
			return null;
		}
	}

	public String peek() {
		if (firstIn > 0) {
			return array[firstIn - 1];
		} else {
			return null;
		}
	}

	public boolean isEmpty() {
		if (firstIn == 0) {
			return true;
		} else {
			return false;
		}
	}

}
