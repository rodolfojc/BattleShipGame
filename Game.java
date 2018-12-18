//RODOLFO JOHAM CARVAJAL MARQUEZ

import java.util.Scanner;

public class Game {

	Scanner keyboard = new Scanner(System.in); // OBJECT TO GET USER INPUT
	static Player[] players; // PLAYERS
	GameBoard myBoard; // THE BOARD
	int rows, columns; // ROWS AND COLUMNS

	// DEFAULT CONSTRUCTOR
	public Game() {

		welcomeMessage(); // METHOD WELCOME MESSAGE
		numberPlayers(); //  METHOD TO GET NUMBER OF PLAYERS AND THE DETAILS
		getSize(); // METHOD TO PICK A CO-ORDINATE TO PLAY
		theGameDriver(); // METHOD THAT CONTROL/DRIVE THE GAME 

	}

	public void welcomeMessage() {
		System.out.println("                  Welcome to ");

		// I HAVE USED A TEXT GENERATOR TO GET THIS MESSAGE
		// http://patorjk.com/software/taag/
		System.out.println("    ___       _   _   _        __ _     _       ");
		System.out.println("   / __\\ __ _| |_| |_| | ___  / _| |__ (_)_ __  ");
		System.out.println("  /__\\/// _` | __| __| |/ _ \\ \\ \\| '_ \\| | '_ \\ ");
		System.out.println(" / \\/  | (_| | |_| |_| |  __/ _\\ | | | | | |_) |");
		System.out.println(" \\_____/\\__,_|\\__|\\__|_|\\___| \\__|_| |_|_| .__/ ");
		System.out.println("                                         |_|    ");
		System.out.println("");
	}

	// METHOD TO ASK THE USER HOW MANY PLAYERS WILL BE CREATED
	public void numberPlayers() {

		int numplayer = 0;
		boolean numFlag = false;

		do {
			System.out.println("How many players would you like to create?");
			try {
				numplayer = Integer.parseInt(keyboard.nextLine());
				numFlag = numPlayersValidation(numplayer); // DETAILS ARE IN THE METHOD
			} catch (Exception e) {
				System.out.println("Invalid entry, just NUMBERS are allow, please try again!\n");
			}
		} while (numFlag == false);

		players = new Player[numplayer]; // CREATE OBJECT OF THE CLAS PLAYER ACCORDING WITH NUMBER OF PLAYERS

		for (int i = 0; i < numplayer; i++) {
			players[i] = playersCreator(i + 1); // DETAILS IN THE METHOD, IT IS +1 BECAUSE AT LEAST 1 PLAYER WILL BE
												// CREATED
		}

		for (int i = 0; i < numplayer; i++) {
			System.out.println(players[i]); // SHOWS THE DETAILS OF THE PLAYERS
		}
	}

	public Boolean numPlayersValidation(int players) {

		// PLAYER ARE FROM 4 UP TO 4
		if (players >= 1 && players <= 4) {
			return true;
		} else {
			System.out.println("The numbers of players are from 1 up to 4 players, please try again!\n");
			return false;
		}
	}

