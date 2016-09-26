/**
 * 
 */
package sudokuSolver;

/**
 * Manages the entire board and those logics and features that span across multiple boxes.
 * 
 * @author WT-Rocco
 *
 */
public class Board {
	private Box[] boardBox = new Box[9];
	private Move[] moveQueue;
	boolean isActive;
	
	Board(){
		isActive = false; // Default: the game can't be played
	}
	Board(int[] layout){
		this();
		int theValue[] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; // Stores the values to be passed to Box
		int position = 0; // Stores a temporary position in the layout
		if(layout.length == 81){
			// I have an int for each cell.
			// I can assume that anything that is not between 1 and 9 is invalid
			for(int startRow = 1; startRow < 8; startRow += 3){
				for(int startColumn = 1; startColumn < 8; startColumn += 3){
					// I loop through each box
					for(int rowOffset = 0; rowOffset < 3; rowOffset++)
						for(int colOffset = 0; colOffset < 3; colOffset++){
							// I loop throught the box in the layout 
							position = ((startRow - 1 + rowOffset) * 9);
							position += (startColumn -1 + colOffset);
							// I check the value at the position in the layout and assign it to the array accortingly
							if((layout[position] > 0) && (layout[position] <= 9))
								theValue[(rowOffset * 3) + colOffset] = layout[position];
							else
								theValue[(rowOffset * 3) + colOffset] = 0;
						}
					// I create the box and assign it to the array
					Box theBox = new Box(this, startRow, startColumn, theValue); // To be implemented
					boardBox[(startRow / 3) * 3 + (startColumn / 3)] = theBox;
				}
			}
			isActive = true;
		}
	}

	// Move queue: push and poll. 
	private boolean pushMove(int row, int col, int value){
		Move theMove = new Move(row, col, value);
		if(theMove.getIsValid()){
			int theLength = moveQueue.length + 1;
			Move[] theQueue = new Move[theLength];
			for(int i = 0; i < theLength - 1; i++)
				theQueue[i] = moveQueue[i];
			theQueue[theLength - 1] = theMove;
			moveQueue = theQueue;
			return true;
		}
		else
			return false;
	}
	private Move pollMove(){
		int theLength = moveQueue.length;
		if(theLength == 0)
			return null;
		Move theMove = moveQueue[0];
		theLength -= 1;
		Move[] theQueue = new Move[theLength];
		if(theLength > 0)
			for(int i = 0; i < theLength; i++)
				theQueue[i] = moveQueue[i + 1];
		moveQueue = theQueue;
		return theMove;
	}
	
	/**
	 * Adds a move to the queue
	 * 
	 * @return true if done, false if not done
	 */
	public boolean queueMove(int row, int col, int value){
		if(pushMove(row, col, value))
			return true;
		else
			return false;
	}
	
}
