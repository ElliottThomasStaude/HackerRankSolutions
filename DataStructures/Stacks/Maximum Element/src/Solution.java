/*
Transcript of problem:

You have an empty sequence, and you will be given N queries. Each query is one of these three types:

1 x -Push the element x into the stack.
2   -Delete the element present at the top of the stack.
3   -Print the maximum element in the stack.

-- Input Format

The first line of input contains an integer, N. The next N lines each contain an above mentioned query. (It is guaranteed that each query is valid.)

-- Constraints

1 <= N <= 10^5
1 <= x <= 10^9
1 <= type <= 3

-- Output format

For each type 3 query, print the maximum element in the stack on a new line.
*/

import java.io.*;
import java.util.*;

public class Solution {

	/*
	Use a map and a stack; when adding an element to the stack, either set a mapping element to 1 or increment that same element.
	When removing an element from the stack, decrement a mapping element, or (if it is set to 1), remove it from the map.
	If a query is made for type 3, use the map to find the maximum key.
	*/
	
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        
        Stack<Long> inputStack = new Stack<Long>();
        
        TreeMap<Long, Integer> inputMap = new TreeMap<Long, Integer>();
        
        int nValue = inputScanner.nextInt();
        
        for (int inputIndex = 0; inputIndex < nValue; inputIndex++) {
        	int inputType = inputScanner.nextInt();
        	if (inputType == 1) {
        		long inputValue = inputScanner.nextLong();
        		if (inputMap.containsKey(inputValue)) {
        			inputMap.put(inputValue, inputMap.get(inputValue) + 1);
        		} else {
        			inputMap.put(inputValue, 1);
        		}
        		inputStack.push(inputValue);
        	} else if (inputType == 2) {
        		long outputValue = inputStack.pop();
        		if (inputMap.get(outputValue) == 1) {
        			inputMap.remove(outputValue);
        		} else {
        			inputMap.put(outputValue, inputMap.get(outputValue) - 1);
        		}
        	} else {
        		long highestValue = inputMap.lastKey();
        		System.out.println(highestValue);
        	}
        }
    }
}