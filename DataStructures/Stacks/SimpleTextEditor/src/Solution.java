/*
Transcript of problem:

In this challenge, you must implement a simple text editor. Initially, your editor contains an empty string, S. You must perform Q operations of the following 4 types:

1. append(W) - Append string W to the end of S.
2. delete(k) - Delete the last k characters of S.
3. print(k) - Print the kth character of S.
4. undo() - Undo the last (not previously undone) operation of type 1 or 2, reverting S to the state it was in prior to that operation.

-- Input Format

The first line contains an integer, Q, denoting the number of operations.
Each line i of the Q subsequent lines (where 0 <= i < Q) defines an operation to be performed. Each operation starts with a single integer, t (where t IN {1, 2, 3, 4}), denoting a type of operation as defined in the Problem Statement above. If the operation requires an argument, t is followed by its space-separated argument. For example, if t = 1 and W = "abcd", line i will be "1 abcd".

-- Constraints

1 <= Q <= 10^6
1 <= k <= |S|
The sum of the lengths of all W in the input <= 10^6.
The sum of k over all delete operations <= 2 * 10^6.
All input characters are lowercase English letters.
It is guaranteed that the sequence of operations given as input is possible to perform.

-- Output Format

Each operation of type 3 must print the kth character on a new line.
*/

import java.io.*;
import java.util.*;

public class Solution {

	/*
	Perform the dictated operations as they are invoked; when append/delete operations are called normally, pass an additional
	parameter value of TRUE, and FALSE when called through an undo invocation. Each append/delete called normally stores an
	operation that has the inverse effect on the "history" stack; simply accessing and performing the last operation the stack
	holds is the equivalent of performing an undo operation.
	*/
	
	public static Stack<String> operationStack;
	public static String textValue = "";
	
	public static void type1Append(String W, boolean calledNormally) {
		Solution.textValue = Solution.textValue + W;
		if (calledNormally) {
			String operationStackElement = "2" + Integer.toString(W.length());
			Solution.operationStack.push(operationStackElement);
		}
		return;
	}
	
	public static void type2Delete(int k, boolean calledNormally) {
		int textValueLength = Solution.textValue.length();
		int textMinusK = textValueLength - k;
		if (calledNormally) {
			String removedValue = Solution.textValue.substring(textMinusK, textValueLength);
			String operationStackElement = "1" + removedValue;
			Solution.operationStack.push(operationStackElement);
		}
		Solution.textValue = Solution.textValue.substring(0, textMinusK);
		return;
	}
	
	public static void type3Print(int k) {
		System.out.println(Solution.textValue.substring(k - 1, k));
		return;
	}
	
	public static void type4Undo() {
		String lastOperation = Solution.operationStack.pop();
		int lastOperationLength = lastOperation.length();
		int operationType = Integer.parseInt(lastOperation.substring(0, 1));
		if (operationType == 1) {
			String argumentString = lastOperation.substring(1, lastOperationLength);
			Solution.type1Append(argumentString, false);
		} else {
			// Type 2, if not type 1
			int argumentInt = Integer.parseInt(lastOperation.substring(1, lastOperationLength));
			Solution.type2Delete(argumentInt, false);
		}
		return;
	}
	
    public static void main(String[] args) {
    	Scanner inputScanner = new Scanner(System.in);
    	
    	Solution.operationStack = new Stack<String>();
    	
    	int inputCount = inputScanner.nextInt();
    	
    	for (int inputIndex = 0; inputIndex < inputCount; inputIndex++) {
    		int inputType = inputScanner.nextInt();
    		if (inputType == 1) {
    			String wValue = inputScanner.next();
    			Solution.type1Append(wValue, true);
    		} else if (inputType == 2) {
    			int kValue = inputScanner.nextInt();
    			Solution.type2Delete(kValue, true);
    		} else if (inputType == 3) {
    			int kValue = inputScanner.nextInt();
    			Solution.type3Print(kValue);
    		} else {
    			// Type 4, if none of the others
    			Solution.type4Undo();
    		}
    	}
    	
    	inputScanner.close();
    }
}
