package algorithms.trading;

import java.util.ArrayList;
import java.util.List;

public class IOI implements Runnable {
	
			List<String> listCompany = new ArrayList<>();

			List<Order> listAvailable;
		 
		   public void run() {
		      for (int i = 0; i < 13; i++) {

		         System.out.println(Thread.currentThread().getName() + "  " + i);
		         try {
		            // thread to sleep for 1000 milliseconds
		            Thread.sleep(5000);
		         } catch (Exception e) {
		            System.out.println(e);
		         }
		      }
		   }

		   public static void main(String[] args) throws Exception {
			   
			   
			   
			   
		      Thread t = new Thread(new IOI());
		      // this will call run() function
		      t.start();

		      Thread t2 = new Thread(new IOI());
		      // this will call run() function
		      t2.start();
		   }
} 
