package algorithms.ai.ml.image;
import java.awt.Color;

public class Flip {

    public static void main(String[] args) {
    	
        Picture pic = new Picture("../algorithms/src/algorithms/ai/ml/image/enrico.jpeg");
        int width  = pic.width();
        int height = pic.height();
        pic.show();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width / 2; x++) {
                Color c1 = pic.get(x, y);
                Color c2 = pic.get(width - x - 1, y);
                pic.set(x, y, c2);
                pic.set(width - x - 1, y, c1);
            }
        }
        
        pic.show();
    }
 

   
}