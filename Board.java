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
	
	private void pushMove(int row, int col, int value){
		Move theMove = new Move(row, col, value);
		if(theMove.getIsValid()){
			int theLength = moveQueue.length + 1;
			Move[] theQueue = new Move[theLength];
			for(int i = 0; i < theLength - 1; i++)
				theQueue[i] = moveQueue[i];
			theQueue[theLength - 1] = theMove;
			moveQueue = theQueue;
		}
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
}
