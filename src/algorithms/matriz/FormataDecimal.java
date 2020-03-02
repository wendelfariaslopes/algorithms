<<<<<<< Upstream, based on origin/master
package algorithms.matriz;
=======
package matriz;
>>>>>>> 74ea2df Changes

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class FormataDecimal {
	
	/** 
	 *  1 - Valor a arredondar. 
	 *  2 - Quantidade de casas depois da vírgula. 
	 *  3 - Arredondar para cima ou para baixo? 
	 *          Para cima = 0 (ceil) 
	 *          Para baixo = 1 ou qualquer outro inteiro (floor) 
	 **/  
	public static double arredondar(double valor, int casas, int ceilOrFloor) {  
	    double arredondado = valor;  
	    arredondado *= (Math.pow(10, casas));  
	    if (ceilOrFloor == 0) {  
	        arredondado = Math.ceil(arredondado);             
	    } else {  
	        arredondado = Math.floor(arredondado);  
	    }  
	    arredondado /= (Math.pow(10, casas));  
	    return arredondado;  
	}  
	
	
	/**
	 * Formata a exibicao do numero sem alterar sua precisao.
	 * 
	 * @param valor a ser formatado
	 * @return valor formatado
	 */
	public static String formatarDeciamal(double valor){
		String formatado = 
				new DecimalFormat("0.00").format(valor);
		return formatado;
	}
	 
	public static BigDecimal arredondarParaBaixo(double valor, int casas){
	
		BigDecimal valorExato = new BigDecimal(valor)  
		        .setScale(casas, RoundingMode.HALF_DOWN);
		
		return valorExato;
	}
	
	public static BigDecimal arredondarParaCima(double valor, int casas){
		
		BigDecimal valorExato = new BigDecimal(valor)  
		        .setScale(casas, RoundingMode.HALF_UP);
		
		return valorExato;
	}
	

}
