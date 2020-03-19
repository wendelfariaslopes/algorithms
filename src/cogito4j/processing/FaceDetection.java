package cogito4j.processing;

import Jama.Matrix;

import java.util.Arrays;

public class FaceDetection {

    public static double[] array;

    public static void main(String[] args){
        double[][] originalMatrixImage = {
                {0.1,0.1,0.2,0.1,0.7,0.1},
                {0.2,0.3,0.2,0.7,0.8,0.2},
                {0.1,0.4,0.3,0.3,0.1,0.3},
                {0.1,0.5,0.1,0.1,0.2,0.8},
                {0.1,0.4,0.8,0.5,0.6,0.5}
        };

//       double[][] originalMatrixImage = {
//               {1,1,1,1,1,1},
//               {1,1,1,1,1,1},
//               {1,1,1,1,1,1},
//               {1,1,1,1,1,1},
//               {1,1,1,1,1,1}};

        double[][] I = getIntegralMatrixImage(originalMatrixImage);

        Jama.Matrix matrix = new Matrix(I);
        matrix.print(1,2);


        int x = 2;
        int y = 1;
        int w = 2;
        int h = 3;
        double[][] partI = new double[h][w];

        for (int i = 0; i < partI.length; i++) {
            for (int j = 0; j < partI[0].length; j++) {
                partI[i][j] = I[y+i][x+j];
            }
        }
        Jama.Matrix m = new Matrix(partI);
        m.print(1,2);

        System.out.println("SumArea=I(C)+I(A)-I(B)-I(D) = "+getSumArea(x,y,w,h,I));

    }


    private static double[][] getIntegralMatrixImage(double[][] matrixImage){
        int m = matrixImage.length;
        int n = matrixImage[0].length;
        double[][] integralMatrixImage = new double[m][n];

        double sum = 0.0;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                sum+= matrixImage[i][j];
                integralMatrixImage[i][j]= sum;
            }
            sum = 0.0; // zera a soma da linha
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if(i > 0){
                    integralMatrixImage[i][j]= integralMatrixImage[i][j]+ integralMatrixImage[i-1][j];
                }
            }
        }

        return integralMatrixImage;
    }

    /**
     * SumArea=I(C)+I(A)-I(B)-I(D)
     * where points A,B,C,D belong to the integral image I, as shown in the figure.
     * @param x
     * @param y
     * @param w
     * @param h
     * @return
     */
    private static double getSumArea(int x, int y, int w, int h, double[][] I){
        double A = I[y-1][x-1];
        System.out.println("ponto A = "+A);
        double B = I[y-1][x+(w-1)];
        System.out.println("ponto B = "+B);
        double C = I[y+(h-1)][x+(w-1)];
        System.out.println("ponto C = "+C);
        double D = I[y+(h-1)][x-1];
        System.out.println("ponto D = "+D);
        //SumArea=I(C)+I(A)-I(B)-I(D)
        return C + A - B - D;
    }


}
