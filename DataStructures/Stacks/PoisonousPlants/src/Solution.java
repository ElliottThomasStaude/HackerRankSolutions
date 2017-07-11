/*
Transcript of problem:

There are N plants in a garden. Each of these plants has been added with some amount of pesticide. After each day, if any plant has more pesticide than the plant at its left, being weaker than the left one, it dies. You are given the initial values of the pesticide in each plant. Print the number of days after which no plant dies, i.e. the time after which there are no plants with more pesticide content than the plant to their left.

-- Input Format

The input consists of an integer N. The next line consists of N integers describing the array P where P[i] denotes the amount of pesticide in plant i.

-- Constraints

1 <= N <= 100000
0 <= P[i] <= 10^9

-- Output Format

Output a single value equal to the number of days after which no plants die.
*/

import java.io.*;
import java.util.*;

public class Solution {

	/*
	Throw inputs on a stack. In sequence, check the plants' pesticide values, and whenever a pesticide value that would result
	in that plant dying occurs, discard it. After going to the bottom of the stack, if any plants died, increment the count of
	days, and then re-add all valid values to the original stack. Repeat until no more dying plants are identified.
	*/
	
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        long numberOfPlants = inputScanner.nextLong();
        
        ArrayDeque<Long> plantRow = new ArrayDeque<Long>();
        for (long plantIndex = 0L; plantIndex < numberOfPlants; plantIndex++) {
        	long currentPesticideValue = inputScanner.nextLong();
        	plantRow.push(currentPesticideValue);
        }
        inputScanner.close();
        
        ArrayDeque<Long> plantBuffer = new ArrayDeque<Long>();
        long plantDays = 0;
        boolean plantsStabilized = false;
        while (!plantsStabilized) {
        	plantsStabilized = true;
        	long lastPlantPesticideValue = -1;
        	while (plantRow.size() != 0) {
        		long currentPlantPesticideValue = plantRow.pop();
        		if (currentPlantPesticideValue < lastPlantPesticideValue) {
        			if (plantsStabilized) {
        				plantsStabilized = false;
        			}
        		} else {
        			if (lastPlantPesticideValue >= 0) {
        				plantBuffer.add(lastPlantPesticideValue);
        			}
        		}
        		lastPlantPesticideValue = currentPlantPesticideValue;
        		if (plantRow.size() == 0) {
        			plantBuffer.add(currentPlantPesticideValue);
        		}
        	}
        	if (!plantsStabilized) {
        		plantDays++;
        		plantRow = plantBuffer;
        		plantBuffer = new ArrayDeque<Long>();
        	}
        }
        
        System.out.println(plantDays);
    }
}