	// METHOD WHICH ASK THE USER THE DETAILS OF THE PLAYER(S)
	// IT USES DO WHILE LOOP UNTIL THE ENTRY IS VALID, AND TRY AND CATCH FOR INVALID ENTRIES
	// THE VALIDATION ARE OTHER METHODS THAT RETURN VALUES OF TRUE OF FALSE
	public Player playersCreator(int numPlayer) {

		String name = "";
		String email = "";
		int age = 0;

		boolean nameFlag = false;
		// TO GET THE FULL NAME
		do {
			System.out.println("What is the FULL NAME of the Player " + numPlayer + "?");
			try {
				name = keyboard.nextLine();
				nameFlag = nameValidation(name); // DETAILS ARE IN THE METHOD
			} catch (Exception e) {
				System.out.println(
						"The name is not valid, numbers or special characters are not allow, please try again!\n");
			}
		} while (nameFlag == false);

		boolean emailFlag = false;
		// TO GET THE EMAIL
		do {
			System.out.println("What is your email " + name + "?");
			try {
				email = keyboard.nextLine();
				emailFlag = emailValidation(email); // DETAILS ARE IN THE METHOD
			} catch (Exception e) {
				System.out.println(
						"The email is not valid, it must have the format " + "example@example.com, please try again!\n");
			}
		} while (emailFlag == false);

		boolean ageFlag = false;

		// TO GET THE AGE
		do {
			System.out.println("Great!!\nLast question! " + name + ", what is your age?");
			try {
				age = Integer.parseInt(keyboard.nextLine());
				ageFlag = ageValidation(age); // DETAILS ARE IN THE METHOD
			} catch (Exception e) {
				System.out.println("The age must be just numbers, please try again!\n");
			}
		} while (ageFlag == false);

		return new Player(name, age, email, 0, 0, 0); // IT RETURN A PLAYER WITH DETAILS

	}

	public Boolean emailValidation(String email) {

		// A VALID EMAIL MUST HAVE AT LEAST A "@" AND A "."
		if (email.contains("@") && email.contains(".")) {
			return true;
		} else {
			System.out.println("The email is not valid. The valid format is example@example.com, please try again!\n");
			return false;
		}
	}

	public Boolean nameValidation(String name) {

		// THE REGEX VALIDAD NAMES OR SURNAMES FROM Aa-Zz AND ALSO LETTERS WITH SPECIAL
		// CHARACTERS AS ασϊι AND OTHER
		// IT VALIDADES ALSO AND SPACE AT LEAST SO PREVENT AT LEAST TWO WORDS HAVE TO BE
		// THERE
		if (name.matches("^[\\p{L} .'-]+$") && name.contains(" ")) {
			return true;
		} else {
			System.out.println(
					"The name is not valid. The valid format is \"NAME\" follow by  \"SURNAME\", please try again!\n");
			return false;
		}
	}

	public Boolean ageValidation(int age) {

		// THE VALID AGE FROM 12 TO 100 YEARS OLD
		if (age >= 12 && age <= 100) {
			return true;
		} else {
			System.out.println("The age is no valid. The player must be between 12 and 100 years old, please try again!\n");
			return false;
		}
	}

	// METHOD WHICH ASK THE USER THE SIZE OF THE BOARD
	// IT VALIDATES JUST ENTRIES BETWEEN 10 AND 20 ROWS OR COLUMNS
	// AND CREATE AN OBJECT OF THE CLASS GAMEBOARD WITH THE VALID FORMAT
	public void getSize() {

		rows = 0;
		columns = 0;

		boolean rowsFlag = false;
		boolean columnsFlag = false;

		System.out.println();
		System.out.println("Please, enter the grid size");
		do {
			System.out.println("How many rows would you like?");
			try {
				rows = Integer.parseInt(keyboard.nextLine());
				rowsFlag = rowsAndColumnsValidation(rows); // DETAILS ARE ON THE METHOD
			} catch (Exception e) {
				System.out.println("Invalid entry, just NUMBERS are allow, please try again\n");
			}
		} while (rowsFlag == false);

		do {
			System.out.println("How many columns would you like?");
			try {
				columns = Integer.parseInt(keyboard.nextLine());
				columnsFlag = rowsAndColumnsValidation(columns);
			} catch (Exception e) {
				System.out.println("Invalid entry, just NUMBERS are allow, please try again\n");
			}
		} while (columnsFlag == false);

		myBoard = new GameBoard(rows, columns); // THE OBJECT OF GAMEBOARD CLASS
		System.out.println(myBoard); // IT SHOWS THE BOARD BY CALLING THE OVERRIDE toString METHOD
	}

	public Boolean rowsAndColumnsValidation(int rowsOrColumns) {

		// VALIDATE THE MINIMUN AND MAX OF ROWS AND COLUMNS
		// 10 x 10 UP TO 20 X 20, SQUARE AND RECTANGULAR AS WELL
		if (rowsOrColumns >= 10 && rowsOrColumns <= 20) {
			return true;
		} else {
			System.out.println("The minimum size is 10 x 10 , up to 20 x 20 (Rows x Columns)," + " please try again\n");
			return false;
		}
	}

