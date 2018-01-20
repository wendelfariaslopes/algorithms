package matriz;

import javax.swing.JOptionPane;

public class MMQ {
	
	public void calcularMedia (){
		
		int k = 0;
		k =	Integer.parseInt(JOptionPane.showInputDialog("Númeo de variáveis"));
		double mediaX = 0;
		double mediaY = 0;
		double somaX = 0;
		double somaY = 0;
		double[] x = new double[k];
		double[] y = new double[k];
		
		for (int i = 0; i < x.length; i++) {
			
			String valorX = JOptionPane.showInputDialog(i+1 +" Valor de X --- Faltam: "+ (x.length - i));
			x[i] = Double.parseDouble(valorX);
			String valorY = JOptionPane.showInputDialog(i+1 +" Valorde Y --- Faltam: "+ (x.length - i));
			y[i] = Double.parseDouble(valorY);
			System.out.println(i + 1 +") Valor inserido de X = " + x[i]+" e Valor inserido de Y = " + y[i]);
			System.out.println("-------------------------------------");
			somaX = somaX + x[i];
			somaY = somaY + y[i];
			
		}

		mediaX = somaX/k;
		mediaY = somaY/k;
		System.out.println("------------------------");
		System.out.println("------------------------");
		System.out.println("Media X: "+mediaX+" Media y: "+mediaY);
		System.out.println("------------------------");
		System.out.println("------------------------");
	
		
		double desvioX = 0;
		double desvioY = 0;
		double somaDesvioX = 0;
		double somaDesvioY = 0;
		for (int i = 0; i < x.length; i++) {
			
			desvioX = x[i] - mediaX;
			desvioY = y[i] - mediaY;
			
			System.out.println("------------------------");
			System.out.println("Desvio de X["+i+"]: "+desvioX+" Desvio de Y["+i+"]: "+desvioY);
			somaDesvioX = somaDesvioX + desvioX;
			somaDesvioY = somaDesvioY + desvioY;
			
		}
		
		System.out.println("--------------------------------------------------------");
		System.out.println("--------------------------------------------------------");
		System.out.println("Soma desvio Y: "+somaDesvioY);
		System.out.println("Soma desvio X: "+somaDesvioX);
		

	}
			
	
	public static void main (String []	args){
		
		MMQ mmq = new MMQ();
		
		mmq.calcularMedia();
		
		
	}
	

}
