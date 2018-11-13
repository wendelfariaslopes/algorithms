package core;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Bean> al = new ArrayList<>();
		List<Bean> ll = new LinkedList<>();
		
		for(int i = 0; i < 100; i++) {
			String k = i+"";
			String v = "value "+i;
			al.add(new Bean(k,v));
			ll.add(new Bean(k,v));
		}
		
		

	}


	
}

class Bean{
	String key;
	String value;
	public Bean (String key, String value) {
		this.key = key;
		this.value = value;
	}
}
