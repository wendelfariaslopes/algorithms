/**
 * 
 */
package validator;


/**
 * @author Wendel F. Lopes
 *
 */
public class AnaliseErroImp {
	
	private static Throwable pilha = new Throwable();
	
	private static StackTraceElement [] elemento = pilha.getStackTrace();

	/**
	 * @param erro the erro to set
	 */
	public static void analisarErro(Exception erro) {

			System.out.println("");
			System.out.println("___________________________________________________________________________________________");
			System.out.println("");
			System.out.println("   Erro Encontrado: "+erro.getMessage());
			System.out.println("___________________________________________________________________________________________");
			System.out.println("");
			System.out.println("   Local: " + elemento[1].getClassName());
			System.out.println("   Classe: " + elemento[1].getFileName() + " : " + elemento[1].getFileName().length()+ " Kb "  + " Instaciada: " + elemento[1].getClassName().length()+" Kb ");
			System.out.println("   Metodo: " + elemento[1].getMethodName() + " : "+elemento[1].getMethodName().length()+" Kb "); 
			System.out.println("   Linha: " +elemento[1].getLineNumber());
			System.out.println("   Metodo nativo: " +elemento[1].isNativeMethod());
			System.out.println("___________________________________________________________________________________________");
			System.out.println("");
			System.out.println("   Pilha de Classes Envolvidas: "+elemento.length+" Classes.");
			System.out.println("___________________________________________________________________________________________");
			System.out.println("");
		
	}
	
	public static String analisarErroView(Exception erro) {
		
		String erroMsg = null;
		erroMsg = "_______________________n/" +
				"Erro Encontrado: " + erro.getMessage() + "n/" +
				"_______________________n/" +
				" Local: " + elemento[1].getClassName() + "n/" +
				" Classe: " + elemento[1].getFileName() + " : " + 
				elemento[1].getFileName().length()+ " Kb "  + " Instaciada: " + 
				elemento[1].getClassName().length()+" Kb n/" +
				" Local: " + elemento[1].getClassName() + "n/" +
				" Metodo: " + elemento[1].getMethodName() + " : " + 
				elemento[1].getMethodName().length()+" Kb n/" +
				" Linha: " +elemento[1].getLineNumber() + "n/" +
				" Metodo nativo: " +elemento[1].isNativeMethod() +"/n" +
				"_______________________n/" +
				" Pilha de Classes Envolvidas: "+elemento.length+" Classes.";
		return erroMsg;
	}
	
}
