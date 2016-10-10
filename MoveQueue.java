package sudokuSolver;

public class MoveQueue {
	private Move[] queue;
	private int length, spareRows;
	private int validPlayed, validToPlay, invalidCount;
	private int nextMove, nextFree;
	private int garbageTolerance; // Spare moves when garbage collection is initiated
	
	// Constructors
	MoveQueue(){
		nextMove = 0;
		nextFree  = 0;
		validPlayed = 0;
		validToPlay = 0;
		invalidCount = 0;
		garbageTolerance = 0;
		length = 100; // Default
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
	
	// Utility methods (private)
	/**
	 * 
	 * @return the index of the first valid and non-played move in the queue, or -1 if not found
	 */
	private int findFirstToMove(){
		int totalLogged = validPlayed + validToPlay + invalidCount;
		int startingPoint = 0;
		int index = -1, i = 0;
		boolean keepLooking = true;
		// Set the starting point to account for garbage collection and logged counts.
		if(totalLogged != (length - spareRows))
			startingPoint = validPlayed;
		else
			startingPoint = validPlayed - (totalLogged - length - spareRows);
		startingPoint = (startingPoint >= 0 ? startingPoint : 0);
		// Loop and look
		while(keepLooking){
			if(!queue[i].getIsPlayed()){
				index = i;
				keepLooking = false;
			}
			if(++i >= length)
				keepLooking = false;
		}
		return index;
	}
	
	// Functionality (add and query moves)
	/*
	 * Da migliorare:
	 * - devo implementare i controlli di garbage collection.
	 */
	public boolean push(int theRow, int theColumn, int theValue){
		boolean hasBeenDone = false;
		if(spareRows > 0){
			Move theMove = new Move(theRow, theColumn, theValue);
			if(theMove.getIsValid()){
				queue[nextFree] = theMove;
				nextFree++;
				hasBeenDone = true;
			}
		}
		return hasBeenDone;
	}
	/**
	 * Extracts the first move from the queue and returns it,
	 * moving the queue's contents left by one and freeing an
	 * additional slot at the end of the queue.
	 * 
	 * @return the first move in the queue
	 */
	/*
	 * Da migliorare:
	 * - forse mi serve un contatore del numero totale di mosse in coda;
	 */
	public Move poll(){
		Move theMove = null;
		if(spareRows < length){
			theMove = queue[0];
			if(spareRows < length - 1)
				for(int i = 0; i < length - spareRows; i++)
					queue[i] = queue[i + 1];
			spareRows++;
			nextFree--;
			nextMove--;
			if(!theMove.getIsPlayed()){
				validPlayed++;
				validPlayed--;
			}
		}
		return theMove;
	}
	/*
	 * Da migliorare:
	 * - forse mi serve un contatore del numero totale di mosse in coda;
	 * - devo verificare se la mossa è valida e se la precedente è stata giocata,
	 *   prima di aggiornare nextMove.
	 */
	public Move pop(){
		Move theMove = null;
		if(spareRows < length){
			theMove = queue[length - spareRows - 1];
			spareRows++;
			nextFree--;
			nextMove = findFirstToMove();
		}
		if(!theMove.getIsPlayed()){
			validPlayed++;
			validPlayed--;
		}
		return theMove;
	}
	/*
	 * Da migliorare:
	 * - mi servono dei setter per incrementare i vari contatori;
	 * - per incrementare nextMove devo verificare se la successiva � valida.
	 */
	public Move next(){
		Move theMove = null;
		if(nextMove < length - spareRows){
			theMove = queue[nextMove];
			nextMove++;
			validPlayed++;
			validToPlay--;
		}
		return theMove;
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
