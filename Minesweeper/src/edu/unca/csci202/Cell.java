package edu.unca.csci202;

public class Cell {

	
	private boolean isMine;
	private String visibleValue;
	private boolean checked = false;
	private int value;
	
	/**
	 * Creates a visible value, and sets isMine to false for each cell.
	 */
	public Cell() {
		visibleValue = " - ";
		isMine=false;
	}
	/**
	 * Takes in a value and sets it as the new value for the cell.
	 * @param value
	 */
	public void changeValue(int value) {
		this.value = value;
	}
	/**
	 * 
	 * @return the value of the cell(not the visible one);
	 */
	public int getValue() {
		return this.value;
	}
	 /**
	  * Grabs value and makes it visible for the user.
	  * @param value
	  */
	public void setValue(String value) {
		
		this.visibleValue = value;
	}
	 /**
	  * Checks if the cell contains a main.
	  * @return isMine 
	  */
	public boolean checkMine() {
		return isMine;
	}
	/**
	 * Makes the cell contain a mine. 
	 */
	public void changeToMine() {
		this.isMine = true;
	}
	/**
	 * Checks if the cell was already checked.
	 * @return checked
	 */
	public boolean getChecked() {
		return checked;
	}
	/**
	 * Marks the cell as already checked.
	 */
	public void alreadyChecked() {
		this.checked = true;
	}
	 
	public String toString() {
		return visibleValue;
	}
	
	
}
