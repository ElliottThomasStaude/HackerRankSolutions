/*
Transcript of problem:

Before we jump into security concepts, let us familiarize ourselves with the mathematical background required for it.
Set X is a collection of elements. Here, X = {1, 2, 3} is one such example. A collection of integers is also a set.
Given two sets, X and Y, we define a function f that maps every element in X to precisely 1 element in Y.
If X = {1, 2, 3} and Y = {alpha, gamma, delta}, the function f will return:
f(1) = alpha, f(2) = gamma and f(3) = delta.
Let us define a function f_1(x) = x_r, where x IN X and x_r IN Y.
Here, x_r is defined as the remainder of x when divided by 11.
Your task is to complete the function that takes the input x and returns x_r.

-- Constraints:
1 <= x <= 1000

*/

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    static int calculate(int x) {
        int returnValue = x % 11;
        return returnValue;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int result = calculate(x);
        System.out.println(result);
        in.close();
    }
}