/*
Transcript of problem:

Given an array A[] of N distinct elements. Let M_1 and M_2 be the smallest and the next smallest element in the interval [L, R] where 1 <= L < R <= N.

S_i = (((M_1 ^ M_2) (+) (M_1 v M_2)) ^ (M_1 (+) M2)).

where ^, v, (+) are the bitwise operators AND, OR, and XOR respectively.
Your task is to find the maximum possible value of S_i.

-- Input Format

First line contains integer N.
Second line contains N integers, representing elements of the array A[].

-- Constraints 

1 < N <= 10^6
1 <= A_i <= 10^9

-- Output Format

Print the value of maximum possible value of S_i.

*/

import java.io.*;
import java.util.*;

public class Solution {
	
	/*
		The operation sequence (((a AND b) XOR (a OR b)) AND (a XOR b)) is actually equal to (a XOR b). This means that the
		final goal is to ascertain which pair of members has the highest XOR value.
		
		Another programmer presented a major pseudocode component on the relevant forum discussions, which is not original
		to this author; rather, it is used as a template for this implementation in light of its considerable efficiency:
		
		
		For each int i in the array A
		    while stack is nonempty
		        yield (i, top of stack)
		        if i is less than the top of the stack, pop the stack
		        otherwise break the while loop
		    push i onto stack
		    
		**Note - the "yield" function performs a XOR on its arguments and stores the result iff it is larger than the last 
		...stored result
		
		
		The original approach taken by the author was to perform preprocessing, and then traverse an array in a manner to
		optimize the operation (in light of the fact that the goal is to find specific elements in subarrays containing
		only contiguous elements).
		This approach first placed the inputs in an array, checking to see if all inputs had the precise same value, then
		assembled a boolean array detailing whether each integer had a 1 in the bit position for the largest power of 2
		smaller than or equal to the largest input (for example, in the sequence [1, 2, 3, 4, 5, 6, 7], the largest power
		of 2 is 4 = 100; the boolean array would be be [F, F, F, T, T, T, T], since 4, 5, 6, and 7 have the 4 bit set to
		1).
		If all stored input values were identical, then all XOR operations would yield 0, and the algorithm 
		short-circuited.
		If the boolean array were entirely true values, then the booleans would be recalculated after subtracting the 
		highest-bit value (for example, in the sequence [4, 5, 6, 5, 6, 5, 7], the boolean array would initially be all
		true values; the inputs would all be reduced by 4, yielding a newer sequence of [0, 1, 2, 1, 2, 1, 3], and a new
		boolean array of [F, F, T, F, T, F, T] since the new largest-bit value is 2). This step would be repeated until
		some number of false values appeared in the boolean array.
		The inputs would be analyzed with two nested loops, so that exactly one element would be either added to or
		removed from a subsequence of the array's elements. The subsequence would begin by starting with element 0 and
		ending with element 1; elements were then added one at a time until the subsequence went from element 0 to element
		n-1. Element 0 would then be removed, and elements from the "front" end would be removed as well until there were
		only 2 elements left in the subsequence, element 1 and element 2. Element 1 would then be removed, and elements 3
		through n-1 added again, and so on. After each addition or removal, the processing would perform a XOR iff there 
		were at least one true and at least one false in the boolean array elements corresponding to the subsequence's 
		contents.
	*/
	
    
    public static void main(String[] args) {
    	Scanner inputScanner = new Scanner(System.in);
    	
    	int inputCount = inputScanner.nextInt();
    	
    	int xorValue = -1;
    	
    	int[] inputArray = new int[inputCount];
    	for (int inputIndex = 0; inputIndex < inputCount; inputIndex++) {
    		inputArray[inputIndex] = inputScanner.nextInt();
    	}
    	Stack<Integer> inputStack = new Stack<Integer>();
        
    	for (int inputIndex = 0; inputIndex < inputCount; inputIndex++) {
    		int currentElement = inputArray[inputIndex];
    		boolean doLoop = !inputStack.empty();
    		while (doLoop) {
    			int topOfStack = inputStack.peek();
    			int temporaryXorValue = topOfStack ^ currentElement;
    			if (xorValue < temporaryXorValue) {
    				xorValue = temporaryXorValue;
    			}
    			if (currentElement < topOfStack) {
    				inputStack.pop();
    			} else {
    				doLoop = false;
    			}
    			if (inputStack.empty()) {
    				doLoop = false;
    			}
    		}
    		inputStack.push(currentElement);
    	}
    	
    	System.out.println(xorValue);
    	
        inputScanner.close();
    }
}
