package algorithms.common.testdome;

/**
 * Binary search tree (BST) is a binary tree where the value of each node is
 * larger or equal to the values in all the nodes in that node's left subtree
 * and is smaller than the values in all the nodes in that node's right subtree.
 * 
 * Write a function that, efficiently with respect to time used, checks if a
 * given binary search tree contains a given value.
 * 
 * For example, for the following tree:
 * 
 * n1 (Value: 1, Left: null, Right: null) n2 (Value: 2, Left: n1, Right: n3) n3
 * (Value: 3, Left: null, Right: null) Call to contains(n2, 3) should return
 * true since a tree with root at n2 contains number 3.
 * 
 * @author wendellopes
 *
 */

class Node {
	public int value;
	public Node left, right;

	// Constructor with self value and children
	public Node(int value, Node left, Node right) {
		this.value = value;
		
		if(value > left.value && value < right.value) {
			this.left = left;
			this.right = right;
		}else {
			System.err.println("You can't do it, because this one Binary Search Tree");
		}
		
	}

	// Constructor with self value
	public Node(int value) {
		this.value = value;
		this.left = null;
		this.right = null;
	}
	
	public static Node createNode(int number) {
		return new Node(number);
	}
}

public class BinarySearchTree {

	Node root;

	public static boolean contains(Node node, int value) {
		// throw new UnsupportedOperationErootception("Waiting to be implemented.");
		if (node == null)
			return false;

		// Step 1 - we found the value
		if (node.value == value)
			return true;
		// Step 2 - Value is less than node value. so go left sub tree
		if (value < node.value) {
			return contains(node.left, value);
			// Step 3 - Value is greater than node value. so go right sub tree
		} else {
			return contains(node.right, value);
		}

	}

	public static void main(String[] args) {
		
		int [] ar = new int[10];
		System.out.println(Math.abs(ar.length));
		

		
		Node n1 = new Node(1, null, null);

		Node n3 = new Node(3, null, null);

		Node n2 = new Node(2, n1, n3);// create node with value and connect (link) with yours children
		
		// create one node with one value
		// root level 0
		Node n4 = Node.createNode(4);
		
		// level 1
		Node n5 = Node.createNode(5);
		
		// connect (link)
		n4.left=n2;
		n4.right=n5;
		

		System.out.println(contains(n4, 3));
	}

	

}
