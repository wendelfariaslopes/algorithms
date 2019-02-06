package algorithms.data.structure;

public class BinaryTree {
	
	public static void main(String[] args) {
		Node node = createTree();

		System.out.println("Deep = " + maxDepth(node));
		System.out.println();
		printTree(node, " ");

	}

	private static Node createTree() {
		Node node = new Node(1);

		Node nodeL1 = node.addChildLeft(new Node(2));
		Node nodeL2 = nodeL1.addChildLeft(new Node(4));
		Node nodeL3 = nodeL1.addChildRight(new Node(6));

		Node nodeR1 = node.addChildRight(new Node(3));
		Node nodeR2 = nodeR1.addChildRight(new Node(5));
		Node nodeR3 = nodeR1.addChildLeft(new Node(7));

		return node;
	}

	private static void printTree(Node node, String appender) {
		
		if (node == null) {
			System.out.println("Nothing....");

		}
		
		if(node.getParent()!=null) {
			System.out.print("("+node.getParent().getKey()+")");
		}
		
		
		System.out.println(appender + node.getKey());
		
		
		if (node.getLeft() != null) {
			
			printTree(node.getLeft(), appender+appender);

		}
		if (node.getRight() != null) {
			printTree(node.getRight(), appender+appender);

		}
		
	}

	public static int maxDepth(Node node) {
		if (node == null)
			return 0;

		int leftDepth = maxDepth(node.getLeft());
		int rightDepth = maxDepth(node.getRight());

		int bigger = Math.max(leftDepth, rightDepth);

		return bigger + 1;
	}

}

class Node {

	private int key;
	private Node parent;
	private Node left = null;
	private Node right = null;

	public Node(int key) {
		this.key = key;
	}

	// add child left
	public Node addChildLeft(Node left) {
		left.setParent(this);
		this.left = left;
		return this.left;
	}

	// add child right
	public Node addChildRight(Node right) {
		right.setParent(this);
		this.right = right;
		return this.right;
	}

	public void setKey(int key) {
		this.key = key;
	}

	public int getKey() {
		return key;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public void setLeft(Node left) {
		this.left = left;
	}

	public Node getLeft() {
		return left;
	}

	public void setRight(Node right) {
		this.right = right;
	}

	public Node getRight() {
		return right;
	}

}