	// METHOD WHICH GET THE POSITION OR COORDINATE TO BE PLAY
	// IT VALIDATES THAT THE POSITION IS ACCORDING TO THE SIZE OF THE BOARD
	// IT VALIDATES THAT THE POSITION IS NOT ALREADY PICKED
	// IT SHOWS THE BOARD WITH A MISS OR HIT

	public void getPosition() {

		int posRow = 0;
		int posCol = 0;

		boolean posRowFlag = false;
		do {

			System.out.print("Please, enter a coordinate in Row: ");
			try {
				posRow = Integer.parseInt(keyboard.nextLine());
				posRowFlag = getPosValidationRow(posRow); // DETAILS ARE IN THE METHOD
			} catch (Exception e) {
				System.out.println("Invalid entry, just numbers allow, please try again!\n");
			}
			;
		} while (posRowFlag == false);

		boolean posColumnFlag = false;
		do {
			System.out.print("Please, enter a coordinate in Column: ");
			try {
				posCol = Integer.parseInt(keyboard.nextLine()); // DETAILS ARE IN THE METHOD
				posColumnFlag = getPosValidationColumn(posCol);
			} catch (Exception e) {
				System.out.println("Invalid entry, just numbers allow, please try again!\n");
			}
			;
		} while (posColumnFlag == false);

		// IF THE COORDINATE VALUE IS 1, THE COORDINATE HAVE BEEN PICKED BEFORE
		if (myBoard.board[posRow - 1][posCol - 1] != 1) { // VALIDATES IF THE COORDINATE IS NOT ALREADY PICKED

			myBoard.board[posRow - 1][posCol - 1] = 1;
			myBoard.matchValidation(posRow - 1, posCol - 1);
			System.out.println(myBoard);
		} else {
			System.out.println("The coordinate has been already picked, please try again\n");
			getPosition();
		}
	}

	public Boolean getPosValidationRow(int posRows) {

		// IT VALIDATES THAT THE ENTRY IS ACCORDING WITH THE SIZE OF THE BOARD IN ROW
		if (posRows - 1 <= rows) {
			return true;
		} else {
			System.out.println("The entry is not valid, the grid has " + this.rows + " rows, please try again!\n");
			return false;
		}

	}

	public Boolean getPosValidationColumn(int posColumn) {

		// IT VALIDATES THAT THE ENTRY IS ACCORDING WITH THE SIZE OF THE BOARD IN COLUMN
		if (posColumn - 1 <= columns) {
			return true;
		} else {
			System.out.println("The entry is not valid, the grid has " + this.columns + " columns, please try again!\n");
			return false;
		}
	}

