package algorithms.converter;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class ArraySemRepeticao {

	public static Set<String> retirarRepeticao(List<String> lista) {

		// Seu SET agora contem os elementos, sem repetição.

		Set<String> semRepeticao = new LinkedHashSet<String>();

		for (String valor : lista) {
			semRepeticao.add(valor);
		}

		return semRepeticao;
	}

	public static Set<String> retirarRepeticao(String[] array) {

		// Seu SET agora contem os elementos, sem repetição.

		Set<String> semRepeticao = new LinkedHashSet<String>();

		for (String valor : array) {
			semRepeticao.add(valor);
		}

		return semRepeticao;
	}

	public static Set<Number> retirarRepeticao(int[] array) {

		// Seu SET agora contem os elementos, sem repetição.

		Set<Number> semRepeticao = new LinkedHashSet<Number>();

		for (int valor : array) {
			semRepeticao.add(valor);
		}

		return semRepeticao;
	}

	public static Set<Number> retirarRepeticao(long[] array) {

		// Seu SET agora contem os elementos, sem repetição.

		Set<Number> semRepeticao = new LinkedHashSet<Number>();

		for (long valor : array) {
			semRepeticao.add(valor);
		}

		return semRepeticao;
	}

	public static Set<Number> retirarRepeticao(float[] array) {

		// Seu SET agora contem os elementos, sem repetição.

		Set<Number> semRepeticao = new LinkedHashSet<Number>();

		for (double valor : array) {
			semRepeticao.add(valor);
		}

		return semRepeticao;
	}

	public static Set<Number> retirarRepeticao(double[] array) {

		// Seu SET agora contem os elementos, sem repetição.

		Set<Number> semRepeticao = new LinkedHashSet<Number>();

		for (double valor : array) {
			semRepeticao.add(valor);
		}

		return semRepeticao;
	}

}
