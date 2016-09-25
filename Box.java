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
}
