/**
 * 
 */
package converter;

import java.util.Arrays;

/**
 * @author Wendel F. Lopes
 *
 */
public class ArrayConverter {
	
	public static String converterArray (int[] array){
		
		String valores = null;
		String value = Arrays.toString(array);
		valores = value.replaceAll("\\[", "").replaceAll("\\]", "");
		
		return valores;
	}
	
	public static String converterArray (long[] array){
		
		String valores = null;
		String value = Arrays.toString(array);
		valores = value.replaceAll("\\[", "").replaceAll("\\]", "");
		
		return valores;
	}
	
	public static String converterArray (float[] array){
		
		String valores = null;
		String value = Arrays.toString(array);
		valores = value.replaceAll("\\[", "").replaceAll("\\]", "");
		
		return valores;
	}
	
	public static String converterArray (double[] array){
		
		String valores = null;
		String value = Arrays.toString(array);
		valores = value.replaceAll("\\[", "").replaceAll("\\]", "");
		
		return valores;
	}
	
	public static String converterArray (String[] array){
		
		String valores = null;
		String value = Arrays.toString(array);
		valores = value.replaceAll("\\[", "").replaceAll("\\]", "");
		
		return valores;
	}

}
