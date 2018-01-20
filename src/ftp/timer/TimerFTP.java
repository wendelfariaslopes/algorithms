package ftp.timer;

import java.util.Timer;
import java.util.TimerTask;

import javax.faces.bean.ManagedBean;


@ManagedBean (name="timerFTP")
public class TimerFTP {  
    
    public static final long TEMPO_I = (1000 * 5); // atualiza o site a cada 1 minuto  
    public static final long TEMPO_II = 1000; // atualiza o site a cada 1 minuto 
    private static int i;
    
    public static void main (String [] args){
    	executarTarefa();
    }
    
    public static void executarTarefa(){
    	
    	System.out.println("inicio");  
        Timer timer = null;  
        if (timer == null) {  
            timer = new Timer();  
            
            TimerTask tarefa = new TimerTask() {  
                public void run() {  
                    try {  
                    	
                    	
                    	System.out.println("Tarefa I "+i); 
                    		
						++i;
                         
                        //chamar metodo  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            };
            
            TimerTask tarefa2 = new TimerTask() {  
                public void run() {  
                    try {  
                    	
                    	
                    	System.out.println("Tarefa II "+i); 
                    		
						++i;
                         
                        //chamar metodo  
                    } catch (Exception e) {  
                        e.printStackTrace();  
                    }  
                }  
            };
            
            timer.scheduleAtFixedRate(tarefa, 1000, TEMPO_I); 
            timer.scheduleAtFixedRate(tarefa2, 5000, TEMPO_II);  
        }  
    	
    }
    
}  