/**
 * 
 */
package dict;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import functions.FrequencyWord;


/**
 * @author Wendel F. Lopes
 *
 */
public class SearchGroup {
	
	private static final String NAME = "/Users/wendellopes/Documents/organon/JavaSource/dict/texto_teste.txt";

	
	private static final String ONE_WORD = "[a-zA-Zà-úÀ-Ú]+"; 
	private static final String TWO_WORD = "[a-zA-Zà-úÀ-Ú]+ (of|for|by) [a-zA-Zà-úÀ-Ú]+"; 
	private static final String THREE_WORD = "[a-zA-Zà-úÀ-Ú]+ [a-zA-Zà-úÀ-Ú]+ [a-zA-Zà-úÀ-Ú]+";
	private static final String FOUR_WORD = "[a-zA-Zà-úÀ-Ú]+ [a-zA-Zà-úÀ-Ú]+ [a-zA-Zà-úÀ-Ú]+ [a-zA-Zà-úÀ-Ú]+"; 
	
	
	public static Set<String> twoWords(String text){
		
		Set<String> result = new LinkedHashSet<String>();
		
        Pattern p = Pattern.compile(TWO_WORD);
        Matcher m = p.matcher(text);
        int i = 1;
        while(m.find()) {
            System.out.println("Palavra " + i + ": " + m.group());
            result.add(m.group());
            i++;
        }

		return result;
	}
	
	public static void main(String []args) {
		
		String textFinal = "";
		
		
		try {
		      FileReader arq = new FileReader(NAME);
		      BufferedReader lerArq = new BufferedReader(arq);

		      String linha = lerArq.readLine();
		      textFinal = linha;
		      int i = 1;
		      
		      while (linha != null) {
		        System.out.printf("%s\n", linha);

		        linha = lerArq.readLine(); // lê da segunda até a última linha
		        
		        textFinal+= linha + " ";
		        i+=1;
		      }

		      arq.close();
		      
		    } catch (IOException e) {
		        System.err.printf("Erro na abertura do arquivo: %s.\n",
		          e.getMessage());
		    }
		
		System.out.println(SearchGroup.twoWords(textFinal));
		FrequencyWord.counter(textFinal);
	}

}
