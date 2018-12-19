package algorithms.collections;

import java.util.Arrays;

public class Stack2
{
    
    int size;
	int firstIn;
	Long array[];
    
    public Stack2() {
    		//size=10;
		array = new Long[size];
		firstIn = 0;
	}
    
    public void push(Long i) {
        
        if(i!=null) {
			size+=1;
			array = Arrays.copyOf(array, size);
		}
		
		if (firstIn < size) {
			array[firstIn] = i;
			firstIn++;
		} else {
			System.err.println("Error Stack Overflow!!");
		}

    }
    
    public Long pop() {
        
    		if (!isEmpty()) {
			Long popped = this.peek();
			array[firstIn - 1] = null;
			firstIn--;
			return popped;
		} else {
			return null;
		}
    }
    
    private Long peek() {
		if (firstIn > 0) {
			return array[firstIn - 1];
		} else {
			return null;
		}
	}
    
    private boolean isEmpty() {
		if (firstIn == 0) {
			return true;
		} else {
			return false;
		}
	}
    
    public static void main(String[] args) {
        Stack2 stack = new Stack2();
        stack.push(5L);
        stack.push(6L);
        stack.push(7L);
        
        stack.push(8L);
        stack.push(9L);
        stack.push(10L);
        
        System.out.println(Arrays.toString(stack.array));
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(Arrays.toString(stack.array));
    }
}
