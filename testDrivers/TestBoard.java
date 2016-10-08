package sudokuSolver.testDrivers;

import sudokuSolver.Board;

public class TestBoard {

	public static void main(String[] args) {
		String testResults = String.format("%n%n╔══════════════╗%n"); // Stores the test results to be printed at the end.
		testResults += String.format("║ TEST RESULTS ║%n");
		testResults += String.format("╚══════════════╝%n");
		
		// Test 001
		int[] startValues = new int[81];
		for(int value : startValues)
			value = 0;
		Board testBoard01 = new Board(startValues);
		// First round of tests
		for(int i = 0; i < 9; i++){
			System.out.printf("Moving value %d to row 1, column %d%n", i + 1, i);
			testBoard01.queueMove(1, i, i + 1);
		}
		int testResult01 = testBoard01.printMoveQueue();
		if(testResult01 == 9)
			testResults += String.format("Test 001: passed.%n");
		else
			testResults += String.format("Test 001: failed.%n");
		
		// Print the test results
		System.out.println(testResults);
	}

}
