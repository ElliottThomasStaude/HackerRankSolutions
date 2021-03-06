/*
Transcript of problem:

Consider an n-integer sequence, A = {a_0, a_1, ..., a_(n-1)}. We perform a query on A by using an integer, d, to calculate the result of the following expression:

min(max(a_j))
0 <= i <= n-d
i <= j <= i+d

In other words, if we let m_i = max(a_i, a_(i+1), a_(i+2), ..., a_(i+d-1)), then you need to calculate min(m_0, m_1, ..., m_(n-d)).

Given A and q queries (each query consists of an integer, d), print the result of each query on a new line.

-- Input Format

The first line consists of two space-separated integers describing the respective values of n and q.
The second line consists of n space-separated integers describing the respective values of a_0, a_1, ..., a_(n-1).
Each of the q subsequent lines contains a single integer denoting the value of d for that query.

-- Constraints

1 <= n <= 10^5
0 <= a_i < 10^6
1 <= q <= 100
1 <= d <= n

-- Output Format

For each query, print an integer denoting the query's answer on a new line. After completing all the queries, you should have printed q lines.
*/

import java.io.*;
import java.util.*;

public class Solution {

	/*
	The approach here is to construct a queue containing the first interval examined, and then find the local maximum in this queue. 
	After the local maximum is found in this interval, record the number of instances it occurs. Set a "global-minimum" value to the
	local maximum. Thereafter, execute the following algorithm until the last element in the input sequence is included in the 
	interval: 
	
	Add a new element from the input stream.
	If the new element is higher than the current maximum, set the maximum count to 1, and set a "new-maximum" flag to true.
	Remove the last element from the queue.
	Perform the following 2 steps only if the new-maximum flag is set to false.
	Decrement the count of maximum values if the removed element has the maximum value.
	If the count of maximum values becomes 0, perform the find-local-maximum process again, recalculating the maximum count.
	If the current maximum is smaller than the global-minimum value, then set the global-minimum to the current maximum.
	*/
	public static int maximumCount = 0;
	
	public static int maximumValue = 0;
	
	public static int minimumValue = 0;
	
	public static void findCurrentIntervalValues(ArrayDeque<Integer> examinedInterval) {
		Iterator<Integer> examinedIntervalIterator = examinedInterval.iterator();
		int localMaximumValue = 0;
		int localMaximumCount = 0;
		while (examinedIntervalIterator.hasNext()) {
			int nextValue = examinedIntervalIterator.next();
			if (localMaximumValue < nextValue) {
				localMaximumValue = nextValue;
				localMaximumCount = 1;
			} else if (localMaximumValue == nextValue) {
				localMaximumCount++;
			}
		}
		Solution.maximumValue = localMaximumValue;
		Solution.maximumCount = localMaximumCount;
	}
	
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int nValue = inputScanner.nextInt();
        int qValue = inputScanner.nextInt();
        int[] inputValueArray = new int[nValue];
        
        // Set up storage for input values
        for (int inputIndex = 0; inputIndex < nValue; inputIndex++) {
        	inputValueArray[inputIndex] = inputScanner.nextInt();
        }
        
        // For each of the q values, perform the above-described operations
        for (int queryIndex = 0; queryIndex < qValue; queryIndex++) {
        	int dValue = inputScanner.nextInt();
        	
        	ArrayDeque<Integer> examinedInterval = new ArrayDeque<Integer>();
        	int currentInputIndex = 0;
        	Solution.maximumValue = 0;
        	for (currentInputIndex = 0; currentInputIndex < dValue; currentInputIndex++) {
        		int inputValue = inputValueArray[currentInputIndex];
        		if (Solution.maximumValue < inputValue) {
        			Solution.maximumValue = inputValue;
        			Solution.maximumCount = 1;
        		} else if (Solution.maximumValue == inputValue) {
        			Solution.maximumCount++;
        		}
        		examinedInterval.add(inputValue);
        	}
        	Solution.minimumValue = Solution.maximumValue;
        	
        	for (currentInputIndex = dValue; currentInputIndex < nValue; currentInputIndex++) {
        		int inputValue = inputValueArray[currentInputIndex];
        		boolean newMaximumFound = false;
        		if (Solution.maximumValue < inputValue) {
        			Solution.maximumValue = inputValue;
        			Solution.maximumCount = 1;
        			newMaximumFound = true;
        		} else if (Solution.maximumValue == inputValue) {
        			Solution.maximumCount++;
        		}
        		examinedInterval.add(inputValue);
        		
        		int ejectionValue = examinedInterval.poll();
        		if (!newMaximumFound) {
        			if (Solution.maximumValue == ejectionValue) {
            			Solution.maximumCount--;
            			if (Solution.maximumCount == 0) {
            				Solution.findCurrentIntervalValues(examinedInterval);
            			}
            		}
        		}
        		
        		if (Solution.minimumValue > Solution.maximumValue) {
        			Solution.minimumValue = Solution.maximumValue;
        		}
        	}
        	System.out.println(Solution.minimumValue);
        }
        inputScanner.close();
    }
}