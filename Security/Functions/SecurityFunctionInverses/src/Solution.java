/*
Transcript of problem:

Consider a bijective function f:X -> Y.
Define another function g:Y -> X so that for x IN X and y IN Y if f(x) = y then g(y) = x.
Now, the function g is said to be the inverse function of f and is denoted as g = f^-1.
In this task, you'll be given an integer n and a bijective function f:X -> X where X = {1, 2, 3,..., n}.
Output the inverse of f.

-- Input Format:

There are 2 lines in the input.
The first line contains a single positive integer n.
The second line contains n space separated integers, the values of f(1), f(2), f(3), ..., f(n), respectively.

-- Constraints:

1 <= n <= 20

-- Output Format:

Output n lines. The ith line should contain the value of f^-1(i).

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int nValue = inputScanner.nextInt();
        
        int[] inverseFunction = new int[nValue + 1];
        
        for (int nIndex = 1; nIndex <= nValue; nIndex++) {
        	int inputValue = inputScanner.nextInt();
        	inverseFunction[inputValue] = nIndex;
        }
        
        for (int nIndex = 1; nIndex <= nValue; nIndex++) {
        	System.out.println(inverseFunction[nIndex]);
        }

        inputScanner.close();
    }
}