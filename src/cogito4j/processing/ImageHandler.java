package cogito4j.processing;

import algorithms.ai.ml.image.Luminance;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;

public class ImageHandler {

    public static void drawRect(BufferedImage image, int x, int y, int w, int h, Color color){
        java.awt.Graphics2D greenRect = image.createGraphics();
		greenRect.setColor(color);
		greenRect.drawRect(x, y, w, h);
		greenRect.dispose();
    }

    public static BufferedImage crop(BufferedImage image, int x, int y, int width, int height) {
        return image.getSubimage(x, y, width, height);
    }

    public static BufferedImage grayScale(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();

        for(int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int p = image.getRGB(x, y);

                int a = (p >> 24) & 0xff;
                int r = (p >> 16) & 0xff;
                int g = (p >> 8) & 0xff;
                int b = p & 0xff;

                //calculate average
                //int avg = (r+g+b)/3;
                int avg = (int) (Math.round(0.299 * r + 0.587 * g + 0.114 * b));

                //replace RGB value with avg
                p = (a << 24) | (avg << 16) | (avg << 8) | avg;

                image.setRGB(x, y, p);
            }
        }
        return image;
    }

    public static BufferedImage threshold(BufferedImage image, int threshould) {
        for (int i = 0; i < image.getWidth(); i++) {
            for (int j = 0; j < image.getHeight(); j++) {
                Color color = new Color(image.getRGB(i, j));
                double lum = Luminance.intensity(color);
                if (lum >= threshould) {
                    image.setRGB(i, j, Color.WHITE.getRGB());
                } else {
                    image.setRGB(i, j, Color.BLACK.getRGB());
                }
            }
        }
        return image;
    }

    public static int[][] convertToMatrix(BufferedImage image) {
        int w = image.getWidth();
        int h = image.getHeight();
        int[][] vector = new int[w][h];
        for (int i = 0; i < h; i++) {
            for (int j = 0; j < w; j++) {
                vector[i][j] = image.getRGB(j, i) == 0xFFFFFFFF ? 0 : 1;
            }
        }
        return vector;
    }

//    public static int[][] convertToMatrix(BufferedImage image) {
//
//        final byte[] pixels = ((DataBufferByte) image.getRaster().getDataBuffer()).getData();
//        final int width = image.getWidth();
//        final int height = image.getHeight();
//        final boolean hasAlphaChannel = image.getAlphaRaster() != null;
//
//        int[][] result = new int[height][width];
//        if (hasAlphaChannel) {
//            final int pixelLength = 4;
//            for (int pixel = 0, row = 0, col = 0; pixel + 3 < pixels.length; pixel += pixelLength) {
//                int argb = 0;
//                argb += (((int) pixels[pixel] & 0xff) << 24); // alpha
//                argb += ((int) pixels[pixel + 1] & 0xff); // blue
//                argb += (((int) pixels[pixel + 2] & 0xff) << 8); // green
//                argb += (((int) pixels[pixel + 3] & 0xff) << 16); // red
//                result[row][col] = argb;
//                col++;
//                if (col == width) {
//                    col = 0;
//                    row++;
//                }
//            }
//        } else {
//            final int pixelLength = 3;
//            for (int pixel = 0, row = 0, col = 0; pixel + 2 < pixels.length; pixel += pixelLength) {
//                int argb = 0;
//                argb += -16777216; // 255 alpha
//                argb += ((int) pixels[pixel] & 0xff); // blue
//                argb += (((int) pixels[pixel + 1] & 0xff) << 8); // green
//                argb += (((int) pixels[pixel + 2] & 0xff) << 16); // red
//                result[row][col] = argb;
//                col++;
//                if (col == width) {
//                    col = 0;
//                    row++;
//                }
//            }
//        }
//
//        return result;
//    }

    public static double[][] getIntegralImage(double[][] matrixImage){
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

    public static int[][] getIntegralImage(int[][] matrixImage){
        int m = matrixImage.length;
        int n = matrixImage[0].length;
        int[][] integralMatrixImage = new int[m][n];

        int sum = 0;

        for (int i = 0; i < m; i++){
            for (int j = 0; j < n; j++){
                sum+= matrixImage[i][j];
                integralMatrixImage[i][j]= sum;
            }
            sum = 0; // zera a soma da linha
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

    public static double getSummedArea(int x, int y, int w, int h, double[][] I){
        double A = I[y-1][x-1];
        System.out.println("ponto A = "+A);
        double B = I[y-1][x+(w-1)];
        System.out.println("ponto B = "+B);
        double C = I[y+(h-1)][x+(w-1)];
        System.out.println("ponto C = "+C);
        double D = I[y+(h-1)][x-1];
        System.out.println("ponto D = "+D);
        //SumArea=I(C)+I(A)-I(B)-I(D) -> where points A,B,C,D belong to the integral image I, as shown in the figure.
        return C + A - B - D;
    }

    public static double getSummedArea(int x, int y, int w, int h, int[][] I){

        double A = 0;
        double B = 0;
        double C = I[y+(h-1)][x+(w-1)];
        double D = 0;
        if(x!=0 && y!=0){
            A = I[y-1][x-1];
            B = I[y-1][x+(w-1)];
            D = I[y+(h-1)][x-1];
        }
       // System.out.println("ponto A = "+A+ " B = "+B+" C = "+C+" D = "+D);

        //SumArea=I(C)+I(A)-I(B)-I(D) -> where points A,B,C,D belong to the integral image I, as shown in the figure.
        return C + A - B - D;
    }

}
