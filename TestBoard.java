package sudokuSolver;

public class TestBoard {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] startValues = new int[81];
		for(int value : startValues)
			value = 0;
		Board testBoard01 = new Board(startValues);
		System.out.println("Moving 1 to 1,1");
		testBoard01.queueMove(1, 1, 1);
		System.out.println("Moving 2 to 1,2");
		testBoard01.queueMove(1, 2, 2);
		System.out.println("Moving 3 to 1,3");
		testBoard01.queueMove(1, 3, 3);
		System.out.println("Moving 4 to 1,4");
		testBoard01.queueMove(1, 4, 4);
		System.out.println("Moving 5 to 1,5");
		testBoard01.queueMove(1, 5, 5);
		System.out.println("Moving 6 to 1,6");
		testBoard01.queueMove(1, 6, 6);
		System.out.println("Moving 7 to 1,7");
		testBoard01.queueMove(1, 7, 7);
		System.out.println("Moving 8 to 1,8");
		testBoard01.queueMove(1, 8, 8);
		if(testBoard01.printMoveQueue() == 8)
			System.out.println("First test passed.");
	}

}
