package sudokuSolver.testDrivers;

import sudokuSolver.Move;

public class TestMove {

	public static void main(String[] args) {
		String testResults = String.format("%n%n╔══════════════╗%n"); // Stores the test results to be printed at the end.
		testResults += String.format("║ TEST RESULTS ║%n");
		testResults += String.format("╚══════════════╝%n");
		
		// Test 001
		Move testMove01[] = new Move[9];
		for(int i = 0; i < 9; i++){
			System.out.printf("Moving value %d to row 1, column %d%n", i + 1, i);
			testMove01[i] = new Move(1, i, i + 1);
			System.out.println(testMove01[i].toString());
		}
	}

}