	////////////////////////////////////////////////////////////
	// THIS IS THE MAIN METHOD THAT CONTROL AND DRIVE THE GAME//
	////////////////////////////////////////////////////////////
	public void theGameDriver() {

		// TURNS
		do {
			for (int i = 0; i < players.length; i++) { // FOR LOOP TO TURN THE PLAYER TO PLAY
				if (myBoard.endGame() == false) { // VALIDATION TO CHECK IF THE SHIP STILL ALIVE
					System.out.println("Player " + players[i].getName() + ", it's time to play!\n");
					getPosition();
					if (myBoard.hit.contains("Boom!!")) { // VALIDATION OF HIT ACCORDING WITH THE VARIBLE HIT IN
															// GAMEBOARD CLASS
						if (myBoard.win == myBoard.numShip && players.length > 1) { // BONUS FOR LAST POSITION LEFT
							players[i].hit += 2;
						} else {
							players[i].hit += 1;
						}
					} else if (myBoard.hit.contains("Missed")) { // VALIDATION OF HIT ACCORDING WITH THE VARIBLE HIT IN
																	// GAMEBOARD CLASS
						players[i].miss += 1;
					}
				}
			}
		} while (myBoard.endGame() == false); // VALIDATION TO CHECK IF THE SHIP STILL ALIVE

		System.out.println("!!!The ship has been destroyed!!!\n");

		// FOR LOOP TO SHOW THE HIT AND MISS OF EACH PLAYER
		for (int i = 0; i < players.length; i++) {
			System.out.println("Player " + players[i].getName() + " has hit " + players[i].hit + " , and has missed "
					+ players[i].miss);
		}

		System.out.println();
		// FOR LOOP TO CALCULARE THE SCORE=HIT-(MISS*2) OF EACH PLAYER AND SHOW THEM
		for (int i = 0; i < players.length; i++) {
			players[i].score = players[i].hit - (players[i].miss * 2); // FORMULA
			System.out.println("Player " + players[i].getName() + " final score is " + players[i].score + " points");
		}

		// VALIABLE THAT WILL STORAGE THE NUMBER OF THE WINNER
		// IT START FROM -800 TO PREVENT A CRASH IF A PLAYER MISS A BOARD 20 X 20 (LIMIT
		// WITHOUT SHIP)
		int winner = -800;
		int winNum = 0;

		int[] draw = new int[4]; // VALIABLE TO STORE THE NUMBER OF THE WINNER
		int flag = 0; // FLAG TO CHECK IF THERE IS A DRAW, AND AS A CONTROL VALIABLE TO GET THE NUMBER
						// OF DRAWS

		// IT VALIDATES IF THERE ARE MORE THAN JUST ONE PLAYER
		if (players.length > 1) {

			// FOR LOOP TO GET THE NUMBER OF THE WINNER ACCORDING WITH THE SCORE
			for (int i = 0; i < players.length; i++) {
				if (players[i].score > winner) {
					winner = players[i].score;
					winNum = i;
				}
			}
			// FOR LOOP TO CHECK IF A DRAW HAVE BEEN PLACED
			for (int i = 0; i < players.length; i++) {
				// IT VALIDATES IF THE SCORE OF THE WINNER IS EQUAL TO ANY OTHER PLAYER
				// IT VALIDATES ALSO THAT IS NOT GOING TO CHECK THE SAME WINNER AS A PLAYER
				if (players[winNum].score == players[i].score && winNum != i) {
					draw[flag] = i; // STORRE THE NUMBER OR THE PLAYER IN THE NUMBER OF FLAG OR POSIBLES DRAWS
					flag++; // FLAG CONTROL AND COUNTER
				}
			}

			// IF THERE IS NOT ANY DRAW
			if (flag == 0) {

				System.out.println("\nThe winner is " + players[winNum].getName());

				// IF THERE IS A DRAW(S)
			} else {
				System.out.println("There is a draw between ");
				System.out.println(players[winNum].getName());
				for (int i = 0; i < flag; i++) { // FOR LOOP ACCORDING WITH THE NUMBER OF DRAWS
					System.out.println(players[draw[i]].getName()); // VALIABLE DRAW[] STORAGE THE NUMBER OF THE PLAYER

				}
			}
		}

		// I HAVE USED A TEXT GENERATOR TO GET THIS MESSAGE
		// http://patorjk.com/software/taag/
		System.out.println("    _____                         ____                 ");
		System.out.println("   / ____|                       / __ \\                ");
		System.out.println("  | |  __  __ _ _ __ ___   ___  | |  | |_   _____ _ __ ");
		System.out.println("  | | |_ |/ _` | '_ ` _ \\ / _ \\ | |  | \\ \\ / / _ \\ '__|");
		System.out.println("  | |__| | (_| | | | | | |  __/ | |__| |\\ V /  __/ |   ");
		System.out.println("   \\_____|\\____|_| |_| |_|\\___|  \\____/  \\_/ \\___|_|   ");
		System.out.println(
			"\n\t    Thanks so much! See you soon!!!\n\t Rodolfo Carvajal - 2017032                                                            ");
	
		
	}

}
