/*
Transcript of problem:

Kitty has a tree, T, consisting of n nodes where each node is uniquely labeled from 1 to n. Her friend Alex gave her q sets, where each set contains k distinct nodes. Kitty needs to calculate the following expression on each set:
(SIGMA{u, v} u * v * dist(u, v)) mod (10^9 + 7)
where:
{u, v} denotes an unordered pair of nodes belonging to the set
dist(u, v) denotes the number of edges on the unique path between nodes u and v.

Given T and q sets of k distinct nodes, can you help her calculate the expression for each set? For each set of nodes, print the value of the expression modulo 10^9 + 7 on a new line.

-- Input Format

The first line contains two space-separated integers describing the respective values of n (the number of nodes in tree T) and q (the number of sets).
Each of the n-1 subsequent lines contains two space separated integers, a and b, describing an undirected edge between nodes a and b.
The 2 * q subsequent lines define each set over two lines in the following format:
1. The first line contains an integer, k, denoting the size of the set.
2. The second line contains k space-separated integers describing the set's elements.

-- Constraints

1 <= n <= 2 * 10^5
1 <= a, b <= n
1 <= q <= 10^5
1 <= k_i <= 10^5
The sum of k_i over all q does not exceed 2 * 10^5.
All elements in each set are distinct.

-- Subtasks

1 <= n <= 2000 for 24% of the maximum score.
1 <= n <= 5 * 10^4 for 45% of the maximum score.
1 <= n <= 2 * 10^5 for 100% of the maximum score.

-- Output Format

Print q lines of output where each line i contains the expression for the ith query, modulo 10^9 + 7.
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
	/*
	Each calculation is a chain of sum operations, followed by an application of the modulo function. The summed elements are
	products of three numbers - two provided integers and a calculated value which is the distance between two tree nodes.
	
	The process first constructs the tree one edge at a time. Then, for each distinct pair of endpoints in the provided sets,
	a breadth-first search is performed to determine how far apart the two endpoints lie.
	*/
	
	public static TreeMap<Long, TreeSet<Long>> treeStructure;
	
	public static boolean addGraphVertex(long vertex0, long vertex1) {
		if (!treeStructure.containsKey(vertex0)) {
			TreeSet<Long> zeroSet = new TreeSet<Long>();
			treeStructure.put(vertex0, zeroSet);
		}
		TreeSet<Long> zeroSet = treeStructure.get(vertex0);
		boolean zeroAdd = zeroSet.add(vertex1);
		
		if (!treeStructure.containsKey(vertex1)) {
			TreeSet<Long> oneSet = new TreeSet<Long>();
			treeStructure.put(vertex1, oneSet);
		}
		TreeSet<Long> oneSet = treeStructure.get(vertex1);
		boolean oneAdd = oneSet.add(vertex0);
		
		boolean addingSuccess = zeroAdd & oneAdd;
		return addingSuccess;
	}
	
    public static void main(String[] args) {
    	treeStructure = new TreeMap<Long, TreeSet<Long>>();
        Scanner inputScanner = new Scanner(System.in);
        
        long moduloSize = 1000000007;
        
        int vertexCount = inputScanner.nextInt();
        int edgeCount = vertexCount - 1;
        int setCount = inputScanner.nextInt();
        
        // Assemble tree
        for (int vertexIndex = 0; vertexIndex < edgeCount; vertexIndex++) {
        	int firstVertex = inputScanner.nextInt();
        	int secondVertex = inputScanner.nextInt();
        	addGraphVertex(firstVertex, secondVertex);
        }
        
        // Perform operation on tree for each set
        for (int setIndex = 0; setIndex < setCount; setIndex++) {
        	int sizeOfSet = inputScanner.nextInt();
        	int sizeOfSetDecrement = sizeOfSet - 1;
        	long operationValue = 0;
        	
    		long[] setElementArray = new long[sizeOfSet];
        	for (int setElementIndex = 0; setElementIndex < sizeOfSet; setElementIndex++) {
        		setElementArray[setElementIndex] = inputScanner.nextLong();
        	}
        	
        	if (sizeOfSet > 1) {
		        for (int sizeOfSetIndex = 0; sizeOfSetIndex < sizeOfSetDecrement; sizeOfSetIndex++) {
		        	long firstEndpoint = setElementArray[sizeOfSetIndex];
		        	for (int closingIndex = sizeOfSetIndex + 1; closingIndex < sizeOfSet; closingIndex++) {
		        		long distance = 0;
		        		long secondEndpoint = setElementArray[closingIndex];
		        		
		        		TreeSet<Long> elementsSeen = new TreeSet<Long>();
				        TreeSet<Long> currentElements = new TreeSet<Long>();
				        TreeSet<Long> passAlongElements = new TreeSet<Long>();
				        currentElements.add(firstEndpoint);
				        
				        // Perform a breadth-first search for the target endpoint
				        while (!currentElements.contains(secondEndpoint)) {
				        	
				        	while (!currentElements.isEmpty()) {
				        		long firstElement = currentElements.first();
				        		currentElements.remove(firstElement);
				        		
				        		TreeSet<Long> neighboringSet = new TreeSet<Long>();
				        		neighboringSet.addAll(treeStructure.get(firstElement));
				        		
				        		while (!neighboringSet.isEmpty()) {
				        			long firstNeighbor = neighboringSet.first();
					        		if (!currentElements.contains(firstNeighbor) && !passAlongElements.contains(firstNeighbor) && !elementsSeen.contains(firstNeighbor)) {
					        			passAlongElements.add(firstNeighbor);
					        		}
					        		neighboringSet.remove(firstNeighbor);
				        		}
				        		
				        		elementsSeen.add(firstElement);
				        	}
				        	
				        	currentElements.addAll(passAlongElements);
				        	passAlongElements = new TreeSet<Long>();
				        	distance++;
				        }
				        
				        long addValue = distance * firstEndpoint * secondEndpoint;
				        operationValue += addValue;
				        operationValue = operationValue % moduloSize;
		        	}
		        }
	        }
        	System.out.println(operationValue);
    	}
        inputScanner.close();
    }
}
