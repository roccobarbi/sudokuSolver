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
	private final boolean TESTING = false;
	private static final int MOVE_QUEUE_LENGTH = 1000;
	private Box[] boardBox = new Box[9];
	private Move moveQueue[] = new Move[MOVE_QUEUE_LENGTH];
	private int movesInQueue = 0; // Keeps track of the amount of moves in the queue
	boolean isActive;
	
	// Default creator
	Board(){
		isActive = false; // Default: the game can't be played
		movesInQueue = 0;
	}
	// Creator that takes an int array with all 81 cells in sequence, row-wise
	public Board(int[] layout){
		this();
		int theValue[] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0}; // Stores the values to be passed to Box
		int position = 0; // Stores a temporary position in the layout
		if(layout.length == 81){
			// I have an int for each cell.
			// I can assume that anything that is not between 1 and 9 is a void cell
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
		boolean hasBeenDone = false;
		if(movesInQueue < MOVE_QUEUE_LENGTH){
			Move theMove = new Move(row, col, value);
			if(theMove.getIsValid()){
				moveQueue[movesInQueue] = theMove;
				movesInQueue++;
				hasBeenDone = true;
			}
		}
		return hasBeenDone;
	}
	private Move pollMove(){
		Move theMove = null;
		if(movesInQueue > 0){
			theMove = moveQueue[0];
			if(movesInQueue > 1)
				for(int i = 0; i < movesInQueue; i++)
					moveQueue[i] = moveQueue[i + 1];
			movesInQueue--;
		}
		return theMove;
	}
	
	/**
	 * Responds to the request to add a move to the queue.
	 * 
	 * @return true if done, false if not done
	 */
	public boolean queueMove(int row, int col, int value){
		if(pushMove(row, col, value))
			return true;
		else
			return false;
	}
	
	/**
	 * Useful for test-driving the move queue.
	 * This method does something meaningful only during testing.
	 * 
	 * @return the number of moves in the queue, or -1 if there are any errors.
	 */
	public int printMoveQueue(){
		int howManyMoves = -1;
		if(TESTING){
			if(movesInQueue != 0){
				for(int i = 0; i < movesInQueue; i++){
					System.out.println(moveQueue[i].toString());
				}
			}
			else
				System.out.println("There are no moves in the queue");
			howManyMoves = movesInQueue;
		}
		return howManyMoves;
	}
}
