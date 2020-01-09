package algorithms.tests.hackerrank;

import java.io.*;
import java.util.*;


import algorithms.tests.hackerrank.Solution.Heading;
import algorithms.tests.hackerrank.Solution.Node;


public class TestClass2 {
	
	File file = new File("input001.txt");

	public static void main(String[] args) throws java.lang.Exception
	  {
	    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("/Users/wendellopes/eclipse-workspace/algorithms/src/algorithms/tests/hackerrank/input002.txt")));
	    List<Heading> headings = new ArrayList<>();
	    for (String line = br.readLine(); line != null; line = br.readLine()) {
	    		headings.add(parse(line));
	    }
	    
	    toOutline(headings);
	
//	    Node outline = toOutline(headings);
//	    String html = toHtml(outline);
//	    System.out.println(html);
	  }
	
	private static Node toOutline(List<Heading> headings) {
	    // Implement this function. Sample code below builds an
	    // outline of only the first heading.
		
		List<Node> list = new ArrayList<>();    // one List<Node> listed below
		List<Node> listc = new ArrayList<>();
		Node root = new Node(new Heading(0, "Root"));
		list.add(root);
	
		for (Heading h : headings) {
			list.add(new Node(h));
			listc.add(new Node(h));
		}
		int p=0;
	
		
		for (int i = 0; i<list.size();i++) { // comeca a lista
			Node n = list.get(i);
			first:
			for (int j = i+1; j<listc.size();j++) { 
				Node nC = listc.get(j);
				p+=j;
				if(n.heading.weight == (nC.heading.weight - 1)) {
					System.out.print(n.heading.weight+" "+n.heading.text);
					System.out.println(" >"+nC.heading.weight+" "+nC.heading.text);
					//n.children.add(nC);
					
					//break first;
				}
			}
		
		}

		
//		Node l1_n1 = new Node(headings.get(0)); // Level 1 -> Node 1
////		Node l1_n2 = new Node(null); 		   // Level 1 -> Node 2
////		Node l1_n3 = new Node(null); 		   // Level 1 -> Node 3 ...
//		
//		Node l2_n1 = new Node(headings.get(1)); // Level 2 -> Node 1
//		Node l2_n2 = new Node(headings.get(4)); // Level 2 -> Node 2
////		Node l2_n3 = new Node(headings.get(0)); // Level 2 -> Node 3 ...
//		
//		Node l3_n1 = new Node(headings.get(2)); // Level 3 -> Node 1
//		Node l3_n2 = new Node(headings.get(3)); // Level 3 -> Node 2
//		Node l3_n3 = new Node(headings.get(5)); // Level 3 -> Node 3 ...

//		root.children.add(l1_n1); // Insert all children Level 1
//		
//		l1_n1.children.add(l2_n1); // Insert all children Level 2 -> Node 1
//		l1_n1.children.add(l2_n2); // Insert all children Level 2 -> Node 2
//		
//		l2_n1.children.add(l3_n1);
//		l2_n1.children.add(l3_n2);
//		
//		l2_n2.children.add(l3_n3);

	    return root;
	  }
	
	public static void putChildren(Node nf,List<Node> nodes) {
		for(Node nC : nodes) {
			if(nf.heading.weight == (nC.heading.weight - 1)) {
				System.out.print(nf.heading.weight+" "+nf.heading.text);
				System.out.println(" >"+nC.heading.weight+" "+nC.heading.text);
				nf.children.add(nC);
				putChildren(nC,nodes);
				break;
			}
		}
		
	}


	/** Parses a line of input. 
    This implementation is correct for all predefined test cases. */
private static Heading parse(String record) {
  String[] parts = record.split(" ", 2);
  int weight = Integer.parseInt(parts[0].substring(1));
  Heading heading = new Heading(weight, parts[1].trim());
  return heading;
}

private static String toHtml(Node node) {
  StringBuilder buf = new StringBuilder();
  if (!node.heading.text.isEmpty()) {
    buf.append(node.heading.text);
    buf.append("\n");
  }
  Iterator<Node> iter = node.children.iterator();
  if (iter.hasNext()) {
    buf.append("<ol>");
    
    while (iter.hasNext()) {
      Node child = iter.next();
      buf.append("<li>");
      buf.append(toHtml(child));
      buf.append("</li>");
      if (iter.hasNext()) {
        buf.append("\n");
      }
    }
    buf.append("</ol>");
  }
  return buf.toString();
}
	

}

class Solution
{
  public static class Heading {
    protected int weight;
    protected String text;
    
    public Heading(int weight, String text) {
      this.weight = weight;
      this.text = text;
    }
  }
 
  public static class Node {
    protected Heading heading;
    protected List<Node> children;
    
    public Node(Heading heading) {
      this.heading = heading;
      this.children = new ArrayList<>();
    }
  }
}

