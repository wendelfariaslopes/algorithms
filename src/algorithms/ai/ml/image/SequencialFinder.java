package algorithms.ai.ml.image;

import cogito4j.Metrics;

public class SequencialFinder {

	public static void main(String[] args) {

		String name = "cat_1";
		String nameFile = name +"-reduce-by-90";
		java.awt.image.BufferedImage image = Image.load(Image.DIR + nameFile+ ".jpg");
		
		System.out.println("Image Info: {width="+image.getWidth()+",height="+image.getHeight());
		
		int width = 20;
		int height = 20;
		java.awt.image.BufferedImage subImageToFind = image.getSubimage(10, 10, width, height);
		double[] subVectorToFind = Image.imageToVector(subImageToFind);
		
		double percentSimilarityLow = 0.80;
		double percentSimilarityMedium = 0.90;
		double percentSimilarityHigh = 0.95;
		
		
			boolean test = true;
		loopLine:
		for(int i=0; i < (image.getWidth() - width+1); i++) {
			for(int j=0; j < image.getHeight() - height+1; j++) {
				java.awt.image.BufferedImage fractionImageToAnalise = image.getSubimage(i, j, width, height);
				double[] fractionToAnalise = Image.imageToVector(fractionImageToAnalise);
				
				
				
				double similar = Metrics.pearson(fractionToAnalise, subVectorToFind);
				
				if (similar >= percentSimilarityLow && similar < percentSimilarityMedium) {
					System.out.println("LOW -> Found in line = "+i+" colunm = "+j);			
				}else if(similar >= percentSimilarityMedium && similar <= percentSimilarityHigh){
					System.out.println("Medium -> Found in line = "+i+" colunm = "+j);
				}else if(similar > percentSimilarityHigh){
					System.out.println("High -> Found in line = "+i+" colunm = "+j);
		
				    break loopLine;
				}
				
				System.out.println("Finder in line = "+i+" colunm = "+j);
			}
		}
		
		

	}

	public static int indexOf(double[] big, double[] small) {
		for (int i = 0; i < big.length - small.length + 1; ++i) {
			boolean found = true;
			for (int j = 0; j < small.length; ++j) {
				if (big[i + j] != small[j]) {
					found = false;
					break;
				}
			}
			if (found)
				return i;
		}
		return -1;
	}

//	public static int indexOf(double[] big, double[] small, double percentSimilarity) {
//
//		for (int i = 0; i < big.length - small.length + 1; ++i) {
//
//			double[] blockToTest = new double[small.length];
//			for (int j = 0; j < small.length; ++j) {
//				blockToTest[j] = big[i + j];
//			}
//
//			// System.out.println(Arrays.toString(blockToTest));
//			if (Metrics.pearson(blockToTest, small) >= percentSimilarity) {
//				return i;
//			}
//		}
//		return -1;
//	}
	
	public static int indexOf(double[] big, double[] small, double percentSimilarity) {

		for (int i = 0; i < big.length - small.length + 1; ++i) {

			double[] blockToTest = new double[small.length];
			for (int j = 0; j < small.length; ++j) {
				blockToTest[j] = big[i + j];
			}

			// System.out.println(Arrays.toString(blockToTest));
			if (Metrics.pearson(blockToTest, small) >= percentSimilarity) {
				return i;
			}
		}
		return -1;
	}

	public static double[] insertSequenceInArray(double[] array, double[] sequence, int insertOf) {
		for (int i = 0; i < array.length; i++) {
			if (i == insertOf) {
				for (int j = 0; j < sequence.length; j++) {
					array[i] = sequence[j];
					++i;
				}
			}
		}
		return array;
	}

}
