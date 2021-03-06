/*
Transcript of problem:

You are given a grid with both sides equal to N. Rows and columns are numbered from 0 to N-1. There is a castle on the intersection of the ath row and the bth column.

Your task is to calculate the minimum number of steps it would take to move the castle from its initial position to the goal position (c, d).

It is guaranteed that it is possible to reach the goal position from the initial position.

Note: You can move the castle from cell (a, b) to any (x, y) in a single step if there is a straight horizontal line or a straight vertical line between (a, b) and (x, y) that does not contain any forbidden cell. Here, "X" denotes a forbidden cell.

--Input Format

The first line contains an integer N, the size of the grid.

The following N lines contains a string of length N that consists of one of the following characters: "X" or ".". Here, "X" denotes a forbidden cell, and "." denotes an allowed cell. 

The last line contains a, b, denoting the initial position of the castle, and c, d, denoting the goal position. Here, a, b, c, and d are space separated.

-- Constraints

1 <= N <= 100
0 <= a,b,c,d < N

-- Output Format

Output a single line: the integer denoting the minimum number of steps required to move the castle to the goal position.
*/

import java.io.*;
import java.util.*;

public class Solution {
	public static int currentColumn;
	public static int currentRow;
	public static int currentLocation;
	public static int gridSize;
	public static HashSet<Integer> solutionSet;
	// Note: a "deque" is technically not called for here, but the author finds them easier to work with for these purposes than the standard queue implementations of Java
	public static ArrayDeque<Integer> solutionQueue;
	
	public static boolean[][] gridArray;
	
	public static int getGridLocation(int gridRow, int gridColumn) {
		int gridLocation = Solution.gridSize * gridRow + gridColumn;
		return gridLocation;
	}
	
	public static int getGridColumn(int gridLocation) {
		int gridColumn = gridLocation % Solution.gridSize;
		return gridColumn;
	}
	
	public static int getGridRow(int gridLocation) {
		int gridRow = Math.floorDiv(gridLocation, Solution.gridSize);
		return gridRow;
	}
	
	public static boolean getGridContent(int gridRow, int gridColumn) {
		return Solution.gridArray[gridRow][gridColumn];
	}
	
	public static void changeGridValuesToLocation(int gridLocation) {
		Solution.currentLocation = gridLocation;
		Solution.currentRow = Solution.getGridRow(gridLocation);
		Solution.currentColumn = Solution.getGridColumn(gridLocation);
		return;
	}
	
	public static void changeGridValuesToRowColumn(int gridRow, int gridColumn) {
		Solution.currentRow = gridRow;
		Solution.currentColumn = gridColumn;
		Solution.currentLocation = Solution.getGridLocation(gridRow, gridColumn);
		return;
	}
	
	public static boolean checkPresenceOfValidLocation(int rowLocation) {
		boolean isValid = true;
		if (!Solution.solutionSet.contains(rowLocation)) {
			Solution.solutionSet.add(rowLocation);
			Solution.solutionQueue.add(rowLocation);
		} else {
			isValid = false;
		}
		return isValid;
	}
	
    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        
        Solution.solutionSet =  new HashSet<Integer>();
        Solution.solutionQueue = new ArrayDeque<Integer>();
        Solution.gridSize = inputScanner.nextInt();
        
        Solution.gridArray = new boolean[Solution.gridSize][Solution.gridSize];
        
        for (int gridRowIndex = 0; gridRowIndex < Solution.gridSize; gridRowIndex++) {
        	String gridString = inputScanner.next();
        	String[] gridLine = gridString.split("");
        	for (int gridColumnIndex = 0; gridColumnIndex < Solution.gridSize; gridColumnIndex++) {
        		if (gridLine[gridColumnIndex].equals(".")) {
        			gridArray[gridRowIndex][gridColumnIndex] = true;
        		} else {
        			gridArray[gridRowIndex][gridColumnIndex] = false;
        		}
        	}
        }
        int aInput = inputScanner.nextInt();
        int bInput = inputScanner.nextInt();
        int cInput = inputScanner.nextInt();
        int dInput = inputScanner.nextInt();
        
        Solution.currentRow = aInput;
        Solution.currentColumn = bInput;
        Solution.currentLocation = Solution.getGridLocation(Solution.currentRow, Solution.currentColumn);
        int targetLocation = Solution.getGridLocation(cInput, dInput);
        int pathLength = 0;
        
        // Assuming that the start and end locations are different, do pathfinding; otherwise no steps are required for correct traversal
        if (Solution.currentLocation != targetLocation) {
	        // Set up path searching framework
	        Solution.checkPresenceOfValidLocation(Solution.currentLocation);
	        // A value of -1 is used to indicate the end of the current set of points which can be reached in X or fewer steps
	        Solution.solutionQueue.add(-1);
	        
	        boolean shortestPathFound = false;
	        while (!shortestPathFound) {
	        
	        	// Search based on next location in queue
	        	int nextLocation = Solution.solutionQueue.poll();
	        	if (nextLocation == -1) {
	        		// This marks the end of the points which can be reached within the number of steps taken thus far
	        		pathLength++;
	        		Solution.solutionQueue.add(-1);
	        	} else {
		        	// Check for any candidate locations in the 4 cardinal directions from the current location
		        	// Update current values to reflect newest location
	        		Solution.changeGridValuesToLocation(nextLocation);
	        		// "North" checks
	        		int row = Solution.currentRow;
	        		boolean openPath = true;
		        	while (openPath) {
		        		if (row >= 0 && Solution.getGridContent(row, Solution.currentColumn)) {
		        			int rowLocation = Solution.getGridLocation(row, Solution.currentColumn);
		        			Solution.checkPresenceOfValidLocation(rowLocation);
		        		} else {
		        			openPath = false;
		        		}
		        		row--;
		        	}
		        	// "South" checks
		        	row = Solution.currentRow;
		        	openPath = true;
		        	while (openPath) {
		        		if (row < Solution.gridSize && Solution.getGridContent(row, Solution.currentColumn)) {
		        			int rowLocation = Solution.getGridLocation(row, Solution.currentColumn);
		        			Solution.checkPresenceOfValidLocation(rowLocation);
		        		} else {
		        			openPath = false;
		        		}
		        		row++;
		        	}
		        	// "West" checks
		        	int column = Solution.currentColumn;
		        	openPath = true;
		        	while (openPath) {
		        		if (column >= 0 && Solution.getGridContent(Solution.currentRow, column)) {
		        			int columnLocation = Solution.getGridLocation(Solution.currentRow, column);
		        			Solution.checkPresenceOfValidLocation(columnLocation);
		        		} else {
		        			openPath = false;
		        		}
		        		column--;
		        	}
		        	// "East" checks
		        	column = Solution.currentColumn;
		        	openPath = true;
		        	while (openPath) {
		        		if (column < Solution.gridSize && Solution.getGridContent(Solution.currentRow, column)) {
		        			int columnLocation = Solution.getGridLocation(Solution.currentRow, column);
		        			Solution.checkPresenceOfValidLocation(columnLocation);
		        		} else {
		        			openPath = false;
		        		}
		        		column++;
		        	}
		        	if (Solution.solutionSet.contains(targetLocation)) {
		        		// A path has been found from start to target; the number of steps taken in this journey is the only desired metric
		        		shortestPathFound = true;
		        		pathLength++;
		        	}
	        	}
	        	
	        }
	        
        }
        System.out.println(pathLength);
        inputScanner.close();
    }
}