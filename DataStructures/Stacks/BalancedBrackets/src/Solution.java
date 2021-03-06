/*
Transcript of problem:

A bracket is considered to be any one of the following characters: (, ), {, }, [, or ].

Two brackets are considered to be a matched pair if an opening bracket (i.e., (, [, or {) occurs to the left of a closing bracket (i.e., ), ], or }) of the exact same type. There are three types of matched pairs of brackets: [], {}, and ().

A matching pair of brackets is not balanced if the set of brackets it encloses are not matched. For example, {[(])} is not balanced because the contents in between { and } are not balanced. The pair of square brackets encloses a single, unbalanced opening bracket, (, and the pair of parentheses encloses a single, unbalanced closing bracket, ].

By this logic, we say a sequence of brackets is considered to be balanced if the following conditions are met:

It contains no unmatched brackets.
The subset of brackets enclosed within the confines of a matched pair of brackets is also a matched pair of brackets.

Given n strings of brackets, determine whether each sequence of brackets is balanced. If a string is balanced, print YES on a new line; otherwise, print NO on a new line.

-- Input Format

The first line contains a single integer, n, denoting the number of strings.

Each line i of the subsequent lines consists of a single string, s, denoting a sequence of brackets.

-- Constraints

1 <= n <= 10^3
1 <= len_s <= 10^3, where len_s is the length of the sequence.

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

	/*
	Make a stack. Upon intake of each bracket, add the bracket to the stack if it is a left bracket. If it is a right bracket, 
	and the bracket at the top of the stack is its twin, then pop the stack and continue; otherwise halt and answer "no". Once
	all brackets have been examined, if the stack is not empty, answer "no". If an answer has not been provided at this time,
	answer "yes".
	*/
	
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nValue = in.nextInt();
        
        for (int nIndex = 0; nIndex < nValue; nIndex++) {
            boolean answeredNo = false;
            Stack<String> inputStack = new Stack<String>();
            
            String stringInput = in.next();
            String[] splitString = stringInput.split("");
            int splitLength = splitString.length;
            
            for (int stringIndex = 0; stringIndex < splitLength; stringIndex++) {
                String s = splitString[stringIndex];
                if (s.equals("(") || s.equals("{") || s.equals("[")) {
                	inputStack.push(s);
                } else {
                	if (inputStack.empty()) {
                		answeredNo = true;
                		System.out.println("NO");
                		stringIndex = splitLength;
                	} else {
                		String topValue = inputStack.pop();
                		if ((s.equals(")") && !topValue.equals("(")) || (s.equals("}") && !topValue.equals("{")) || (s.equals("]") && !topValue.equals("["))) {
                			answeredNo = true;
                    		System.out.println("NO");
                    		stringIndex = splitLength;
                		}
                	}
                }
            }
            if (!answeredNo) {
            	if (!inputStack.empty()) {
            		System.out.println("NO");
            	} else {
            		System.out.println("YES");
            	}
            }
        }
        in.close();
    }
}