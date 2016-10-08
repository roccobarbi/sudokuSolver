package sudokuSolver.testDrivers;

import sudokuSolver.Box;
import sudokuSolver.Cell;

public class testCells {

	public static void main(String[] args) {
		String testResults = String.format("%n%n╔══════════════╗%n"); // Stores the test results to be printed at the end.
		testResults += String.format("║ TEST RESULTS ║%n");
		testResults += String.format("╚══════════════╝%n");
		
		Box utilityBox = new Box();
		
		// Test 001
		// Create 3 cells, one of which is filled.
		System.out.println("Test 001");
		boolean test001Passed = false;
		int test001Number = 7;
		Cell cell001 = new Cell(0, 0, utilityBox);
		Cell cell002 = new Cell(0, 1, utilityBox);
		Cell cell003 = new Cell(0, 2, utilityBox, test001Number);
		System.out.printf("cell001.getNumber() = %d%n", cell001.getNumber());
		System.out.printf("cell002.getNumber() = %d%n", cell002.getNumber());
		System.out.printf("cell003.getNumber() = %d%n", cell003.getNumber());
		System.out.printf("cell001.getIsActive() = %b%n", cell001.getIsActive());
		System.out.printf("cell002.getIsActive() = %b%n", cell002.getIsActive());
		System.out.printf("cell003.getIsActive() = %b%n", cell003.getIsActive());
		if(cell001.getNumber() == -1 && cell002.getNumber() == -1 && cell003.getNumber() == test001Number)
			if(cell001.getIsActive() && cell002.getIsActive() && cell003.getIsActive())
				test001Passed = true;
		testResults += "Test 001: " + (test001Passed ? "passed\n" : "failed\n");
		
		// Test 002
		// Move to the second cells, update available numbers on the first
		boolean test002Passed = false;
		int test002Number = 6;
		System.out.println("Test 002");
		System.out.println("cell002 moved successfully? -> " + cell002.move(test002Number));
		System.out.printf("cell001.getNumber() = %d%n", cell001.getNumber());
		System.out.printf("cell002.getNumber() = %d%n", cell002.getNumber());
		System.out.printf("cell003.getNumber() = %d%n", cell003.getNumber());
		System.out.printf("cell001.getAvailableNumbersLength() = %d%n", cell001.getAvailableNumbersLength());
		int cell01Removed = cell001.removeAvailable(test002Number);
		System.out.printf("cell01Removed = %d%n", cell01Removed);
		System.out.printf("cell001.getAvailableNumbersLength() = %d%n", cell001.getAvailableNumbersLength());
		if(cell001.getNumber() == -1 && cell002.getNumber() == test002Number && cell003.getNumber() == test001Number)
			if(cell01Removed == 8)
				test002Passed = true;
		testResults += "Test 002: " + (test002Passed ? "passed\n" : "failed\n");
		
		// Test 003
		// Checks the available numbers
		/*boolean test003Passed = false;
		int[] cell001Number = cell001.getAvailableNumbers();
		int[] cell002Number = cell002.getAvailableNumbers();
		int[] cell003Number = cell003.getAvailableNumbers();
		System.out.println(cell001.getAvailableNumbers());
		System.out.println(cell002.getAvailableNumbers());
		System.out.println(cell003.getAvailableNumbers());
		System.out.println(cell001Number.length == 8);
		System.out.println(cell002Number.length == 0);
		System.out.println(cell003Number.length == 0);
		if(cell001Number.length == 8 && cell002Number.length == 0 && cell003Number.length == 0)
			test003Passed = true;
		testResults += "Test 003: " + (test003Passed ? "passed\n" : "failed\n");*/
		
		// Print the test results
		System.out.println(testResults);
	}

}
