// RODOLFO JOHAM CARVAJAL MARQUEZ

import java.util.Random;

public class GameBoard {

	int rows, columns;
	int win = 0; // CONTROL VARIBALE FOR HITS
	int numShip = 0; // VARIBALE WITH THE NUMBER OF PLACES WHERE THE SHIP WILL BE PLACE
	int[] randomRow, randomColumns; // VARIABLE FOR RANDOM NUMBER TO PLACE THE SHIP
	int[][] shipBoard; // VARIABLE WHERE THE SHIP WILL BE PLACED
	int[][] board; // VARIBALE TO DRAW THE BOARD
	boolean[][] shipBoardTable; // BOOLEAN ARRAY WHERE VALUES WILL BE TRUE, AT THE SAME COORDINATES AS SHIPBOARD
								// VARIBALE
	String hit = ""; // VARIABLE WHICH STORAGE A MESSAGE TO LET THE PLAYER KNOWS IF THERE IS A HIT OR
						// MISS

	// CONSTRUCTOR WHICH IS CALLED BY THE DRIVER WITH TWO ENTRIES BY THE USER
	public GameBoard(int rows, int columns) {

		this.rows = rows;
		this.columns = columns;
		this.board = new int[rows][columns];
		randomGenerator(rows, columns);
		toString();

	}

	// OVERRIDE FOR METHOD toString TO CREATE A BOARD THAT CAN BE PRINTED
	// WITH A SYSTEM.OUT.PRINT(GAMEBOARD-OBJECT);
	@Override
	public String toString() {

		String toPrint = ""; // VARIBALE TO STORAGE THE STRINGS TO CONSTRUCT THE BOARD
		String space = " ";
		int num = 0;

		toPrint = "   "; // BLANK SPACE TO START THE BOARD
		// FOR LOOP TO CREATE TH FIRST LINE WITH REFERENCE OF THE ROWS AND COLUMNS
		for (int i = 0; i < columns; i++) {
			num = i + 1;
			if (i < 9) { // IF THE NUMBER ARE LESS THAN 9, THERE IS A EXTRA SPACE. (1 2 3 4)
				toPrint += num + space;
			} else { // FROM 10 UNTIL 20, THE NUMBER ARE TOGETHER, TO FIT THE BOARD. (10111213)
				toPrint += num;
			}

		}
		toPrint += "\n"; // SPACE TO START THE BOARD

		for (int i = 0; i < rows; i++) {
			int line = i + 1;
			if (i < 9) { // FOR NUMBER LESS THAN 9, THERE IS A EXTRA SPACE TO FIT THE BOARD BY COLUMN (1
							// |)
				toPrint += line + " |";
			} else {
				toPrint += line + "|"; // FROM 10 UNTIL 20, THERE IS NOT SPACE TO FIT THE BOARD. (10|)
			}
			for (int j = 0; j < columns; j++) {
				toPrint = toPrint + missOrgood(board[i][j], shipBoardTable[i][j]) + "|";// DETAILS IN THE METHOD
			}

			toPrint += "\n";
		}
		toPrint += hit; // "HIT" IS A VARIABLE THAT CONTAINS A TEXT IF THE SHIP HAS BEEN HIT OR MISSED

		return toPrint; // IT RETURNS ALL THE STRINGS HAVE BEEN ADDED TO THE VARIABLE

	}

	// THIS IS A METHOD THAT IS CALLED FROM THE GAME CLASS TO CHECK IF THE SHIP HAS
	// BEEN HIT OR MISSED
	public void matchValidation(int rows, int columns) {

		// THE IF STATEMENT CHECK IF THE COORDINATE IN VARIBALE BOARD HAS A VALUE OF 1
		// IF THE COORDINATE HAS A VALUE OF 1 AND THE VARIABLE SHIPBOARDTABLE IS TRUE
		// IT MEANS THE USER HAS HIT THE BOARD
		if (board[rows][columns] == 1 && shipBoardTable[rows][columns] == true) {
			hit = "\nBoom!! Well done, it is a hit!!\n";
			win++;// CONTROL VARIBALE TO COMPARE WITH THE NUMBER OF SLOTS THAT THE SHIP HAS
			if (win == numShip - 1 && Game.players.length > 1) { // CONDITION FOR THE LAST HIT LEFT
				// MESSAGE TO LET PLAYERS KNOW THAT THE LAST HIT IS WITH EXTRA POINT BONUS
				hit += "\nNow, there is just one hit left, 1 point plus bonus! Good luck!!!\n";
			}
			// IF THE BOOLEAN ARRAY SHIPBOARDTABLE IS FALSE, IT MEANS THE SHIP IS NOT THERE
			// AND IT IS A MISS
		} else if (board[rows][columns] == 1 && shipBoardTable[rows][columns] == false) {
			hit = "\nMissed!! Good try, best look next time!\n";
		}

	}

	// METHOD TO CHANGE THE VALUE OF ZEROS OF THE INTEGER ARRAY BOARD
	public String missOrgood(int i, boolean ship) {

		// IF THE PLACE IS 0, THE PLAYER DOES NOT PICK THE POSITION YET
		// IF THE PLACE IS 3, THE BOARD IS PLACE THERE, BUT IT HAS TO BE HIDDEN TO THE
		// PLAYER(S)
		if (i == 0 || i == 3) {
			return "_"; //
		} else if (i == 1 && ship == true) {
			return "H"; // THE COORDENATE HAS HIT
		} else if (i == 1 && ship == false) {
			return "M"; // THE COORDENATE HAS MISSED
		} else {
			return null;
		}
		// RETURN TO THE METHOD OVERRIDE "toString" A STRING TO SHOW ON THE BOARD
	}

