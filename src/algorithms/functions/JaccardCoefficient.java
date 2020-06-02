/**
 * 
 */
<<<<<<< Upstream, based on origin/master
<<<<<<< Upstream, based on origin/master
package algorithms.functions;
=======
package functions;
>>>>>>> 74ea2df Changes
=======
package algorithms.functions;
>>>>>>> e7cfaf3 Changes in place

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Wendel F. Lopes
 *
 */
public class JaccardCoefficient {

    public JaccardCoefficient() {
	    // empty
	}
	
	public double similarity(String[] x, String[] y) {
	    double sim=0.0d;
	    if ( (x!=null && y!=null) && (x.length>0 || y.length>0)) {
	                    sim = similarity(Arrays.asList(x), Arrays.asList(y)); 
	    } else {
	            throw new IllegalArgumentException("The arguments x and y must be not NULL and either x or y must be non-empty.");
	    }
	    return sim;
	}
	
	private double similarity(List<String> x, List<String> y) {
	    
	    if( x.size() == 0 || y.size() == 0 ) {
	        return 0.0;
	    }
	    
	    Set<String> unionXY = new HashSet<String>(x);
	    unionXY.addAll(y);
	    
	    Set<String> intersectionXY = new HashSet<String>(x);
	    intersectionXY.retainAll(y);
	
	    return (double) intersectionXY.size() / (double) unionXY.size(); 
	}

}
