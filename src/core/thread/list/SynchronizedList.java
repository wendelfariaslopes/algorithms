package core.thread.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedList {
	
	static List<String> listSupply;
	static List<String> listDemand;
	static List<String> listTrade;
	
	public static void main(String[] args) {
		//List methods are synchronized
		listSupply = Collections.synchronizedList(new ArrayList<String>());
		listDemand = Collections.synchronizedList(new ArrayList<String>());
		listTrade = Collections.synchronizedList(new ArrayList<String>());
		
		Supply s = new Supply();
		s.setName("Ask");
		s.setSleep(1000);
		
		Demand d = new Demand();
		d.setName("Bid");
		d.setSleep(1000);
		
		BookOrder bo = new BookOrder();
        bo.setName("BookOrder");
		
      //  Trade t = new Trade();
       // t.setSleep(5000);
        
		s.start();
		d.start();
		bo.start();
	//	t.start();
		
		
	}


}
