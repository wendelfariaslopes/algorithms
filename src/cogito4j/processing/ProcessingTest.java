package cogito4j.processing;

import algorithms.math.In;
import cogito4j.preprocessing.File;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class ProcessingTest {

    private static final String DIR = "C:\\Users\\Utilisateur\\IdeaProjects\\algorithms\\src\\cogito4j\\preprocessing\\";

    public static void main(String args[])throws IOException {
        // Extract Image
        final String fileName = "img_1.jpg";
        BufferedImage img = File.read(DIR + java.io.File.separator + fileName);

        // Transform Image
        int threshold = 120;
        BufferedImage imgThresholded = ImageHandler.threshold(img, threshold);

        //Store New Image
        final String newFileName = "img_1_t.jpg";
        if (File.write(imgThresholded, DIR + java.io.File.separator + newFileName))
            System.out.println("Image thresholded recorded in file");

        // Load Image
        BufferedImage bufferedImage = File.read(DIR + java.io.File.separator + newFileName);

        System.out.println("Testing convertToMatrix:");
        long startTime = System.nanoTime();
        int[][] matrix = ImageHandler.convertToMatrix(bufferedImage);
        long endTime = System.nanoTime();
        System.out.println(String.format("(Step 1) convertToMatrix() time: "+ Timer.between(startTime, endTime)));

        System.out.println("Testing convert to Integral Image:");
        startTime = System.nanoTime();
        int[][] II = ImageHandler.getIntegralImage(matrix);
        endTime = System.nanoTime();
        System.out.println(String.format("(Step 2) IntegralImage() time: "+ Timer.between(startTime, endTime)));

        System.out.println("Testing generation Haar Features:");
        startTime = System.nanoTime();
        List<HaarFeature> haarFeatureList = generateHaarFeatures(II);
        endTime = System.nanoTime();
        System.out.println(String.format("(Step 3) Generation Haar Features() time: "+ Timer.between(startTime, endTime)));

        haarFeatureList.forEach((k)-> System.out.println(k.getType()));


        System.out.println("Testing getSumAreas in Integral Image:");
        startTime = System.nanoTime();
        HaarFeature haarFeature = haarFeatureList.get(5);
        int area = haarFeature.getWidth()*haarFeature.getHeight();

        for(int i=0; i < (II.length - haarFeature.getWidth()+1); i++) {
            for (int j = 0; j < (II[0].length - haarFeature.getHeight()+1); j++) {
                double similarity = ImageHandler.getSummedArea(i, j, haarFeature.getWidth(), haarFeature.getHeight(), II)/area;
                if(0.4999 < similarity &&  similarity < 0.5001){
                    System.out.println(haarFeature.getType() + " -> getSummedArea = " + similarity+ " (x,y) = (" + i + ","+j+")");
                }


            }
        }

        endTime = System.nanoTime();
        System.out.println(String.format("(Step 4) getSummedArea() time: "+ Timer.between(startTime, endTime)));



    }

    public static List<HaarFeature> generateHaarFeatures(int[][] II){

        List<HaarFeature> haarFeatureList = new ArrayList<>();
        int h = II[0].length/10; // arbitrario 10% da altura == 40 pixels
        int w = II.length;
        //Generate feature que cabem dentro dessa foto em sentido vertical maximo
        for (int f = 0; f < 10; f++){
            //se altura == largura vira quadrado
            HaarFeature haarFeature = new HaarFeature();
            haarFeature.setType("edge_w_"+w+"_h_"+h);
            haarFeature.setWidth(w);
            haarFeature.setHeight(h);
            haarFeatureList.add(haarFeature);
            w-=h;
        }
        return haarFeatureList;
    }

    public static Map<Integer,HaarFeature> finderFeature(List<HaarFeature> haarFeatureList){

        Map<Integer,HaarFeature> haarFeatureFoundMap = new TreeMap<>();

//        for(int i=0; i < (II.length - haarFeature.getWidth()+1); i++) {
//            for (int j = 0; j < II[0].length - haarFeature.getHeight() + 1; j++) {
//
//            }
//        }

        return haarFeatureFoundMap;
    }
}
