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
	private Box[] boardBox;
	private Move[] moveQueue;
	boolean isActive;
	
	Board(){
		isActive = false; // Default: the game can't be played
		/*
		 * I have to implement a creator that receives the right value and passes them on
		 * to the subsequent creators. I will probably need to modify the creators in order
		 * to receive the values and pass them along.
		 */
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
