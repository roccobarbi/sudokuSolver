package sudokuSolver;

public class Move {
	private final boolean DEBUG = false;
	private int column;
	private int row;
	private int value;
	private long id;
	private boolean isValid;
	private static long nextId;
	
	// Private setters (checks the validity of the values)
	private boolean setColumn(int newCol){
		if((newCol >= 0) && (newCol < 9)){
			column = newCol;
			return true;
		}
		else
			return false;
	}
	private boolean setRow(int newRow){
		if((newRow >= 0) && (newRow < 9)){
			row = newRow;
			return true;
		}
		else
			return false;
	}
	private boolean setValue(int newValue){
		if(DEBUG) System.out.println("setValue - newValue = " + newValue);
		if((newValue > 0) && (newValue <= 9)){
			if(DEBUG) System.out.println("newValue has been written to row " + row);
			value = newValue;
			return true;
		}
		else
			return false;
	}
	// Public getters
	public int getColumn(){
		return column;
	}
	public int getRow(){
		return row;
	}
	public int getValue(){
		return value;
	}
	public long getId(){
		return id;
	}
	public boolean getIsValid(){
		return isValid;
	}
	
	// Default constructor, sets the id
	Move(){
		id = nextId++;
		isValid = false;
	}
	// Correct constructor
	Move(int theRow, int theColumn, int theValue){
		this();
		if(setRow(theRow) && setColumn(theColumn) && setValue(theValue))
			isValid = true;
	}
	
	public String toString(){
		String output = "# " + this.getId();
		output += " - write " + this.getValue() + " to row " + this.getRow() + " and column " + this.getColumn();
		output += " - the move ";
		output += isValid ? "is" : "is not";
		output += " valid";
		return output;
	}
}
