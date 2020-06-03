
package algorithms.math;


/*
 * vectorX, vectorY - Arrays of the x and y coordinates of the vertices, traced in a clockwise direction, starting at any vertex. 
 * If you trace them counterclockwise, the result will be correct but have a negative sign.
 * numPoints -	The number of vertices
 * Returns 	 -	the area of the polygon
 */


public class AreaPolygon {

	public static void main(String[] args) {
		int xPts[] = {4,  4,  8,  8, -4,-4};
		int yPts[] = {6, -4, -4, -8, -8, 6};
		
		AreaPolygon ap = new AreaPolygon();
		double a = ap.calculateArea(xPts, yPts, xPts.length);
		System.out.println("Area = "+a);

	}
	
	public double calculateArea(int vectorX[], int vectorY[], int numPoints){
		double area = 0.0;			// Accumulates area in the loop
		int j = numPoints - 1; 		// The last vertex is the 'previous' one to the first
		for(int i = 0; i < numPoints; i++){
			area = area + (vectorX[j]+vectorX[i]) * (vectorY[j]-vectorY[i]);
			j = i;  				//j is previous vertex to i
		}
		return area/2;
	}

}
