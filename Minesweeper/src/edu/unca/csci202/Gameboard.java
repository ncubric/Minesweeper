package edu.unca.csci202;



import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Gameboard {

	private static final int SIZE = 8;
	
	private Cell[][] gameboard = new Cell[SIZE][SIZE];
	private static int MAX_MINES = 10;
	private int score = 0;
	Random rand = new Random();
	Scanner scan = new Scanner(System.in);
	private String peek = "If you would like to see where the mines are type in 88";
	private int guessedRow ;
	private int guessedColumn ;
	private int checkMine;
	private String guessRow = "Please pick a row(from 1 to 8):";
	private String guessColumn = "Please pick a column(from 1 to 8):";
	private Stack<Cell> Zero = new Stack<Cell>();
	
	
	
	/**
	 * Reveals the position of the mines.
	 */
	private void peek() {
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				if(gameboard[i][j].checkMine()== false) {
					System.out.print(gameboard[i][j]);
				}else {
					//System.out.print(" M ");
					gameboard[i][j].setValue(" M ");
					System.out.print(gameboard[i][j]);
				}
				
			}
			
			System.out.println();
		}
	}
	
	
	
	
	/**
	 * Calculates the value for a particular cell, by checking surrounding cells, and sets the value for the cell.
	 * @param  row1 takes in the row of a cell
	 * @param  column1 takes in the column of a cell
	 */
	public void checkAround(int row1, int column1) {
		
		int row = row1;
		int column = column1;
		
		int value = 0;
		
		for(int i=column-1; i<column+2;i++) {
			if(i>=0 && i<=SIZE) {
				if(row == 0) {
					
				}else {
					if(i<SIZE) {
						if(gameboard[row-1][i].checkMine() == true) {
							value++;
						}
					}
				}
			}
		}
		if(column>0) {
			if(gameboard[row][column-1].checkMine()== true) {
				value++;
			}
		}
		if(column<SIZE-1) {
			if(gameboard[row][column+1].checkMine() == true) {
				value++;
			}
		}
		for(int i=column-1; i<column+2;i++) {
			if(i>=0 && i<SIZE) {
				if(row <SIZE-1) {
					if(gameboard[row+1][i].checkMine() == true) {
						value++;
					}
				}
			}
		}
		    gameboard[row][column].changeValue(value);
			//String newValue = Integer.toString(value);
			//gameboard[guessedrow][guessedcolumn].setValue(" "+newValue+" ");
	}
	/**
	 * Reveals the value of the cell the user picked, and if it's zero it expands.
	 * @param  guessedrow Takes in the user's guessed row
	 * @param guessedcolumn Takes in the user's guessed column
	 */
	public void guess(int guessedrow,int guessedcolumn) {
		int row = guessedrow - 1; 
		int column = guessedcolumn - 1;
		if(gameboard[row][column].getValue() == 0) {
			Zero.push(gameboard[row][column]);
			gameboard[row][column].alreadyChecked();
			expand(row,column);
		}else {
		 String newValue = Integer.toString(gameboard[row][column].getValue());
		 gameboard[row][column].setValue(" "+newValue+" ");
		}
	}
	/**
	 * Called if user guessed a zero value cell to check if there are any zeros around so it can expand.
	 * @param row Takes in the row of the value zero cell
	 * @param column Takes in the column of the value zero cell
	 */
	public void expand(int row,int column) {
		if(row>0) {
			if(column>0) {
				if(gameboard[row-1][column-1].getValue() == 0 && gameboard[row-1][column-1].getChecked() == false) {
					Zero.push(gameboard[row-1][column-1]);
					gameboard[row-1][column-1].alreadyChecked();
					expand(row-1,column-1);
				}else {
					 String newValue = Integer.toString(gameboard[row-1][column-1].getValue());
					 gameboard[row-1][column-1].setValue(" "+newValue+" ");
					}
			}
			if(gameboard[row-1][column].getValue() == 0 && gameboard[row-1][column].getChecked() == false) {
				Zero.push(gameboard[row-1][column]);
				gameboard[row-1][column].alreadyChecked();
				expand(row-1,column);
			}else {
				 String newValue = Integer.toString(gameboard[row-1][column].getValue());
				 gameboard[row-1][column].setValue(" "+newValue+" ");
				}
		}
		if(column>0) {
			if(gameboard[row][column-1].getValue() == 0 && gameboard[row][column-1].getChecked() == false) {
				Zero.push(gameboard[row][column-1]);
				gameboard[row][column-1].alreadyChecked();
				expand(row,column-1);
			}else {
				 String newValue = Integer.toString(gameboard[row][column-1].getValue());
				 gameboard[row][column-1].setValue(" "+newValue+" ");
				}
			if(row<SIZE-1) {
				if(gameboard[row+1][column-1].getValue() == 0 && gameboard[row+1][column-1].getChecked() == false) {
					Zero.push(gameboard[row+1][column-1]);
					gameboard[row+1][column-1].alreadyChecked();
					expand(row+1,column-1);
				}else {
					 String newValue = Integer.toString(gameboard[row+1][column-1].getValue());
					 gameboard[row+1][column-1].setValue(" "+newValue+" ");
					}
				if(gameboard[row+1][column].getValue() == 0 && gameboard[row+1][column].getChecked() == false) {
					Zero.push(gameboard[row+1][column]);
					gameboard[row+1][column].alreadyChecked();
					expand(row+1,column);
				}else {
					 String newValue = Integer.toString(gameboard[row+1][column].getValue());
					 gameboard[row+1][column].setValue(" "+newValue+" ");
					}
			}
		}
			if(column<SIZE-1) {
				if(row>0) {
					if(gameboard[row-1][column+1].getValue() == 0 && gameboard[row-1][column+1].getChecked() == false) {
						Zero.push(gameboard[row-1][column+1]);
						gameboard[row-1][column+1].alreadyChecked();
						expand(row-1,column+1);
					}else {
						 String newValue = Integer.toString(gameboard[row-1][column+1].getValue());
						 gameboard[row-1][column+1].setValue(" "+newValue+" ");
						}
				}
				if(gameboard[row][column+1].getValue() == 0 && gameboard[row][column+1].getChecked() == false) {
					Zero.push(gameboard[row][column+1]);
					gameboard[row][column+1].alreadyChecked();
					expand(row,column+1);
				}else {
					 String newValue = Integer.toString(gameboard[row][column+1].getValue());
					 gameboard[row][column+1].setValue(" "+newValue+" ");
					}
				if(row<SIZE-1) {
					if(gameboard[row+1][column+1].getValue() == 0 && gameboard[row+1][column+1].getChecked() == false) {
						Zero.push(gameboard[row+1][column+1]);
						gameboard[row+1][column+1].alreadyChecked();
					}else {
						 String newValue = Integer.toString(gameboard[row+1][column+1].getValue());
						 gameboard[row+1][column+1].setValue(" "+newValue+" ");
						}
				}
			}	
	}
	/**
	 * Prints the gameboard.
	 */
	public void printBoard() {
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
			
				System.out.print(gameboard[i][j]);
			
		}
			System.out.println();
		}
	}
	/**
	 * Asks if the user wants to play again.
	 */
	public void playAgain() {
		System.out.println("Would you like to play again?(enter 1 if yes and 2 for no)");
		int num = scan.nextInt();
		while(num<1||num>2) {
			System.out.println("Please pick 1 for yes and 2 for no");
			 num= scan.nextInt();
		}
		if(num == 1 ) {
			Run();
		}else if(num == 2 ) {
			System.out.println("Thank you for playing!");
			while(true) {
				
			}
		}
	}
	/**
	 * Goes through the gameboard and calculates the value for each cell.
	 */
	public void calculateValues() {
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				if(gameboard[i][j].checkMine() == false) {
				checkAround(i,j);
				}
			}
		}
	}
	
	/**
	 * Runs the game, and calls all the functions in needed order. 
	 */
	public void  Run() {
		for(int i=0;i<SIZE;i++) {
			for(int j=0;j<SIZE;j++) {
				gameboard[i][j]= new Cell();
			}
		}
		int numberOfMines =0;
		int row;
		int column;
		while(numberOfMines< MAX_MINES) {
			row = rand.nextInt(SIZE);
			column = rand.nextInt(SIZE);
				if(gameboard[row][column].checkMine() == false) {
				gameboard[row][column].changeToMine();
				numberOfMines++;
				}
		} 
		
		calculateValues();
		System.out.println("Welcome to Minesweeper!");
		printBoard();
		
		System.out.println();
		System.out.println(peek);
		System.out.println(guessRow);
		
		guessedRow = scan.nextInt();
		
		
		if(guessedRow == 88) {
			peek();
			//playAgain();
		}
	
		while(guessedRow >SIZE || guessedRow <1){
			
			
			System.out.println("Please pick a row from 1 to 8!");
			guessedRow = scan.nextInt();
		}
		
		
		while(guessedRow != 88) {
			System.out.println(guessColumn);
			guessedColumn = scan.nextInt();
			while(guessedColumn>SIZE) {
				System.out.println("Please pick a column from 1 to 8!");
				guessedColumn = scan.nextInt();
			}
				if(guessedColumn == 88) {
					peek();
					//playAgain();
				}
		
			System.out.println("You picked row: "+ guessedRow + " and column: "+ guessedColumn);
			System.out.println("Do you think there is a mine here?(1 for yes and 2 for no)");
			checkMine = scan.nextInt();
			if(checkMine == 88) {
				peek();
				//playAgain();
				
			}
			while(checkMine<0||checkMine>2) {
				System.out.println("Not an option please pick 1 for yes and 2 for no!");
				checkMine = scan.nextInt();
			}
			if(checkMine == 1) {
				if(gameboard[guessedRow - 1][guessedColumn - 1].checkMine() == false) {
					System.out.println("Sorry you lose");
					peek();
					playAgain();
				}else {
					gameboard[guessedRow - 1][guessedColumn - 1].setValue(" M ");
					score++;
					if(score==MAX_MINES) {
						System.out.println("YOU WIN!");
						playAgain();
					}
				}
			}
			if(checkMine == 2 ) {
				if(gameboard[guessedRow - 1][guessedColumn - 1].checkMine() == true) {
					System.out.println("Sorry you lose");
					peek();
					playAgain();
				}else {
					guess(guessedRow, guessedColumn);
					//expands all adjacent 0's
					while(!Zero.isEmpty()) {
						Cell temp = Zero.pop();
						String newValue = Integer.toString(temp.getValue());
						temp.setValue(" "+newValue+" ");
					}
				}
			}
			
			printBoard();
			System.out.println(guessRow);
			guessedRow = scan.nextInt();
			while(guessedRow >SIZE || guessedRow < 1) {
				if(guessedRow == 88) {
					peek();
					//playAgain();
				}
				
				System.out.println("Please pick a row from 1 ot 8!");
				guessedRow = scan.nextInt();
			}
			
		}
		
		
	}
}
