/**
 * 
 */
package algorithms.text;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import algorithms.functions.FrequencyWord;

/**
 * @author Wendel F. Lopes
 *
 */
public class ClusteringText {

	private static final String NAME = "/Users/wendellopes/Documents/organon/JavaSource/dict/texto_teste.txt";

	public static void main(String[] args) {

		String textFinal = "";

		try {
			FileReader arq = new FileReader(NAME);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine();
			textFinal = linha;

			while (linha != null) {
				linha = lerArq.readLine(); // lê da segunda até a última linha
				textFinal += linha + " ";

			}
			arq.close();

		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		FrequencyWord.counter(textFinal);
	}

}
