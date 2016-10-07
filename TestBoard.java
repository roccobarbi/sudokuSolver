package sudokuSolver;

public class TestBoard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
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
		if(testResult01 == 8)
			System.out.println("First test passed.");
	}

}
