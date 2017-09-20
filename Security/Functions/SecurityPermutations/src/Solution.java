/*
Transcript of problem:

Consider a function f:X -> X where X is any set.
If f is a bijection, then f is a permutation function of X. There is nothing special about the set X. It can be replaced by the set {1, 2, 3, ..., n} where n = |X|.
Consider a permutation f given by (2, 3, 1). This means that f(1) = 2, f(2) = 3, and f(3) = 1.
In this task, you're given a permutation f:{1, 2, 3, ..., n} -> {1, 2, 3, ..., n}.
Output f(f(x)) for all x IN {1, 2, 3, ..., n}.

-- Constraints:

1 <= n <= 20

-- Input Format:

There are 2 lines in the input.
The first line contains a single positive integer n.
The second line contains n space separated integers, the values of f(1), f(2), f(3), ..., f(n), respectively.

-- Output Format:

On separate lines, output the values of f(f(1)), f(f(2)), f(f(3)), ..., f(f(n)), respectively.

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	/*
	First, construct the bijective function mapping with an array. Then, follow each domain value to the range value through 
	two invocations of f().
	*/
	
    public static void main(String[] args) {
    	Scanner inputScanner = new Scanner(System.in);
    	
    	int inputSize = inputScanner.nextInt();
    	
    	int inputSizeIncrement = inputSize + 1;
    	int[] inputValues = new int[inputSizeIncrement];
    	inputValues[0] = 0;
    	
    	for (int domainValue = 1; domainValue < inputSizeIncrement; domainValue++) {
    		inputValues[domainValue] = inputScanner.nextInt();
    	}
    	
    	for (int mappingValue = 1; mappingValue < inputSizeIncrement; mappingValue++) {
    		int initialReferencedValue = inputValues[mappingValue];
    		int finalReferencedValue = inputValues[initialReferencedValue];
    		System.out.println(finalReferencedValue);
    	}
    	
        inputScanner.close();
    }
}
