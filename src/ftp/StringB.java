package ftp;

import java.lang.reflect.Field;

public class StringB {

	private static final String str = "Wendel Lopes";

	/*
	 * public static void main (String [] args ){
	 * 
	 * StringB s = new StringB();
	 * 
	 * s.concatenaSQL(); }
	 * 
	 * public static void main(String[] args) throws Exception { String str =
	 * "Wendel Lopes"; mudaString(str); System.out.println("Wendel Lopes"); }
	 */

	public static void mudaString(String str) throws Exception {

		int a = 2;
		int b = 3;

		if (a < b) {

			String ref = str.intern();
			Field value = String.class.getDeclaredField("value"); // 1
			value.setAccessible(true); // 2
			char[] charsDaString = (char[]) value.get(ref); // 3
			charsDaString[0] = '\u0045';
			charsDaString[1] = '\u006E';
			charsDaString[2] = 'c';
			charsDaString[3] = 'r';
			charsDaString[4] = 'i';
			charsDaString[5] = 'p';
			charsDaString[6] = 't';
			charsDaString[7] = 'a';
			charsDaString[8] = 'c';
			charsDaString[9] = 'a';
			charsDaString[10] = 'o';

		}

	}

	/*
	 * 
	 * public static void main (String [] args) {
	 * 
	 * int y = 6; y = foo(y); bar(y,42);
	 * 
	 * }
	 * 
	 * private static int foo (int y){ return y*7; }
	 * 
	 * private static void bar(int y, int z){ if (y == z) {
	 * System.out.println("Sistema Ok"); } else {
	 * System.out.println("Sistema Adulterado"); }
	 * 
	 * }
	 */
	/**
	 * Metodo responsavel por transformar os dados de um codigo
	 * 
	 * Confidencialidade - paramentro que limita ao acesso a informacao a quem
	 * tem permissao - AUTORIZA Integridade - paramentro que garante as
	 * caracteristicas originais Disponibilidade - paramentro que garante
	 * informacao legitima ao autorizado Autenticidade - paramentro que garante
	 * que a informacao e proveniente do emissor original
	 * 
	 * 
	 * 
	 */
	public void transformarDados(int c, int i, int d, int a) {

	}

	/**
	 * Metodo responsavel por transformar a abstracao de um codigo
	 * 
	 * Confidencialidade - paramentro que limita ao acesso a informacao a quem
	 * tem permissao - AUTORIZA Integridade - paramentro que garante as
	 * caracteristicas originais Disponibilidade - paramentro que garante
	 * informacao legitima ao autorizado Autenticidade - paramentro que garante
	 * que a informacao e proveniente do emissor original
	 * 
	 * 
	 * 
	 */

	public static int transformarAbstracao(int entrada, int comparacao,
			int controle) {

		int saida = 0;

		if (controle == 1) {

			saida = entrada * 2;

		} else if (controle == 2) {

			if (entrada == comparacao) {

				saida = entrada * 3;
			}
			saida = entrada * 4;
		}

		return saida;
	}

	/**
	 * Metodo responsavel por transformar controle de um codigo
	 * 
	 * Confidencialidade - paramentro que limita ao acesso a informacao a quem
	 * tem permissao - AUTORIZA Integridade - paramentro que garante as
	 * caracteristicas originais Disponibilidade - paramentro que garante
	 * informacao legitima ao autorizado Autenticidade - paramentro que garante
	 * que a informacao e proveniente do emissor original
	 * 
	 * 
	 * 
	 */

	public void transformarControle(int c, int i, int d, int a) {

	}

	public static void main(String[] args) {

		int num = transformarAbstracao(1, 2, 1);

		System.out.println(num);

	}
}