	public void randomGenerator(int rows, int columns) {

		double sizeShip = columns / 3; // FORMULA TO GET THE NUMBER OF PLACES FOR THE SHIP
		numShip = (int) sizeShip; // VARIBLE WITH THE RESULT OF THE FOMULA IN INTEGER

		randomRow = new int[numShip];
		randomColumns = new int[numShip];

		Random forRow = new Random();
		this.randomRow[0] = forRow.nextInt(rows); // TO GET A RANDOM NUMBER FOR ROW
		// IF STAMENT TO PREVENT A CRASH OF ARRAYS
		if (randomRow[0] == 0) {
			randomRow[0] = 1;
		}

		Random forColumns = new Random(); // TO GET A RANDOM NUMBER FOR COLUMNS
		this.randomColumns[0] = forColumns.nextInt(columns);
		// IF STAMENT TO PREVENT A CRASH OF ARRAYS
		// THE USER TYPE A COORDINATE FROM 1 UP TO 20, BUT THE ARRAYS ARE SET FROM 0 TO 19
		// IF THE NUMBER IS 0, IT WILL BE A CRASH WHEN i-1 AND i=0
		if (randomColumns[0] == 0) {
			randomColumns[0] = 1;
		}

		shipBoardTable = new boolean[rows][columns];

		// INICIATION OF THE VARIBLE WITH ALL FALSE VALUES
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				shipBoardTable[i][j] = false;
			}
		}

		// BASED IN THE RAMDON[0], THE BOARD WILL BE PLACE
		// FOR NUMBER EVEN, THE BOARD IS GONNA BE PLACE ON THE COLUMN[0] TO DOWN
		if (randomRow[0] % 2 == 0) {
			// THIS CONDITION PREVENT TO PLACE THE SHIP OUT OF THE BORDER OF THE BOARD
			if (randomColumns[0] < columns - numShip) {
				for (int i = 1; i < numShip; i++) {
					randomColumns[i] = randomColumns[0] + i; // INCREASE BY ONE THE POSITION ON COLUMN RANDOM AND
																// STORAGE THE NUMBER
				}
				for (int j = 0; j < numShip; j++) {
					board[randomRow[0] - 1][randomColumns[j] - 1] = 3; // IT PLACE THE SHIP ON THE BOARD VARIABLE
					System.out.println("The board is in : " + (randomRow[0]) + ", " + (randomColumns[j]));
				}
				// IF THE BOARD DOES NOT FIT TO DOWN, IT PLACE THE SHIP COLUMN[0] TO UP
			} else if ((randomColumns[0] >= columns - numShip)) {
				for (int i = 1; i < numShip; i++) {
					randomColumns[i] = randomColumns[0] - i; // DECREASSE BY ONE THE POSITION ON COLUMN RANDOM AND
																// STORAGE THE NUMBER
				}
				for (int j = 0; j < numShip; j++) {
					board[randomRow[0] - 1][randomColumns[j] - 1] = 3; // IT PLACE THE SHIP ON THE BOARD VARIABLE
					System.out.println("The board is in : " + (randomRow[0]) + ", " + (randomColumns[j]));
				}
			}
			// FOR NUMBER ODD, THE BOARD IS GONNA BE PLACE ON THE ROWS[0] TO DOWN
		} else if (randomRow[0] % 2 != 0) {
			// THIS CONDITION PREVENT TO PLACE THE SHIP OUT OF THE BORDER OF THE BOARD
			if (randomRow[0] < rows - numShip) {
				for (int i = 1; i < numShip; i++) {
					randomRow[i] = randomRow[0] + i; // // INCREASE BY ONE THE POSITION ON ROW RANDOM AND STORAGE THE
														// NUMBER
				}
				for (int j = 0; j < numShip; j++) {
					board[randomRow[j] - 1][randomColumns[0] - 1] = 3; // IT PLACE THE SHIP ON THE BOARD VARIABLE
					System.out.println("The board is in : " + (randomRow[j]) + ", " + (randomColumns[0]));
				}
				// IF THE BOARD DOES NOT FIT TO DOWN, IT PLACE THE SHIP ROW[0] TO UP
			} else if ((randomRow[0] >= rows - numShip)) {
				for (int i = 1; i < numShip; i++) {
					randomRow[i] = randomRow[0] - i; // DECREASSE BY ONE THE POSITION ON ROW RANDOM AND STORAGE THE
														// NUMBER
				}
				for (int j = 0; j < numShip; j++) {
					board[randomRow[j] - 1][randomColumns[0] - 1] = 3; // IT PLACE THE SHIP ON THE BOARD VARIABLE
					System.out.println("The board is in : " + (randomRow[j]) + ", " + (randomColumns[0]));
				}
			}

		}
		// FOR LOOP TO PLACE THE SHIP ON THE BOOLEAN ARRAY FROM THE BOARD ARRAY
		// THE VALUE WILL BE TRUE AT THE SAME POSITION AS THE INTEGER ARRAY BOARD
		// VARIABLE
		// THE BOOLEAN IS USES TO COMPARE DATA FOR HIT AND MISS
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if (board[i][j] == 0) {
					shipBoardTable[i][j] = false;
				} else {
					shipBoardTable[i][j] = true;
					//System.out.println(shipBoardTable[i][j]);
				}
			}
		}

	}

	// CONTROL METHOD, IT IS USES IN GAME CLASS TO VALIDATE
	// IF THE SHIP HAS BEEN TOTALLY HIT OR NOT
	// RETURN A BOOLEAN
	public Boolean endGame() {

		if (win == numShip) {
			return true; // END OF THE GAME
		} else
			return false; // NOT YET
	}

}
