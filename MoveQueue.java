package sudokuSolver;

public class MoveQueue {
	Move[] queue;
	int length, spareRows;
	int nextMove, validPlayed, validToPlay, invalidCount;
	int garbageTolerance; // Spare moves when garbage collection is initiated
	
	// Constructors
	MoveQueue(){
		nextMove = 0;
		validPlayed = 0;
		validToPlay = 0;
		invalidCount = 0;
		garbageTolerance = 0;
	}
	public MoveQueue(int theLength){
		this();
		queue = new Move[length];
		setLength(theLength);
		spareRows = length;
		setGarbageTolerance(); // Default setter used
	}
	public MoveQueue(int theLength, int gCTolerance){
		this(theLength);
		setGarbageTolerance(gCTolerance); 
	}
	
	// Getters and setters
	private void setLength(int theLength){
		length = (theLength >= 81 ? theLength : 81); // Minimum: the 81 cells in a board
	}
	private int getLength(){
		return length;
	}
	private void setGarbageTolerance(){ // Default
		garbageTolerance = length / 10; // Default: initiate when only a 10th of the space is free
	}
	private void setGarbageTolerance(int gCTolerance){
		garbageTolerance = (gCTolerance < length / 2 ? gCTolerance : length / 2); // Max: intiate at half capacity
	}
	private int getGarbageTolerance(){
		return garbageTolerance;
	}
	
	// Functionality (add and query moves)
	public void push(int theRow, int theColumn, int theValue){
		//
	}
	public Move pool(){
		//
		return null;
	}
	public Move pop(){
		//
		return null;
	}
	public Move next(){
		//
		return null;
	}
	
	// Display
	public String toString(){
		//
		return null;
	}
	public String toCSV(){
		//
		return null;
	}
	
	// Internal functionality
	private void garbageCollect(){
		//
	}
}
