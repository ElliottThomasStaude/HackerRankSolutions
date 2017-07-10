/*
Transcript of problem:

There are N buildings in a certain two-dimensional landscape. Each building has a height given by h_i, i IN [1, N]. If you join K adjacent buildings, they will form a solid rectangle of area K x min(h_i, h_i+1, ..., h_i+k-1).

Given N buildings, find the greatest such solid area formed by consecutive buildings.

-- Input Format

The first line contains N, the number of buildings altogether.
The second line contains N space-separated integers, each representing the height of a building.

-- Constraints

1 <= N <= 10^5
1 <= h_i <= 10^6

-- Output Format

One integer representing the maximum area of rectangle formed.
*/

import java.io.*;
import java.util.*;

public class Solution {

    /*
    Keep track of the greatest building heights in a stack, as one moves down the line of buildings. Start with a "fake" building of
    height 0, and each time a new building is examined do one of two things. If the previous value on the stack is less than the 
    building's height, push that building's height onto a stack, and then put the index of the building's position in line into a
    map. If the previous value on the stack is greater than the building's height, then pop elements from the stack until the top
    element on the stack is less than or equal to the building's height; each time the "prior height" value is removed from the 
    stack, find the map value with that prior height as a key - the mapped value is the earliest starting point for a rectangle with
    the key as its height. Multiply that height by the difference between the mapped value and the currently examined building 
    index to find the rectangle area, and replace the current maximum area if appropriate.
    */

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        long numberOfInputs = inputScanner.nextLong();

        long maxArea = numberOfInputs;
        TreeMap<Long, Long> contiguousBuildingsEarliestIndex = new TreeMap<Long, Long>();
        Stack<Long> buildingStack = new Stack<Long>();
        
        long firstBuildingSize = 0L;
        contiguousBuildingsEarliestIndex.put(firstBuildingSize, 0L);
        buildingStack.push(firstBuildingSize);

        // The index here is specifically started at 1, despite the fact that the first building has already been counted, because it allows the difference between this index and the indices stored in contiguousBuildingsEarliestIndex to be used to find the longest run of buildings permitting rectangles of certain heights to form
        for (long buildingIndex = 1L; buildingIndex <= numberOfInputs; buildingIndex++) {

            long buildingSize = inputScanner.nextLong();
            long lastSize = buildingStack.peek();
            
            if (buildingSize > lastSize) {
                buildingStack.push(buildingSize);
                contiguousBuildingsEarliestIndex.put(buildingSize, buildingIndex);
            } else if (buildingSize < lastSize) {
                long earliestPosition = buildingIndex;
                while (buildingSize < lastSize) {
                    long rectangleHeight = buildingStack.pop();
                    if (earliestPosition > contiguousBuildingsEarliestIndex.get(rectangleHeight)) {
                        earliestPosition = contiguousBuildingsEarliestIndex.get(rectangleHeight);
                    }
                    long lengthOfRectangle = buildingIndex - earliestPosition;
                    long candidateMaxArea = rectangleHeight * lengthOfRectangle;
                    if (maxArea < candidateMaxArea) {
                        maxArea = candidateMaxArea;
                    }
                    contiguousBuildingsEarliestIndex.remove(rectangleHeight);
                    lastSize = buildingStack.peek();
                }
                if (buildingSize > lastSize) {
                    contiguousBuildingsEarliestIndex.put(buildingSize, earliestPosition);
                    buildingStack.push(buildingSize);
                }
            }
        }
        // One last "building" of size 0 at the end, to ensure the end-of-buildings calculations are done correctly
        long buildingSize = 0L;
        long lastSize = buildingStack.peek();

        while (buildingSize < lastSize) {

            long rectangleHeight = buildingStack.pop();
            long lengthOfRectangle = numberOfInputs + 1 - contiguousBuildingsEarliestIndex.get(rectangleHeight);
            long candidateMaxArea = rectangleHeight * lengthOfRectangle;
            if (maxArea < candidateMaxArea) {
                maxArea = candidateMaxArea;
            }
            contiguousBuildingsEarliestIndex.remove(rectangleHeight);
            lastSize = buildingStack.peek();
        }
        
        System.out.println(maxArea);
        inputScanner.close();
    }
}