package algorithms.data.structure;

//Java program to find height of tree 
/*maxDepth()
1. If tree is empty then return 0
2. Else
     (a) Get the max depth of left subtree recursively  i.e., 
          call maxDepth( tree->left-subtree)
     (a) Get the max depth of right subtree recursively  i.e., 
          call maxDepth( tree->right-subtree)
     (c) Get the max of max depths of left and right 
          subtrees and add 1 to it for the current node.
         max_depth = max(max dept of left subtree,  
                             max depth of right subtree) 
                             + 1
     (d) Return max_depth
*/
//A binary tree node 
class Node2 
{ 
 int data; 
 Node2 left, right; 

 Node2(int item)  
 { 
     data = item; 
     left = right = null; 
 } 
} 

public class BinaryTree2  
{ 
  Node2 root; 

 /* Compute the "maxDepth" of a tree -- the number of  
    nodes along the longest path from the root node  
    down to the farthest leaf node.*/
 int maxDepth(Node2 node)  
 { 
     if (node == null) 
         return 0; 
     else 
     { 
         /* compute the depth of each subtree */
         int lDepth = maxDepth(node.left); 
         int rDepth = maxDepth(node.right); 

         /* use the larger one */
         if (lDepth > rDepth) 
             return (lDepth + 1); 
          else 
             return (rDepth + 1); 
     } 
 } 
    
 /* Driver program to test above functions */
 public static void main(String[] args)  
 { 
     BinaryTree2 tree = new BinaryTree2(); 

     tree.root = new Node2(1); 
     tree.root.left = new Node2(2); 
     tree.root.right = new Node2(3); 
     tree.root.left.left = new Node2(4); 
     tree.root.left.right = new Node2(5); 

     System.out.println("Height of tree is : " +  
                                   tree.maxDepth(tree.root)); 
 } 
} 
