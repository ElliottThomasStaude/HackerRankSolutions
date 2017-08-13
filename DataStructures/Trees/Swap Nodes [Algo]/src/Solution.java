/*
Transcript of problem:

A binary tree is a tree which is characterized by any one of the following properties:
- It can be an empty (null).
- It contains a root node and two subtrees, left subtree and right subtree. These subtrees are also binary tree.

Inorder traversal is performed as
1. Traverse the left subtree.
2. Visit root (print it).
3. Traverse the right subtree.

(For an Inorder traversal, start from the root and keep visiting the left subtree recursively until you reach the leaf, then you print the node at which you are and then you visit the right subtree.)

We define depth of a node as follow:
- Root node is at depth 1.
- If the depth of parent node is d, then the depth of current node will be d+1.

-- Swapping: Swapping subtrees of a node means that if initially node has left subtree L and right subtree R, then after swapping left subtree will be R and right subtree L.

-- Swap operation: Given a tree and a integer, K, we have to swap the subtrees of all the nodes who are at depth h, where h IN [K, 2K, 3K, ...].

You are given a tree of N nodes where nodes are indexed from [1... N] and it is rooted at 1. You have to perform T swap operations on it, and after each swap operation print the inorder traversal of the current state of the tree.

-- Input Format

First line of input contains N, number of nodes in tree. Then N lines follow. Here each of ith line (1 <= i <= N) contains two integers, a b, where a is the index of left child, and b is the index of right child of ith node. -1 is used to represent null node.
Next line contain an integer, T. Then again T lines follows. Each of these line contains an integer K.

-- Output Format
For each k, perform swap operation as mentioned above and print the inorder traversal of the current state of tree.

-- Constraints
1 <= N <= 1024
1 <= T <= 100
1 <= K <= N
Either a = -1 or 2 <= a <= N
Either b = -1 or 2 <= b <= N
Index of (non-null) child will always be greater than that of parent.
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	/*
	Given the necessity of using the Solution class for this problem, a TreeMap is employed for tracking tree structure. The
	TreeMap's values are themselves TreeMaps, with keys 0 and 1; these respectively point to the left and right children values
	of each node.
	The tree is first assembled from the inputs by creating and adding/updating TreeMap objects in the structuring TreeMap.
	After all the inputs have been translated into new TreeMap contents, a recursive function is used to switch the respective
	contents of any nodes at the appropriate depth.
	*/

	public static TreeMap<Integer, TreeMap<Integer, Integer>> treeStructure;
	
	public static TreeMap<Integer, Integer> makeShellTree() {
		TreeMap<Integer, Integer> shellTree = new TreeMap<Integer, Integer>();
		shellTree.put(0, -1);
		shellTree.put(1, -1);
		return shellTree;
	}
	
	public static boolean addNodeToTree(int parentValue) {
    	treeStructure.put(parentValue, makeShellTree());
		return true;
	}
	
	public static int getTreeDepth(int parentValue) {
		if (parentValue == -1) {
			return 0;
		} else {
			TreeMap<Integer, Integer> subtree = treeStructure.get(parentValue);
			int leftDepth = getTreeDepth(subtree.get(0));
			int rightDepth = getTreeDepth(subtree.get(1));
			int returnDepth = leftDepth;
			if (rightDepth > leftDepth) {
				returnDepth = rightDepth;
			}
			returnDepth++;
			return returnDepth;
		}
	}
	
	public static boolean setLeftChild(int parentValue, int childValue) {
		if (treeStructure.containsKey(parentValue)) {
			TreeMap<Integer, Integer> subtree = treeStructure.get(parentValue);
			subtree.put(0, childValue);
			treeStructure.put(parentValue, subtree);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean setRightChild(int parentValue, int childValue) {
		if (treeStructure.containsKey(parentValue)) {
			TreeMap<Integer, Integer> subtree = treeStructure.get(parentValue);
			subtree.put(1, childValue);
			treeStructure.put(parentValue, subtree);
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean swapChildren(int parentValue, int depth) {
		if (parentValue == -1) {
			return false;
		} else {
			TreeMap<Integer, Integer> subtree = treeStructure.get(parentValue);
			int leftValue = subtree.get(0);
			int rightValue = subtree.get(1);
			if (depth == 1) {
				subtree.put(0, rightValue);
				subtree.put(1, leftValue);
				treeStructure.put(parentValue, subtree);
			} else {
				int childDepth = depth - 1;
				swapChildren(leftValue, childDepth);
				swapChildren(rightValue, childDepth);
			}
		}
		return true;
	}
	
	public static String readTree(int parent) {
		TreeMap<Integer, Integer> subtree = treeStructure.get(parent);
		int leftValue = subtree.get(0);
		String leftString = "";
		if (leftValue != -1) {
			leftString = readTree(leftValue) + " ";
		}
		int rightValue = subtree.get(1);
		String rightString = "";
		if (rightValue != -1) {
			rightString = " " + readTree(rightValue);
		}
		String subtreeString = leftString + Integer.toString(parent) + rightString;
		return subtreeString;
	}
	
    public static void main(String[] args) {
		treeStructure = new TreeMap<Integer, TreeMap<Integer, Integer>>();
        Scanner inputScanner = new Scanner(System.in);
        
        ArrayDeque<Integer> nodeQueue = new ArrayDeque<Integer>();
        nodeQueue.add(1);
        int nodeCount = inputScanner.nextInt();
        
        for (int nodeIndex = 0; nodeIndex < nodeCount; nodeIndex++) {
        	int lastNode = nodeQueue.remove();
        	while (lastNode == -1) {
        		lastNode = nodeQueue.remove();
        	}
        	addNodeToTree(lastNode);
        	int leftChild = inputScanner.nextInt();
        	nodeQueue.add(leftChild);
        	int rightChild = inputScanner.nextInt();
        	nodeQueue.add(rightChild);
        	setLeftChild(lastNode, leftChild);
        	setRightChild(lastNode, rightChild);
        }
        
        int maximumDepth = getTreeDepth(1);
        
        int swapCount = inputScanner.nextInt();
        for (int swapIndex = 0; swapIndex < swapCount; swapIndex++) {
        	int baseSwapDepth = inputScanner.nextInt();
        	int swapDepth = baseSwapDepth;
        	
        	swapChildren(1, swapDepth);
        	while (swapDepth + baseSwapDepth <= maximumDepth) {
        		swapDepth += baseSwapDepth;
        		swapChildren(1, swapDepth);
        	}
        	
            String treeReadout = readTree(1);
            System.out.println(treeReadout);
        }
        
        inputScanner.close();
    }
}