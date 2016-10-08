/**
 * 
 */
package sudokuSolver;

/**
 * Manages single cells in the sudoku board.
 * 
 * @author WT-Rocco
 *
 */
public class Cell {
	private final boolean DEBUG = true;
	private int row; // 0 to 9
	private int column; // 0 to 9
	private int[] availableNumbers = {}; // 0 to 9
	private int number;
	private Box father; // Box object that owns it
	private boolean isActive; // Check to disallow default-constructed cells.
	private boolean isFilled;
	
	// Getters
	// There are no setters because fields can only be set by constructors or functionality
	public int getRow(int y){
		return row;
	}
	public int getCol(int x){
		return column;
	}
	// Returns an array of available numbers
	public int[] getAvailableNumbers(){
		return availableNumbers;
	}
	// Returns an array of available numbers
		public int getAvailableNumbersLength(){
			return availableNumbers.length;
		}
	// Returns the number, or -1 if not filled
	public int getNumber(){
		int result;
		if(isFilled)
			result = number;
		else
			result = -1;
		return result;
	}
	public boolean getIsFilled(){
		return isFilled;
	}
	public boolean getIsActive(){
		return isActive;
	}
	public Box getFather(){
		return father;
	}
	
	// Constructors: the cell is inactive by default.
	// Inactive cells must NOT respond to commands.
	Cell(){
		row = 0;
		column = 0;
		availableNumbers = new int[0];
		father = null;
		isActive = false;
	}
	public Cell(int x, int y, Box fatherBox){
		this(); // inactive by default, unless the rest of the parameters are good
		if(DEBUG) System.out.println("Creating new cell with values:");
		if(DEBUG) System.out.println("\trow = " + y);
		if(DEBUG) System.out.println("\tcol = " + x);
		if((x >= 0 && x < 9) && (y >= 0 && y < 9)){
			row = y;
			column = x;
			father = fatherBox;
			if(father != null)
				isActive = true;
			availableNumbers = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
			isFilled = false;
		}
	}
	public Cell(int x, int y, Box fatherBox, int filledWith){
		this(); // inactive by default, unless the rest of the parameters are good
		if(DEBUG) System.out.println("Creating new cell with values:");
		if(DEBUG) System.out.println("\trow = " + y);
		if(DEBUG) System.out.println("\tcol = " + x);
		if(DEBUG) System.out.println("\tval = " + filledWith);
		if(DEBUG) System.out.println("(x > 0 && x <= 9) = " + (x > 0 && x <= 9));
		if(DEBUG) System.out.println("(y > 0 && y <= 9) = " + (y > 0 && y <= 9));
		if(DEBUG) System.out.println("(filledWith > 0 && filledWith <= 9) = " + (filledWith > 0 && filledWith <= 9));
		if((x >= 0 && x < 9) && (y >= 0 && y < 9) && (filledWith > 0 && filledWith <= 9)){
			if(DEBUG) System.out.println("filledWith is valid.");
			row = y;
			column = x;
			father = fatherBox;
			if(father != null)
				isActive = true;
			if(filledWith > 0 && filledWith <= 9){
				availableNumbers = new int[0];
				isFilled = true;
			}
			else {
				availableNumbers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
				isFilled = false;
			}
			number = filledWith;
		}
	}
	
	/**
	 * Reduces the amount of numbers that are available for a move and returns their length.
	 * Can be uses in expressions like:
	 * if(cellX.removeAvailable(y) == 1)
	 *     cellX.move(cellX.getAvailableNumbers()[0]);
	 *     
	 * @return the new length of the array, 0 if the cell is not active.
	 */
	public int removeAvailable(int num){
		if(DEBUG) System.out.println("Trying to remove number: " + num);
		if(isActive){
			if(DEBUG) System.out.println("The cell is active.");
			boolean numFound = false;
			for(int i = 0; i < (availableNumbers.length - 1); i++){
				if(DEBUG) System.out.printf("At position %d found number: %d%n", i, availableNumbers[i]);
				if(availableNumbers[i] == num){
					numFound = true;
					if(DEBUG) System.out.printf("%d has been found at position %d%n", num, i);
				}
				if(availableNumbers[i] >= num){
					if(DEBUG) System.out.printf("Moving %d from %d to %d%n", availableNumbers[i + 1], (i + 1), i);
					availableNumbers[i] = availableNumbers[i + 1];
				}
			}
			if(numFound){
				if(DEBUG) System.out.println("The number has been found, reducing the array.");
				int[] newNumbers = new int[availableNumbers.length - 1];
				if(DEBUG) System.out.println("availableNumbers had length: " + availableNumbers.length);
				if(DEBUG) System.out.println("newNumbers has length: " + newNumbers.length);
				for(int i = 0; i < (newNumbers.length); i++){
					newNumbers[i] = availableNumbers[i];
					if(DEBUG) System.out.printf("%d has been moved from availableNumbers to newNumbers at position %d%n", newNumbers[i], i);
				}
				if(DEBUG) System.out.println("newNumbers has length: " + newNumbers.length);
				availableNumbers = newNumbers;
				if(DEBUG) System.out.println("availableNumbers = newNumbers has length: " + availableNumbers.length);
			}
			return availableNumbers.length;
		}
		else
			return 0;
	}
	
	/**
	 * Writes a number to a cell and returns its isFilled status.
	 * @return true if the move was completed correctly.
	 */
	public boolean move(int value){
		if(isActive && value > 0 && value <= 9){
			number = value;
			isFilled = true;
		}
		return isFilled;
	}
}
