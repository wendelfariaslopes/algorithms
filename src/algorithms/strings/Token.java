package algorithms.strings;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

public class Token {
	
	public static void extrairToken (String obj) throws IOException{
	
		StreamTokenizer st = new StreamTokenizer(new StringReader (obj));
		st.ordinaryChar('/');
		while (st.nextToken() != StreamTokenizer.TT_EOF) {
	            switch (st.ttype) {
	            case StreamTokenizer.TT_NUMBER:
	                System.out.println ("Valor: " + st.nval);
	                break;
	            case StreamTokenizer.TT_WORD:
	                System.out.println ("Palavra: '" + st.sval + "'");
	                break;
	            case StreamTokenizer.TT_EOL:
	                System.out.println ("Fim de Linha:");
	                break;
	            case '(':
	                System.out.println ("Abertura de Parenteses");
	                break;
	            case ')':
	                System.out.println ("Fechamento de Parenteses");
	                break;
	            case '[':
	                System.out.println ("Abertura de Chaves");
	                break;
	            case ']':
	                System.out.println ("Fechamento de Chaves");
	                break;    
	            case '+':
	                System.out.println ("Operador Soma");
	                break;
	            case '-':
	                System.out.println ("Operador Subtracao");
	                break;
	            case '/':
	                System.out.println ("Operador Divisao");
	            case '*':
	                System.out.println ("Operador Multiplicacao");
	                break;
	            case '^':
	                System.out.println ("Operador Potencia");
	                break;    
	            default:
	                System.out.println ("Token nao reconhecido = '" + (char) st.ttype + "'");
	            }
	        }
		
	
	}
	
	
	
	
}
