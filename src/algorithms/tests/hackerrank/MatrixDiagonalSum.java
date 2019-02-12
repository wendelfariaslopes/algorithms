package algorithms.tests.hackerrank;


public class MatrixDiagonalSum {

    // Complete the diagonalDifference function below.
    static int diagonalDifference(int[][] arr) {
        int sumLR=0, sumRL=0;
        for(int i = 0; i < arr.length;i++){
            for(int j=0;j<arr[0].length;j++){
                if(i==j)
                    sumLR+=arr[i][j];
                if(i == arr[0].length - 1 - j)   
                    sumRL+=arr[i][j]; 
            }
        }
        return Math.abs(sumLR-sumRL);
    }


}
