package algorithms.collections;

public class Queue {
	
	int maxSize;
	int top;
	int rear;
	String arr[];

	public Queue() {
		int n = 0;
		maxSize = n;
		arr = new String[maxSize];
		top = -1;
		rear = 0;
	}

	public void queuePush(String cars) {
		if (top < maxSize - 1) {
			top++;
			arr[top] = cars;
			// display();
			// }else{
			// System.out.println("Error Queue Overflow!!");
		}
	}

	public void queuePop() {
		if (top >= rear) {
			rear++;
			// display();
		} else {
			System.out.println("Error");
		}
		// }
		// public void display() {
		// if (top >= rear) {
		// System.out.println("Elements in Queue : ");
		// for (int i = rear; i <= top; i++) {
		// System.out.println(arr[i]);
		// }
		// }
		// }
		//
		// public static void main(String[] args) {
		// Queue queueDemo = new Queue(20);
		// queueDemo.pop();
		// queueDemo.push("23");
		// queueDemo.push("2");
		// queueDemo.push("73");
		// queueDemo.push("21");
		// queueDemo.pop();
		// queueDemo.pop();
		// queueDemo.pop();
		// queueDemo.pop();
		// }

	}
}