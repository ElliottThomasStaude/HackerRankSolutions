/*
Transcript of problem:

Suppose there is a circle. There are N petrol pumps on that circle. Petrol pumps are numbered 0 to (N - 1) (both inclusive). You have two pieces of information corresponding to each of the petrol pumps: (1) the amount of petrol that particular petrol pump will give, and (2) the distance from that petrol pump to the next petrol pump.

Initially, you have a tank of infinite capacity carrying no petrol. You can start the tour at any of the petrol pumps. Calculate the first point from where the truck will be able to complete the circle. Consider that the truck will stop at each of the petrol pumps. The truck will move one kilometer for each litre of petrol.

-- Input Format

The first line will contain the value of N.
The next N lines will contain a pair of integers each, i.e. the amount of petrol that petrol pump will give and the distance between that petrol pump and the next petrol pump.

-- Constraints

1 <= N <= 10^5
1 <= amount of petrol, distance <= 10^9

-- Output Format

An integer which will be the smallest index of the petrol pump from which we can start the tour.
*/

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        long numberOfPumps = inputScanner.nextLong();
        
        long startingPump = 0;
        long endingPump = 0;
        long petrolQuantity = 0;
        long distanceQuantity = 0;
        
        long[] pumpDistanceArray = new long[(int)numberOfPumps];
        long[] pumpPetrolArray = new long[(int)numberOfPumps];
        
        for (long pumpIndex = 0; pumpIndex < numberOfPumps; pumpIndex++) {
        	long pumpPetrol = inputScanner.nextInt();
        	pumpPetrolArray[(int)pumpIndex] = pumpPetrol;
        	long pumpDistance = inputScanner.nextInt();
        	pumpDistanceArray[(int)pumpIndex] = pumpDistance;
        	
        	// Perform checks while assembling arrays to save time
        	if (petrolQuantity >= distanceQuantity) {
    			petrolQuantity += pumpPetrolArray[(int)endingPump];
    			distanceQuantity += pumpDistanceArray[(int)endingPump];
    			endingPump++;
    		} else {
    			petrolQuantity -= pumpPetrolArray[(int)startingPump];
    			distanceQuantity -= pumpDistanceArray[(int)startingPump];
    			startingPump++;
    		}
        }
        
        boolean pumpNotFound = true;
        
        while (pumpNotFound) {
    		if (petrolQuantity >= distanceQuantity) {
    			petrolQuantity += pumpPetrolArray[(int)endingPump];
    			distanceQuantity += pumpDistanceArray[(int)endingPump];
    			endingPump++;
    			endingPump = endingPump % numberOfPumps;
    		} else {
    			petrolQuantity -= pumpPetrolArray[(int)startingPump];
    			distanceQuantity -= pumpDistanceArray[(int)startingPump];
    			startingPump++;
    			startingPump = startingPump % numberOfPumps;
    		}
    		if (endingPump == startingPump - 1 || (startingPump == 0 && endingPump == numberOfPumps - 1)) {
        		pumpNotFound = false;
        	}
        }
        System.out.println(startingPump);
        inputScanner.close();
    }
}