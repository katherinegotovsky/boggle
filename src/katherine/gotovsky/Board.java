package katherine.gotovsky;

import java.util.Random;

public class Board {
	private int size = 4; // Dimensions of grid
	Cell[][] grid = new Cell[size][size];
	private Random generator = new Random();
	
	public Board() {
		generator = new Random();
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j] = new Cell(randomChar());
			}
		}
	}

	public String toString() {
		String str = "|";
		for (int j = 0; j < size * 4 - 1 ; j++) {
			str = str + '-';
		}
		str = str + "|\n";

		for (int i = 0; i < size; i++) {
			str = str + "| ";
			for (int j = 0; j < size; j++) {
				str = str + grid[i][j];
				str = str + " | ";
			}
			str = str + "\n|";
			for (int j = 0; j < size * 4 - 1; j++) {
				str = str + '-';
			}
			str = str + "|\n";
		}
		return str;
	}
	
	
private String randomChar() {
	float i = generator.nextInt(1000000);
	i = i / 1000000;
	if (i < .08167) {
		return "A";
	}
	if (i < .09659) {
		return "B";
	}
	if (i < .12441) {
		return "C";
	}
	if (i < .16694) {
		return "D";
	}
	if (i < .29396) {
		return "E";
	}
	if (i < .31624) {
		return "F";
	}
	if (i < .33639) {
		return "G";
	}
	if (i < .39733) {
		return "H";
	}
	if (i < .46699) {
		return "I";
	}
	if (i < .46852) {
		return "J";
	}
	if (i < .47624) {
		return "K";
	}
	if (i < .51649) {
		return "L";
	}
	if (i < .54055) {
		return "M";
	}
	if (i < .60804) {
		return "N";
	}
	if (i < .68311) {
		return "O";
	}
	if (i < .70240) {
		return "P";
	}
	if (i < .70335) {
		return "Q";
	}
	if (i < .76322) {
		return "R";
	}
	if (i < .82649) {
		return "S";
	}
	if (i < .91705) {
		return "T";
	}
	if (i < .94463) {
		return "U";
	}
	if (i < .95441) {
		return "V";
	}
	if (i < .97801) {
		return "W";
	}
	if (i < .97951) {
		return "X";
	}
	if (i < .99925) {
		return "Y";
	}
	if (i < 1) {
		return "Z";
	} else {
		// Failsafe
		return "E";
	}
}

public void resetBoard() {
	// reset the board visits
	for (int row = 0; row < size; row++) {
		for (int col = 0; col < size; col++) {
			grid[row][col].unVisit();
		}
	}
}

private boolean isWordInBoard(char[] wChars, int pos, int row, int col) {
	grid[row][col].setVisited(); // set visited for the letter on the
			
	if (pos < (wChars.length) - 1) {

		// check the letters above on the board
		if (row > 0) {
			if (!grid[row - 1][col].wasVisited()) {
				if (grid[row - 1][col].value.charAt(0) == wChars[pos + 1]) {
					pos++; // got a match.... move to next letter and keep going up
					if (isWordInBoard(wChars, pos, row - 1, col))
						return true;
				}
			} 
		}
		// check the letter to the right
		if (col < size - 1) {
			if (!grid[row][col + 1].wasVisited()) {
				if (grid[row][col + 1].value.charAt(0) == wChars[pos + 1]) {
					pos++;
					if (isWordInBoard(wChars, pos, row, col + 1))
						return true;
					}
			} 
		}
		
		// check the letter below
		if (row < size - 1) {
			if (!grid[row + 1][col].wasVisited()) {
				if (grid[row + 1][col].value.charAt(0) == wChars[pos + 1]) {
					pos++; // found below keep looking
					if (isWordInBoard(wChars, pos, row + 1, col))
						return true;
						}
			} 
		}
		// check the letter to the left
		if (col > 0) {
			if (!grid[row][col - 1].wasVisited()) {
				if (grid[row][col - 1].value.charAt(0) == wChars[pos + 1]) {
					pos++;
					if (isWordInBoard(wChars, pos, row, col - 1))
						return true;
									}
			} 
		}
		
		//check the letter to the top right
		
		if (col < size - 1 && row > 0) {
			if (!grid[row-1][col+1].wasVisited()) {
				if (grid[row-1][col+1].value.charAt(0) == wChars[pos + 1]) {
					pos++;
					if (isWordInBoard(wChars, pos, row-1, col+1))
						return true;
									}
			}
		}
		
		//check the letter to the top left
		
		if (col > 0 && row > 0) {
			if (!grid[row-1][col-1].wasVisited()) {
				if (grid[row-1][col - 1].value.charAt(0) == wChars[pos + 1]) {
					pos++;
					if (isWordInBoard(wChars, pos, row-1, col - 1))
						return true;
									}
			}
		}
		//check letter to the bottom left
		
		if (row < size-1 && col > 0) {
			if (!grid[row+1][col-1].wasVisited()) {
				if (grid[row+1][col - 1].value.charAt(0) == wChars[pos + 1]) {
					pos++;
					if (isWordInBoard(wChars, pos, row+1, col - 1))
						return true;
									}
			}
		}
		
		//check letter to the bottom right
		
		if (row < size-1 && col < size-1) {
			if (!grid[row+1][col + 1].wasVisited()) {
				if (grid[row+1][col + 1].value.charAt(0) == wChars[pos + 1]) {
					pos++;
					if (isWordInBoard(wChars, pos, row+1, col +1 ))
						return true;
									}
			}
		}
		
		return false;
		
	} else {
		return true; // All letters have been checked

	}
}

public boolean checkWord(String word) {
	char[] wordChars = word.toCharArray();
	int pos = 0; // start with the first letter in the word
	char curr_letter = wordChars[pos];

	boolean found = false;
	boolean done = false;
	int row = 0, col = 0;
	while (!done) {
		if (curr_letter == grid[row][col].value.charAt(0)) {
			if (isWordInBoard(wordChars, pos, row, col)) {
				found = true;
			}
		}
		if (col < size - 1) {
			col++;
		} else {
			col = 0;
			if (row < size - 1) {
				row++;
			} else {
				done = true;
			}

		}
		resetBoard();

	}
	return found;

}
}