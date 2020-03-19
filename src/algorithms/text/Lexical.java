/**
 * 
 */
package algorithms.text;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;


/**
 * @author Wendel F. Lopes
 *
 */
public class Lexical {
	
	public static final String[] PREPOSITION = {"aboard","about","above","across","after","against","along","alongside","amid","amidst","among","amongst","anti","around","as","astride","at","atop",
		"bar","barring","before","behind","below","beneath","beside","besides","between","beyond","but","by","because of","but for","by means of",
		"circa","concerning","considering","counting","cum","close to", "contrary to", 
		"despite","down","during","depending on","due to",
		"except","excepting","excluding","except for",
		"following","for","from","forward of","futher to",
		"given","gone",
		"in","including","inside","into","in addition to","in between","in case of","in face of","in favor of","in front of","in spite of","instead of","in view of",
		"less", "like","minus","near","notwithstanding","near to","next","next to",
		"of","off","on","onto","opposite","over","out","outside","on account of","on behalf of","on to","on top of","opposite to","other than","out of","outside of","owing to",
		"pace","past","per","plus","pending","pro","preparatory to","prior",
		"re","regarding","respecting","round","regardless","regardless of",
		"save","saving","since","save for",
		"than","through","thru","throughout","till","to","touching","toward","towards","thanks","thank","thank to","together","together with",
		"under","underneath","unlike","until","up","up against","up to","up until","upon",
		"versus","via","vice",
		"with","within","without","worth","with reference to","with regard to", "the", "is", "'a","a","a few","ah","ALCON","all","y'all","y'all's","allyou","anie","any","anybodies","anybody","anybody's","anybuddy","anyone","anythang","anythin'",
		"anything","anythink","anywhere","'at","aught","bugger","coself","dat","dem","dese","dey","dis","'e","each","eirs","elsewhat","'em","em","emself","'emselves","enough","enuf",
		"'er","everthang", "s", "are","an","that", "be","this","can", "and","it","which", "such","or","more","not", "see","other","one", "isbn","his","her","him","has","had",
		"most","must","was","they","you","also","same","its","different","will","have","found","so","there","them","how","only","when","what","where","who","we","were","very","their",
		"if","way","new","wikipedia","pdf","xml","our","us","yours","just","been","he","three","four","five","sixth","six","one","two","[2]","&nbsp;","[1]","[3]",",",";",":","?",""," ","  ","   ", "    ", " ,", ", "};
	
	public static boolean existWord(String str){
		boolean exist = false;
		for(int i = 0; i < PREPOSITION.length; i++){
            if(PREPOSITION[i].equals(str)){
                exist = true;
                break;
            }
        }
		return exist;
	}
	public static boolean isSmall(String str){
		if(str.length() < 3){
			return true;
		} else{
			return false;
		}
	}
	
	public static boolean isNumber(String str){
		boolean isNum = false;
		StreamTokenizer st = new StreamTokenizer(new StringReader (str));
		try {
			if (st.nextToken() == StreamTokenizer.TT_NUMBER) {
			  isNum = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return isNum;
	}
	

}
