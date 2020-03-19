package core;

public class ResourcesAvailable {

	public static void main(String[] args) {
		 Runtime runtime = Runtime.getRuntime();
		 System.out.println( "Note: there are " + runtime.availableProcessors() + " cores available\n\n" );
	}

}
