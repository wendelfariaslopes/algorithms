package algorithms.ai.ml.image;

import java.awt.Color;

public class Threshold {

    public static void main(String[] args) {
        int THRESHOLD = 100;
        String filename = "../algorithms/src/algorithms/ai/ml/image/enrico.jpeg";
        Picture pic = new Picture(filename);
        pic.show();
        for (int i = 0; i < pic.width(); i++) {
            for (int j = 0; j < pic.height(); j++) {
                Color color = pic.get(i, j);
                double lum = Luminance.intensity(color);
                if (lum >= THRESHOLD) pic.set(i, j, Color.WHITE);
                else                  pic.set(i, j, Color.BLACK);
            }
        }
        pic.show();
    }
}

