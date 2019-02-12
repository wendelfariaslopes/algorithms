package algorithms.tests.hackerrank;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class ClimbValey {

	static Map<Integer, CharGraphic> map;

	public static void main(String[] args) {

		String s = "UDDDUDUU";
		map = new HashMap<>();

		int v = 0; // # of valleys
		int seaLevel = 0; // current level

		String ud = "";
		// for(char c : s.toCharArray()){
		for (int p = 0; p < s.length(); p++) {
			if (s.codePointAt(p) == 'U') {
				++seaLevel;
				ud = "/";
			}
			if (s.codePointAt(p) == 'D') {
				--seaLevel;
				ud = "\\";
			}

			map.put(p, new CharGraphic(p, seaLevel, ud));

			// if (seaLevel == 0 && s.codePointAt(p) == 'U' && s.codePointBefore(p) == 'U')
			if (seaLevel == 0 && s.codePointAt(p) == 'U')
				++v;
		}
		System.out.println(v);

		print(map);
		// Map -> Stream -> Filter -> MAP
		// Map<Integer, String> collect = unsortMap.entrySet().stream().filter(x ->
		// x.getKey() == 2)
		// .collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

	}

	private static void print(Map<Integer, CharGraphic> unsortMap) {

		// printMap(unsortMap);

		// Comparator<Integer> c = (o1, o2) -> o2.compareTo(o1);
		Comparator<Integer> c = (o1, o2) -> o1.compareTo(o2);

		Map<Integer, CharGraphic> treeMap = new TreeMap<>(c);
		treeMap.putAll(unsortMap);

		printMap2(treeMap);

		// Map<Integer,CharGraphic> treeMap2 = new TreeMap<>(
		// new Comparator<Integer>() {
		//
		// @Override
		// public int compare(Integer o1, Integer o2) {
		// return o2.compareTo(o1);
		// }
		//
		// });

	}

	public static <K, V> void printMap(Map<K, V> map) {
		for (Map.Entry<K, V> entry : map.entrySet()) {
			System.out.println("Key : " + entry.getKey() + " Value : " + entry.getValue());
		}

	}

	public static void printMap2(Map<Integer, CharGraphic> map) {
		for (Integer i : map.keySet()) {
			System.out.println("X: " + i + " Y:" + map.get(i).y + " Simbol: " + map.get(i).c);
		}

		// for (int r=-map.size(); r< map.size();r++ ) {
		// if(r>0) {
		// System.out.print(" "+r);
		//
		//
		// }else if(r<0){
		// System.out.println(r+" ...................................................");
		// }else {
		// System.out.println(" "+r);
		// }
		//
		// }
		//
		// for (Integer i: map.keySet()) {
		// System.out.print(map.get(i).c);
		// }

		position(map);
	}

	public static Predicate<Diff> isDifferenceLessThan(double maxDiff) {
		return p -> Math.abs(p.getD2() - p.getD1()) < maxDiff;
	}

	public static void position(Map<Integer, CharGraphic> map) {
		for (int i = 0; i < map.size(); i++) {
			
			if (map.get(i).y == 0) {
				px(i, map.get(i).x, map.get(i).c);
			}	
			
			if (map.get(i).y == -1) {
				System.out.println();
				px(i, map.get(i).x, map.get(i).c);
				
			}
			
			if (map.get(i).y == -2) {
				System.out.println();
				px(i, map.get(i).x, map.get(i).c);
			}	
			
			
			
		}

	}
	
	public static void px(int i, int x, String c) {
		for (int p = 0; p < x; p++) {
			System.out.print(" ");
		}
		if (i == x) {
			System.out.print(map.get(i).c);
		}
	}

}

class CharGraphic {
	int x;
	int y;
	String c;

	public CharGraphic(int x, int y, String c) {
		this.x = x;
		this.y = y;
		this.c = c;
	}
}

class Diff {
	private double d1;
	private double d2;

	public Diff(double d1, double d2) {
		this.d1 = d1;
		this.d2 = d2;
	}

	public double getD1() {
		return d1;
	}

	public void setD1(double d1) {
		this.d1 = d1;
	}

	public double getD2() {
		return d2;
	}

	public void setD2(double d2) {
		this.d2 = d2;
	}

}
