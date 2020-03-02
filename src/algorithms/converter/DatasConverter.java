<<<<<<< Upstream, based on origin/master
package algorithms.converter;
=======
package converter;
>>>>>>> 74ea2df Changes

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatasConverter {
	
	/**
	 * Converte uma String para um objeto Date. Caso a String seja vazia ou nula, 
	 * retorna null - para facilitar em casos onde formulários podem ter campos
	 * de datas vazios.
	 * @param data String no formato dd/MM/yyyy a ser formatada
	 * @return Date Objeto Date ou null caso receba uma String vazia ou nula
	 * @throws Exception Caso a String esteja no formato errado
	 */
	public static java.util.Date stringUtilDate(String data) throws Exception { 
		if (data == null || data.equals(""))
			return new java.util.Date();
		
        java.util.Date date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            date = (java.util.Date)formatter.parse(data);
        } catch (ParseException e) {            
            throw e;
        }
        return date;
	}
	
	/**
 	 * Converte uma String para um objeto Date. Caso a String seja vazia ou nula, 
 	 * retorna null - para facilitar em casos onde formulários podem ter campos
 	 * de datas vazios.
 	 * @param data String no formato dd/MM/yyyy a ser formatada
 	 * @return Date Objeto Date ou null caso receba uma String vazia ou nula
 	 * @throws Exception Caso a String esteja no formato errado
 	 */
 	public static java.sql.Date stringSQLDate(String data) throws Exception { 
 		if (data == null || data.equals(""))
 			return new java.sql.Date(0);
 		
         java.sql.Date date = null;
         try {
             DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
             date = new java.sql.Date( ((java.util.Date)formatter.parse(data)).getTime() );
         } catch (ParseException e) {            
             throw e;
         }
         return date;
 	}
 	
 	public static String transformarEmPadraoBrasileiro(String anteFormato){
 		
 		SimpleDateFormat formatador = new SimpleDateFormat("dd/MM/yyyy"); 
 		
 		String novoFormato = formatador.format(anteFormato);
 		
 		return novoFormato;
 	}
 	
 	public static String transformarEmPadraoAmericano(String anteFormato){
 		
 		SimpleDateFormat formatador = new SimpleDateFormat("MM/dd/yyyy"); 
 		
 		String novoFormato = formatador.format(anteFormato);
 		
 		return novoFormato;
 	}
 	
 	public static String transformarEmBancoDados(String anteFormato){
 		
 		SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd"); 
 		
 		String novoFormato = formatador.format(anteFormato);
 		
 		return novoFormato;
 	}
 	
 	public static String transformarEmPadraoExtenso(String anteFormato){
 		
 		SimpleDateFormat formatador = new SimpleDateFormat("dd MMMMMMMM yyyy"); 
 		
 		String novoFormato = formatador.format(anteFormato);
 		
 		return novoFormato;
 	}
 	
 	public static String transformarEmHora(String anteFormato){
 		
 		SimpleDateFormat formatador = new SimpleDateFormat("HH:mm:ss:SSSS"); 
 		
 		String novoFormato = formatador.format(anteFormato);
 		
 		return novoFormato;
 	}
 	
}
