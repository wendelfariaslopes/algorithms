package algorithms.strings;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;


public class JSON {

	private static final char L_PAREN 	= '(';
	private static final char R_PAREN 	= ')';
	private static final char L_BRACE 	= '{';
	private static final char R_BRACE 	= '}';
	private static final char L_BRACKET = '[';
	private static final char R_BRACKET = ']';

	public static final String FILE_PATH = "../cookphysics/WebContent/files/";

	public static void main(String[] args) {

//		String file = "json-obj2.txt";
//
//		String json = Read.reader(FILE_PATH + file);
		// System.out.println(json);
		// find("book", json);
	
		String json = "{Matias:{wendel:{filho:enrico},neila:{filho:icaro}}}";
		String json2 = "{Matias:[wendel:[enrico],neila:[icaro]]}";
		String json3 = "{pai:Matias,filho:[wendel:[enrico],neila:[icaro]]}";
		String json4 = "{pai:Matias,filho:[pai:wendel,[filho:enrico],filha:neila,[icaro]]}";

		String strError = "1{}";
		String str_1 = "{{Ø},Ø}"; //-1
		
		//String vonNeumann = JsonCreate.vonNeumannOrdinal(20);
		
		processObject(json,"");
		
//		JSONObject jsonO = new JSONObject(json);
//		String xml = XML.toString(jsonO);
//		System.out.println(xml);


	}

	public static String[] splitPairs(String str) {
		return str.split(",");
	}

	public static Map<String, String> map(String[] listKV) {

		Map<String, String> map = new HashMap<String, String>();

		for (int i = 0; i < listKV.length; i++) {
			String k = listKV[i].split(":")[0];
			String v = listKV[i].split(":")[1];
			// if(){
			//
			// }
			System.out.println(k + ":" + v);
			map.put(k, v);
		}

		return map;
	}
	
	/* process Object*/
	public static void processObject(String str, String append) {
		
		str = str.replaceAll("\\s+", "");
	
		Map<String, Integer> mapPos = new HashMap<String, Integer>();
			
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == L_BRACE) {
				mapPos.put("L_BRACE_" + i, i);
			}
			if (c == R_BRACE) {
				mapPos.put("R_BRACE_" + i, i);
			}
		}
		
		Map<Integer,String> mapGroup = new HashMap<Integer,String>();
		
		mapPos = sortByValue(mapPos);
		Set<String> keys = mapPos.keySet();
		int counter = 0;
		int g = 0;
		
		for (String key : keys) {
			//System.out.println(key + " " + mapPos.get(key));
			if (key.startsWith("L_BRACE_")) {
				counter++;
			}
			if (key.startsWith("R_BRACE_")) {
				counter--;
			}
			if (counter == 0) {
				g++;
				//System.out.println("Group(" + g + ") "+ mapPos.get(key));
				
				mapGroup.put(g, str.substring(0, mapPos.get(key)+1));
			}
		}
		
		Set<Integer> k = mapGroup.keySet();
		for (Integer i : k) {
			
			String s = "";
			if(i!=1){
				int l = mapGroup.get(i-1).length();
				String token = mapGroup.get(i).substring(l);
				int start = token.indexOf(L_BRACE);
				int end = token.lastIndexOf(R_BRACE);
				
				s = token.substring(start+1,end);
			}else{
				String token = mapGroup.get(i);
				int start = token.indexOf(L_BRACE);
				int end = token.lastIndexOf(R_BRACE);
				s = mapGroup.get(i).substring(start+1,end);
			}
			
			System.out.println(append+s);
			processObject(s, append+"  ");
		}
	}
	
	public static void processArray(String str, String append) {
		
		str = str.replaceAll("\\s+", "");
	
		Map<String, Integer> mapPos = new HashMap<String, Integer>();
			
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			if (c == L_BRACKET) {
				mapPos.put("L_BRACKET_" + i, i);
			}
			if (c == R_BRACKET) {
				mapPos.put("R_BRACKET_" + i, i);
			}
		}
		
		Map<Integer,String> mapGroup = new HashMap<Integer,String>();
		
		mapPos = sortByValue(mapPos);
		Set<String> keys = mapPos.keySet();
		int counter = 0;
		int g = 0;
		
		for (String key : keys) {
			//System.out.println(key + " " + mapPos.get(key));
			if (key.startsWith("L_BRACKET_")) {
				counter++;
			}
			if (key.startsWith("R_BRACKET_")) {
				counter--;
			}
			if (counter == 0) {
				g++;
				//System.out.println("Group(" + g + ") "+ mapPos.get(key));
				mapGroup.put(g, str.substring(0, mapPos.get(key)+1));
			}
		}
		
		Set<Integer> k = mapGroup.keySet();
		for (Integer i : k) {
			
			String s = "";
			if(i!=1){
				int l = mapGroup.get(i-1).length();
				String token = mapGroup.get(i).substring(l);
				int start = token.indexOf(L_BRACKET);
				int end = token.lastIndexOf(R_BRACKET);
				
				s = token.substring(start+1,end);
			}else{
				String token = mapGroup.get(i);
				int start = token.indexOf(L_BRACKET);
				int end = token.lastIndexOf(R_BRACKET);
				s = mapGroup.get(i).substring(start+1,end);
			}
			
			System.out.println(append+s);
			processArray(s, append+"  ");
		}
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
		return map.entrySet().stream()
				.sorted(Map.Entry
						.comparingByValue(/* Collections.reverseOrder() */))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue2(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

	public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue3(Map<K, V> map) {
		List<Map.Entry<K, V>> list = new LinkedList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<K, V>>() {
			public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		Map<K, V> result = new LinkedHashMap<K, V>();
		for (Map.Entry<K, V> entry : list) {
			result.put(entry.getKey(), entry.getValue());
		}
		return result;
	}

}

