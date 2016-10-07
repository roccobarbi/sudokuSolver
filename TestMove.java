package sudokuSolver;

public class TestMove {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Move testMove01[] = new Move[9];
		// First round of tests
		for(int i = 0; i < 9; i++){
			System.out.printf("Moving value %d to row 1, column %d%n", i + 1, i);
			testMove01[i] = new Move(1, i, i + 1);
			System.out.println(testMove01[i].toString());
		}
	}

}
