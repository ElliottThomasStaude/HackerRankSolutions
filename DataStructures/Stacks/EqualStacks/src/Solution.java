/*
Transcript of problem:

You have three stacks of cylinders where each cylinder has the same diameter, but they may vary in height. You can change the height of a stack by removing and discarding its topmost cylinder any number of times.

Find the maximum possible height of the stacks such that all of the stacks are exactly the same height. This means you must remove zero or more cylinders from the top of zero or more of the three stacks until they're all the same height, then print the height. The removals must be performed in such a way as to maximize the height.

-- Note: an empty stack is still a stack.

-- Input Format

The first line contains three space-separated integers, n1, n2, and n3, describing the respective number of cylinders in stacks 1, 2, and 3. The subsequent lines describe the respective heights of each cylinder in a stack from top to bottom:

The second line contains n1 space-separated integers describing the cylinder heights in stack 1.
The third line contains n2 space-separated integers describing the cylinder heights in stack 2.
The fourth line contains n3 space-separated integers describing the cylinder heights in stack 3.

-- Constraints

0 <= n1, n2, n3 <= 10^5
0 < height of any cylinder <= 100
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	/*
	Do the following until equality is established: compare stack 1 and stack 2; if one is higher than the other, decrease it.
	If stacks 1 and 2 are equal, and stack 3 is greater than stack 1 and stack 2, then decrease stack 3. If stacks 1 and 2 are 
	equal and greater than stack 3, then decrease stack 1. Equality is established when stack 1 is equal to stack 2, and stack
	2 is equal to stack 3.
	*/
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n1 = in.nextInt();
        int n2 = in.nextInt();
        int n3 = in.nextInt();
        // Use deque structures so that the elements can be added in the style of a queue, and stacks don't need to be juggled.
        ArrayDeque<Integer> h1 = new ArrayDeque<Integer>();
        int height1 = 0;
        for (int h1_i=0; h1_i < n1; h1_i++){
        	int inputValue = in.nextInt();
            h1.add(inputValue);
            height1 += inputValue;
        }
        ArrayDeque<Integer> h2 = new ArrayDeque<Integer>();
        int height2 = 0;
        for (int h2_i=0; h2_i < n2; h2_i++){
        	int inputValue = in.nextInt();
            h2.add(inputValue);
            height2 += inputValue;
        }
        ArrayDeque<Integer> h3 = new ArrayDeque<Integer>();
        int height3 = 0;
        for (int h3_i=0; h3_i < n3; h3_i++){
        	int inputValue = in.nextInt();
            h3.add(inputValue);
            height3 += inputValue;
        }
        int equalityValue = -1;
        while (equalityValue == -1) {
        	if (height1 > height2) {
        		int decrease = h1.pop();
        		height1 -= decrease;
        	} else if (height1 < height2) {
        		int decrease = h2.pop();
        		height2 -= decrease;
        	} else {
        		if (height1 > height3) {
        			int decrease = h1.pop();
            		height1 -= decrease;
        		} else if (height1 < height3) {
        			int decrease = h3.pop();
            		height3 -= decrease;
        		} else {
        			equalityValue = height1;
        		}
        	}
        }
        System.out.println(equalityValue);
    }
}

