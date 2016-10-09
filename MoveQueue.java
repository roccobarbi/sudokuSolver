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
	/*
	 * Da migliorare:
	 * - forse mi serve un contatore del numero totale di mosse in coda;
	 * - devo aggiornare il contatore delle giocate/da giocare.
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
		}
		return theMove;
	}
	/*
	 * Da migliorare:
	 * - forse mi serve un contatore del numero totale di mosse in coda;
	 * - devo aggiornare il contatore delle mosse giocate/da giocare;
	 * - devo verificare se la mossa è valida e se la precedente è stata giocata,
	 *   prima di aggiornare nextMove.
	 */
	public Move pop(){
		Move theMove = null;
		if(spareRows < length){
			theMove = queue[length - spareRows - 1];
			spareRows++;
			nextFree--;
			nextMove--;
		}
		return theMove;
	}
	/*
	 * Da migliorare:
	 * - mi servono dei setter per incrementare i vari contatori;
	 * - per incrementare nextMove devo verificare se la successiva è valida.
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
