package core.designpattern;

import java.util.List;

public class SingletonExample {
	
	private static SingletonExample instance;
	private List<String> list;
	
	private SingletonExample() {
		
	}
	
	public static SingletonExample getInstance() {
		if(instance == null) {
			
			synchronized (SingletonExample.class) {
			
				if(instance == null) {
					instance = new SingletonExample();
				}
			}
			
			
		}
		return instance;
	}

	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}
	
	
}
