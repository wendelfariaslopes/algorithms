/**
 * 
 */
<<<<<<< Upstream, based on origin/master
package algorithms.functions;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import algorithms.text.Lexical;
import algorithms.text.SortMap;
=======
package functions;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dict.Lexical;
import dict.SortMap;
>>>>>>> 74ea2df Changes


public class FrequencyWord {
	
	public static void counter(String text){
		
		Map<String,Integer> mapPalavras; 

		mapPalavras = new HashMap<String,Integer>();
		
		//primeiro converte tudo para minúsculo
    	String minusculo = text.toLowerCase();
    	
    	//depois aplica a expressão regular
    	Pattern p = Pattern.compile("(\\d+)|([a-záéíóúçãõôê]+)");
    	Matcher m = p.matcher(minusculo);
    	int nWords = 0;
    	while(m.find()){
    	 
    	String token = m.group(); //pega um token   
    	  
    	  if(!Lexical.existWord(token) && !Lexical.isNumber(token) && !Lexical.isSmall(token)){
    		Integer freq = mapPalavras.get(token); //verifica se esse token já está no mapa	
  			if (freq != null) { //se palavra existe, atualiza a frequencia
  				mapPalavras.put(token, freq+1);
  			}
  			else { // se palavra não existe, insiro com um novo id e freq=1.
  				mapPalavras.put(token,1);
  			}
    	  }
    	  
		 nWords++;
    	}
    	
    	Map<String,Integer> mapFrequency = new HashMap<String,Integer>();
    	
    	 for (Map.Entry<String, Integer> entry : mapPalavras.entrySet()) {
    		//System.out.println(entry.getKey() + "\tfreq=" + entry.getValue());
    		 if(entry.getValue() > 10){
    			 mapFrequency.put(entry.getKey(), entry.getValue()); 
    		 }
    		
    	 }
    	 
    	//SortMap.orderByKey(mapFrequency);
    	//SortMap.orderByValueDesc(mapFrequency);
    	// System.out.println(nWords);
    	counterAdjectivePhrase(SortMap.orderByValueDesc(mapFrequency), text);

    	 
//    	 for (Map.Entry<String, Integer> entry : mapFrequency.entrySet()) {
//     		System.out.println(entry.getKey() + " freq=" + entry.getValue());
//     		
//     	 }
		
	}
	
	public static void counterAdjectivePhrase(Map<String, Integer> map, String text){
		
		 int i = 0;
		 Iterator it = map.entrySet().iterator();
		 while (it.hasNext() && i < 10) {
		     Map.Entry pair = (Map.Entry)it.next();
		     //System.out.println(pair.getKey() + " = " + pair.getValue());
		     //it.remove(); // avoids a ConcurrentModificationException
		     String str = (String) pair.getKey();
		     int k = (Integer) pair.getValue();
		     System.out.print(pair.getKey());
		     positionWord(str, k, text);
		     System.out.println();
		     i++;
		 }	
	}
	
	public static int[] positionWord(String word, int k,String text){
		
		int array[] = new int[k];
		for (int i = -1; (i = text.indexOf(word, i + 1)) != -1; ) {
		    System.out.print(i+",");
		}
	
		return array;
	}
	


}

