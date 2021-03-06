/*
Transcript of problem:

Alexa has two stacks of non-negative integers, stack A = [a_0, a_1, ..., a_n-1] and stack B = [b_0, b_1, ..., b_m-1] where index 0 denotes the top of the stack. Alexa challenges Nick to play the following game:

In each move, Nick can remove one integer from the top of either stack A or stack B.
Nick keeps a running sum of the integers he removes from the two stacks.
Nick is disqualified from the game if, at any point, his running sum becomes greater than some integer x given at the beginning of the game.
Nick's final score is the total number of integers he has removed from the two stacks.

Given A, B, and x for g games, find the maximum possible score Nick can achieve (i.e., the maximum number of integers he can remove without being disqualified during each game and print it on a new line.

-- Input Format

The first line contains an integer, g (the number of games). The 3g subsequent lines describe each game in the following format:
1. The first line contains three space separated integers describing the respective values of n (the number of integers in stack A), m (the number of integers in stack B), and x (the number that the sum of the integers removed from the two stacks cannot exceed).
2. The second line contains n space-separated integers describing the respective values of a_0, a_1, ..., a_n-1.
3. The third line contains m space-separated integers describing the respective values of b_0, b_1, ..., b_m-1.

-- Constraints

1 <= g <= 50
1 <= n, m <= 10^5
0 <= a_i, b_j <= 10^6
1 <= x <= 10^9

-- Subtasks

1 <= n, m, <= 100 for 50% of the maximum score.

-- Output Format

For each of the g games, print an integer on a new line denoting the maximum possible score Nick can achieve without being disqualified.
*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	/*
	Take as many elements from stack A as possible so that the total is less than or equal to x, unless stack A is exhausted. The 
	elements removed from stack A should go on another stack, called holder A here. At this point, if stack A is empty, the same 
	should be done for stack B, so that the sum of elements in both holder A and holder B (which has the meaning one thinks it 
	has) is less than or equal to x. At this point, "exchange" elements removed from stack A for elements removed from stack B;
	that is, move one element from holder A (if possible, and necessary) back onto stack A, and then move as many elements as 
	possible from stack B to holder B so that the sum of elements in both holder stacks is less than or equal to x. Continue 
	until either no more elements exist that can be moved in this way.
	*/
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int g = in.nextInt();
        for (int a0 = 0; a0 < g; a0++) {
            int n = in.nextInt();
            int m = in.nextInt();
            int x = in.nextInt();
            ArrayDeque<Integer> stackA = new ArrayDeque<Integer>();
            Stack<Integer> holderA = new Stack<Integer>();
            for (int a_i=0; a_i < n; a_i++) {
            	stackA.add(in.nextInt());
            }
            ArrayDeque<Integer> stackB = new ArrayDeque<Integer>();
            Stack<Integer> holderB = new Stack<Integer>();
            for (int b_i=0; b_i < m; b_i++) {
            	stackB.add(in.nextInt());
            }
            int aCount = 0;
            int bCount = 0;
            int stackASum = 0;
            int stackBSum = 0;
            while (stackASum <= x && !stackA.isEmpty()) {
            	int topValue = stackA.pop();
            	stackASum += topValue;
            	holderA.push(topValue);
            	aCount++;
            }
            while (stackASum > x) {
            	int replaceValue = holderA.pop();
            	stackASum -= replaceValue;
            	stackA.push(replaceValue);
            	aCount--;
            }
            while (stackASum + stackBSum <= x && !stackB.isEmpty()) {
            	int topValue = stackB.pop();
            	stackBSum += topValue;
            	holderB.push(topValue);
            	bCount++;
            }
            while (stackASum + stackBSum > x) {
            	int replaceValue = holderB.pop();
            	stackBSum -= replaceValue;
            	stackB.push(replaceValue);
            	bCount--;
            }
        	int bestMaxValue = aCount + bCount;
        	while (!holderA.empty() && !stackB.isEmpty()) {
        		int replaceValue = holderA.pop();
            	stackASum -= replaceValue;
            	stackA.push(replaceValue);
            	aCount--;
        		while (stackASum + stackBSum <= x && !stackB.isEmpty()) {
        			int topValue = stackB.pop();
                	stackBSum += topValue;
                	holderB.push(topValue);
                	bCount++;
        		}
        		while (stackASum + stackBSum > x) {
        			replaceValue = holderB.pop();
                	stackBSum -= replaceValue;
                	stackB.push(replaceValue);
                	bCount--;
        		}
        		while (bestMaxValue < aCount + bCount) {
        			bestMaxValue = aCount + bCount;
        		}
        	}
            System.out.println(bestMaxValue);
        }
        in.close();
    }
}