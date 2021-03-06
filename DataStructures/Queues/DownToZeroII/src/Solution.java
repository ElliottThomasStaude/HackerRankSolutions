/*
Transcript of problem:

You are given Q queries. Each query consists of a single number N. You can perform any of the 2 operations on N in each move:

1: If we take 2 integers a and b where N = a x b (a != 1, b != 1) then we can change N = max(a, b)

2: Decrease the value of N by 1.

Determine the minimum number of moves required to reduce the value of N to 0.

--Input Format

The first line contains the integer Q.
The next Q lines each contain an integer, N.

--Constraints

1 <= Q <= 10^3
0 <= N <= 10^6

--Output Format

Output Q lines. Each line containing the minimum number of moves required to reduce the value of N to 0.
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        int maximumNumber = 1000002;
        int[] sizeOfSteps = new int[maximumNumber];
        // Part 1: compute the maximum number of steps from Q to 0, which is Q
        for (int positionIndex = 0; positionIndex < maximumNumber; positionIndex++) {
        	sizeOfSteps[positionIndex] = positionIndex;
        }
        // Part 2: perform multiplications with each number of steps, to find which elements are 1 step away from the current step count
        for (int positionIndex = 1; positionIndex < maximumNumber; positionIndex++) {
        	int currentValue = sizeOfSteps[positionIndex];
        	int lastValue = sizeOfSteps[positionIndex - 1];
        	if (currentValue > lastValue + 1) {
        		sizeOfSteps[positionIndex] = lastValue + 1;
        	}
        	currentValue = sizeOfSteps[positionIndex];
        	for (int innerPositionIndex = 2; innerPositionIndex <= positionIndex && positionIndex * innerPositionIndex < maximumNumber; innerPositionIndex++) {
        		int product = positionIndex * innerPositionIndex;
        		int productValue = sizeOfSteps[product];
        		if (productValue > currentValue + 1) {
        			sizeOfSteps[product] = currentValue + 1;
        		}
        	}
        }
        // Part 3: consult the table that was already made in parts 1 and 2 for the best values in each number's case
        int queryCount = inputScanner.nextInt();
        for (int queryIndex = 0; queryIndex < queryCount; queryIndex++) {
            int parentNumber = inputScanner.nextInt();
            int moveCount = sizeOfSteps[parentNumber];
            System.out.println(moveCount);
        }
    }
}