package cogito4j.processing;

public class HaarFeature {

    private String type;
    private Position position;
    private int width;
    private int height;
    private double similarity;

    public static final int EDGE_HORIZONTAL=1;
    public static final int EDGE_VERTICAL  =2;
    public static final int LINE_HORIZONTAL=3;
    public static final int LINE_VERTICAL  =4;


    //Step 1 - Haar Feature Selection and Haar-like features factory
    public static double[][] getEdgeX(int m, int n){
        double[][] edge = new double[m][n];
        for (int i = m/2; i < m; i++) {
            for (int j = 0; j < n; j++) {
                edge[i][j]= 1;
            }
        }
        return edge;
    }

    public static double[][] getEdgeY(int m, int n){
        double[][] edge = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = n/2; j < n; j++) {
                edge[i][j]= 1;
            }
        }
        return edge;
    }

    public static double[][] getLineX(int m, int n){
        double[][] edge = new double[m][n];
        for (int i = m/3; i < 2*m/3; i++) {
            for (int j = 0; j < n; j++) {
                edge[i][j]= 1;
            }
        }
        return edge;
    }

    public static double[][] getLineY(int m, int n){
        double[][] edge = new double[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = n/3; j < 2*n/3; j++) {
                edge[i][j]= 1;
            }
        }
        return edge;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public double getSimilarity() {
        return similarity;
    }

    public void setSimilarity(double similarity) {
        this.similarity = similarity;
    }

    private class Position{
        private int x;
        private int y;

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }

}
