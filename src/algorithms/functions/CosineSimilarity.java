package algorithms.functions;




import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CosineSimilarity {

public static double consineTextSimilarity(String[] left, String[] right) {
    
	Map<String, Integer> leftWordCountMap = new HashMap<String, Integer>();
    Map<String, Integer> rightWordCountMap = new HashMap<String, Integer>();
    Set<String> uniqueSet = new HashSet<String>();
    Integer temp = null;
    
    for (String leftWord : left) {
        temp = leftWordCountMap.get(leftWord);
        if (temp == null) {
            leftWordCountMap.put(leftWord, 1);
            uniqueSet.add(leftWord);
        } else {
            leftWordCountMap.put(leftWord, temp + 1);
        }
    }
    
    for (String rightWord : right) {
        temp = rightWordCountMap.get(rightWord);
        if (temp == null) {
            rightWordCountMap.put(rightWord, 1);
            uniqueSet.add(rightWord);
        } else {
            rightWordCountMap.put(rightWord, temp + 1);
        }
    }
    
    int[] leftVector = new int[uniqueSet.size()];
    int[] rightVector = new int[uniqueSet.size()];
    int index = 0;
    Integer tempCount = 0;
    
    for (String uniqueWord : uniqueSet) {
        tempCount = leftWordCountMap.get(uniqueWord);
        leftVector[index] = tempCount == null ? 0 : tempCount;
        tempCount = rightWordCountMap.get(uniqueWord);
        rightVector[index] = tempCount == null ? 0 : tempCount;
        index++;
    }
    return consineVectorSimilarity(leftVector, rightVector);
}

private static double consineVectorSimilarity(int[] leftVector,
        int[] rightVector) {
    if (leftVector.length != rightVector.length)
        return 1;
    double dotProduct = 0;
    double leftNorm = 0;
    double rightNorm = 0;
    for (int i = 0; i < leftVector.length; i++) {
        dotProduct += leftVector[i] * rightVector[i];
        leftNorm += leftVector[i] * leftVector[i];
        rightNorm += rightVector[i] * rightVector[i];
    }

    double result = dotProduct
            / (Math.sqrt(leftNorm) * Math.sqrt(rightNorm));
    return result;
}

public static void main(String[] args) {
    String left[] = { "Julie", "loves", "me", "more", "than", "Linda",
            "loves", "me" ,"aaaaa","nn","cccc"};
    String right[] = {  "mmmmmmmmmmm" , "loves",  "more", "me", "than", "Linda",
            "loves","me","aaaaa","nn","cccc"};
//    String right[] = { "Julie", "loves", "me", "more", "than", "Cinda",
//            "loves", "me" };
    System.out.println(consineTextSimilarity(left,right));
}

}

