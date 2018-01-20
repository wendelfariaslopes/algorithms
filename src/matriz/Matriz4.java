package matriz;

import java.util.Scanner;

public class Matriz4 {
    
    public static void main(String[] args){
        int[][] matrix = new int[3][2];
        
        Scanner input = new Scanner(System.in);
        System.out.println("Matrix M[3][2]\n");
        
        for(int line=0 ; line < 3 ; line++){
            for(int column = 0; column < 2 ; column++){
                System.out.printf("Type the element M[%d][%d]: ",line+1,column+1);
                matrix[line][column]=input.nextInt();
            }
        }
        
        System.out.println("\nA The Matrix now is: \n");
        for(int line=0 ; line < 3 ; line++){
            for(int column = 0; column < 2 ; column++){
                System.out.printf("\t %d \t",matrix[line][column]);
            }
            System.out.println();
        }
       
    }


}