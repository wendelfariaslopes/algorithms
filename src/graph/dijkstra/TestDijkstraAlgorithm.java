package graph.dijkstra;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import math.random.Generator;



public class TestDijkstraAlgorithm {

        private static List<Vertex> nodes;
        private static List<Edge> edges;

        public static void main(String [] args) {
        			
        			int size = 200;
        	
                nodes = new ArrayList<Vertex>();
                edges = new ArrayList<Edge>();
                
                for (int i = 0; i < size; i++) {
                        Vertex location = new Vertex("Node_" + i, "Node_" + i);
                        nodes.add(location);
                }
                
//               
//                for (int i = 12; i < size*size; i++) {
//                	int sourceLocNo =  Generator.betweenMinMax(1, 6);
//                	int destLocNo 	=  Generator.betweenMinMax(3, 10);
//                	int duration = Generator.betweenMinMax(50, 800);
//                		addLane("Edge_"+i, sourceLocNo, destLocNo, duration);
//                }
//
                
                for(int i = 0; i < size; i++) {
                		for(int j = 0; j < size; j++) {
                			Random r = new Random();
                			int distance = j-i;
                			if(distance < 0)
                				distance = (-1)*distance;
                			
                			if(distance > 30)
                				distance = 28;
                			
                			int range = r.nextInt(100) +  (int) Math.pow(2, distance);
            		
                				
                			System.out.print("["+i+":"+j+"] ="+ range+" ");
                			addLane("Edge_"+i, i, j, range);
                    }	
                		System.out.println();
                }
//                addLane("Edge_0", 0, 1, 85);
//                addLane("Edge_1", 0, 2, 217);
//                addLane("Edge_2", 0, 4, 173);
//                addLane("Edge_3", 2, 6, 186);
//                addLane("Edge_4", 2, 7, 103);
//                addLane("Edge_5", 3, 7, 183);
//                addLane("Edge_6", 5, 8, 250);
//                addLane("Edge_7", 8, 9, 84);
//                addLane("Edge_8", 7, 9, 167);
//                addLane("Edge_9", 4, 9, 502);
//                addLane("Edge_10", 9, 10, 40);
//                addLane("Edge_11", 1, 10, 900);
//
//                addLane("Edge_12", 9, 10, 100);
                //addLane("Edge_13", 4, 10, 50);
                

                // Lets check from location Loc_1 to Loc_10
                Graph graph = new Graph(nodes, edges);
                DijkstraAlgorithm dijkstra = new DijkstraAlgorithm(graph);
               
                dijkstra.execute(nodes.get(0));
                
                LinkedList<Vertex> path = dijkstra.getPath(nodes.get(size-1));

                System.out.println(path);
                System.out.println(path.size() > 0);

                for (Vertex vertex : path) {
                        System.out.println(vertex);
                }
        }

        private static void addLane(String laneId, int sourceLocNo, int destLocNo,
                        int duration) {
                Edge lane = new Edge(laneId,nodes.get(sourceLocNo), nodes.get(destLocNo), duration );
                edges.add(lane);
        }
        
        private static double randomMinMax(double min,double max) {
    		return min + Math.random() * (max - min);
    	}
        
}