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
	private int row; // 0 to 9
	private int column; // 0 to 9
	private int[] availableNumbers; // 0 to 9
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
	public int[] getAvailableNumbers(){
		return availableNumbers;
	}
	public int getNumber(){
		return number;
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
		availableNumbers = null;
		father = null;
		isActive = false;
	}
	Cell(int x, int y, Box fatherBox){
		this(); // inactive by default, unless the rest of the parameters are good
		if((x >= 0 && x <= 9) && (y >= 0 && y <= 9)){
			row = y;
			column = x;
			father = fatherBox;
			if(father != null)
				isActive = true;
			availableNumbers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
			isFilled = false;
		}
	}
	Cell(int x, int y, int filledWith, Box fatherBox){
		this(); // inactive by default, unless the rest of the parameters are good
		if((x >= 0 && x <= 9) && (y >= 0 && y <= 9) && (filledWith >= 0 && filledWith <= 9)){
			row = y;
			column = x;
			father = fatherBox;
			if(father != null)
				isActive = true;
			availableNumbers = null;
			number = filledWith;
			isFilled = true;
		}
	}
	
	/**
	 * Reduces the amount of numbers that are available for a move and returns their length.
	 * Can be uses in expressions like:
	 * if(cellX.removeAvailable(y) == 1)
	 *     cellX.move(cellX.getAvailableNumbers[0]);
	 */
	public int removeAvailable(int num){
		if(isActive){
			boolean numFound = false;
			for(int i = 0; i < (availableNumbers.length - 1); i ++){
				if(availableNumbers[i] == num)
					numFound = true;
				if(availableNumbers[i] >= num)
					availableNumbers[i] = availableNumbers[i + 1];
			}
			if(numFound){
				int[] newNumbers = new int[availableNumbers.length - 1];
				for(int i = 0; i < (availableNumbers.length - 1); i ++)
					newNumbers[i] = availableNumbers[i];
				availableNumbers = newNumbers;
			}
			return availableNumbers.length;
		}
		else
			return 0;
	}
	
	/**
	 * Writes a number to a cell and returns its isFilled status.
	 * @return
	 */
	public boolean move(int value){
		if(isActive && value >= 0 && value <= 9){
			number = value;
			isFilled = true;
		}
		return isFilled;
	}
}
