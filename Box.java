/**
 * 
 */
package sudokuSolver;

/**
 * Manages a single 3x3 box in the sudoku board.
 * Calls the cell methods when the Board object tells it to do so
 * 
 * @author WT-Rocco
 *
 */
public class Box {
	private int[] availableNumbers; // Numbers not yet used in the box.
	private int startColumn; // Base to be summed to its first column from the left
	private int startRow; // Base to be summed to its first row from the top
	private Cell[] boxCells; // Cells in the box, to be created by the constructor
	private int freeSpace; // Number of cells still to be filled
	private Board father; // Board to which the box belongs
	boolean isActive;
	
	// Accessors
	public int[] getAvailableNumbers(){
		return availableNumbers;
	}
	public int getStartColumn(){
		return startColumn;
	}
	public int getStartRow(){
		return startRow;
	}
	public int getFreeSpace(){
		return freeSpace;
	}
	public Board getFather(){
		return father;
	}
	
	// Constructors
	public Box(){
		isActive = false; // Can't be created empty
	}
	public Box(Board theFather, int rowOne, int colOne, int[] cellValues){
		if((cellValues.length != 9) || (rowOne < 1) || (rowOne > 7) || (colOne < 1) || (colOne > 7))
			isActive = false;
		else{
			father = theFather;
			startRow = rowOne;
			startColumn = colOne;
			boxCells = new Cell[9];
			for(int i = 0; i < cellValues.length; i++){
				// procedo per riga e colonna
				boxCells[i] = new Cell(rowOne + (i / 3), colOne + (i % 3), cellValues[i], this);
			}
		}
	}
	
	/*
	 * Functionality
	 * The Box needs to perform the following operations:
	 * - make a move and update its available numbers (upon request);
	 * - parse its cells against the available numbers (sending a move request to the board if needed);
	 * - return the value of a single cell;
	 * - update the available numbers for a single cell.
	 */
	/**
	 * @return the number that has been moved to the cell, 0 if there was an error
	 */
	private void updateCellNumbers(int number){
		for(int i = 0; i < 9; i++)
			if(boxCells[i].removeAvailable(number) == 1)
				boxCells[i].move(boxCells[i].getAvailableNumbers()[0]); // Bad, I need some internal private Box.move
	}
	public int cellMove(int row, int col, int value){
		if((row > 0 && row <= 9) && (col > 0 && col <= 9) && (value > 0 && value <= 9)){
			int cellIndex = ((row - startRow) * 3) + ((col - startColumn));
			if(boxCells[cellIndex].move(value)){
				updateCellNumbers(value);
				return value;
			}
			else
				return 0;
		}
		else
			return 0;
	}
}
