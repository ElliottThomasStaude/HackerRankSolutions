/*
Transcript of problem:

The height of a binary tree is the number of edges between the tree's root and its furthest leaf. This means that a tree containing a single node has a height of 0.

Complete the getHeight function provided in your editor so that it returns the height of a binary tree. This function has a parameter, root, which is a pointer to the root node of a binary tree.

-- Note - the Height of binary tree with single node is taken as zero.

-- Input Format

You do not need to read any input from stdin. Our grader will pass the root node of a binary tree to your getHeight function.

-- Output Format

Your function should return a single integer denoting the height of the binary tree.
*/

import java.util.*;
import java.io.*;

class Solution {
/*
Note - only the code specifically in the Solution class's height() function is used in the final solution; the problem in 
question has the code for Node.java and the class definition for Solution auto-included as boilerplate.
*/
	
	/*
	A "default" height should be -1; if a given node has no children, it is a leaf and should have a height of 0. Otherwise,
	the current node's height should be set to the maximum of its children's heights, and then incremented by 1.
	*/
	
	
	/*
    class Node 
    	int data;
    	Node left;
    	Node right;
	*/
	static int height(Node root) {
      	int heightValue = -1;
      	int leftHeight = -1;
      	int rightHeight = -1;
      	if (root == null) {
      		// Do nothing
      	} else {
      		if (root.left == null) {
      			// Do nothing
      		} else {
      			leftHeight = height(root.left);
      		}
      		if (root.right == null) {
      			// Do nothing
      		} else {
      			rightHeight = height(root.right);
      		}
      	}
      	heightValue = leftHeight;
      	if (rightHeight > leftHeight) {
      		heightValue = rightHeight;
      	}
      	heightValue++;
		return heightValue;
    }
	
	public static Node insert(Node root, int data) {
        if(root == null){
            return new Node(data);
        }
        else {
            Node cur;
            if(data <= root.data){
                cur = insert(root.left, data);
                root.left = cur;
            }
            else{
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while(t-- > 0){
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        int height = height(root);
        System.out.println(height);
    }	
}
