/**
 * The LetterCrush class represents a grid-based game where characters are arranged in a grid.
 * The class supports various operations like populating the grid with initial characters,
 * applying gravity to make characters fall into empty spaces, removing lines of characters
 * horizontally or vertically, and cascading removals where lines are removed continuously
 * until no more lines can be formed.
 * 
 * @author - pfaux
 */
public class LetterCrush {
	 // 2D char array to represent the game grid.
	private char[][] grid;
	// Constant to represent an empty space in the grid.
	public static final char EMPTY = ' ';

	 /**
     * Constructor to initialize the game grid with specified width, height, and initial characters.
     * 
     * @param width The width of the grid.
     * @param height The height of the grid.
     * @param initial A string containing the initial characters to populate the grid. (Should be less than or equal to
     *                width * height. If longer, excess characters are ignored.)
     */
	public LetterCrush(int width, int height, String initial) {
		this.grid = new char[width][height];
		int currentChar = 0;
		// Populate the grid row by row with characters from the initial string or empty spaces.
		for(int h = 0; h < height; h++) {
			for (int w = 0; w < width; w++) {
				if (currentChar < initial.length()) {
					this.grid[w][h] = initial.charAt(currentChar);
				}
				else {
					this.grid[w][h] = EMPTY;
				}
				currentChar++;
			}
		}
		
	}
	
	 /**
     * Returns a string representation of the game grid, including row and column indices.
     * 
     * @return A string visualizing the grid.
     */
	public String toString() {
		String gridVis = "";
		
		gridVis += "LetterCrush\n";
		 // Append each character in the grid, adding borders and indices for clarity.		
		for (int h = 0; h < this.grid[0].length; h++) {
			gridVis += "|";
			for (int w = 0; w < this.grid.length; w++) {
				gridVis += this.grid[w][h];
			}
			gridVis += String.format("|%d\n", h);
		}
		gridVis += "+";
		for (int q = 0; q < this.grid.length; q++) {
			gridVis += q;
		}
		gridVis += "+";
		return gridVis;
	}				
	
	 /**
     * Checks if the grid is stable, meaning no characters can fall into empty spaces.
     * 
     * @return true if stable, false otherwise.
     */
	public boolean isStable() {
		for (int col = 0; col < this.grid.length; col++) {
			for (int row = this.grid[0].length - 2; row >= 0; row--) {
				if ((this.grid[col][row] != EMPTY) && (this.grid[col][row + 1] == EMPTY)) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
     * Applies gravity to the grid, making characters fall into empty spaces below them.
     */
	public void applyGravity() {
		while(isStable() == false) {
			for (int col = 0; col < this.grid.length; col++) {
				for (int row = this.grid[0].length - 2; row >= 0; row--) {
					if ((this.grid[col][row] != EMPTY) && (this.grid[col][row + 1] == EMPTY)) {
						this.grid[col] [row+1] = this.grid[col][row];
						this.grid[col][row] = EMPTY;
					}
				}
			}
		}
	}
	
	 /**
     * Removes a line of characters from the grid, specified by a Line object.
     * 
     * @param theLine The Line object specifying the line to remove.
     * @return true if the line was successfully removed, false otherwise.
     */
	public boolean remove(Line theLine) { 
		
		if (theLine.isHorizontal() == true){
			// Check if removal is within bounds and perform removal based on line orientation.
			if (((theLine.getStart()[1] + theLine.length() - 1) >= this.grid.length)){
				return false;
			}
			else {
				int row = theLine.getStart()[0];
				for (int col = theLine.getStart()[1]; col <= (theLine.getStart()[1] + theLine.length() - 1); col++) {
					this.grid[col][row] = EMPTY;
				}
					
				return true;
			}
		}
		else {
			if (((theLine.getStart()[0] + theLine.length() - 1) >= this.grid[0].length)){
				return false;
			}
			else {
				int col = theLine.getStart()[1];
				for (int row = theLine.getStart()[0]; row <= (theLine.getStart()[0] + theLine.length() - 1); row++) {
					this.grid[col][row] = EMPTY;
					
				}
				return true;
			}
		}
	}
	
	/**
     * Generates a string representation highlighting a specific line within the grid.
     * 
     * @param theLine The Line object representing the line to highlight.
     * @return A string visualizing the grid with the specified line highlighted.
     */
	public String toString(Line theLine) {
		
		String gridVis = "";
			
		gridVis += "CrushLine\n";
				
		for (int h = 0; h < this.grid[0].length; h++) {
			gridVis += "|";
			for (int w = 0; w < this.grid.length; w++) {
				if (theLine.inLine(h, w)) {
					gridVis += Character.toLowerCase(this.grid[w][h]);
				}
				else {
					gridVis += this.grid[w][h];
				}
				
			}
			gridVis += String.format("|%d\n", h);
		}
		gridVis += "+";
		for (int q = 0; q < this.grid.length; q++) {
			gridVis += q;
		}
		gridVis += "+";
		return gridVis;
	}
	
	/**
     * Identifies the longest contiguous line of identical characters in the grid.
     * 
     * @return The longest Line object found in the grid; returns null if no line longer than 2 characters.
     */
	public Line longestLine() {
//		// Scan first the rows of the grid from bottom to top
//		
////		Create an object of the class Line representing a horizontal line starting at the first row and first column
////		of grid and of length 1. Store the address of this object in a variable called longLine.
		Line longLine = new Line(0,0 , true,1);
		int largest = 0;
		int adjacent;
//			for each row i of the grid starting at the bottom and moving to the top do {
////		letter = letter at row i and leftmost column of the grid
		for (int i = this.grid[0].length - 1; i >= 0; i--) {
			char letter = this.grid[0][i];
			adjacent = 1;
//			for each column j of the grid starting at the second one and moving to the right do {
			for (int j = 1; j < this.grid.length; j++) {
//				if letter at row i and column j of the grid is equal to letter and letter ≠ blank space then {
//				increase adjacent
				if ((grid[j][i] == letter) && (letter != EMPTY)) {
					adjacent++;
//					if adjacent is bigger than largest then {
					if (adjacent > largest) {
						largest = adjacent;
//					longLine = new object of the class Line representing a horizontal line
						longLine = new Line(i, j-adjacent+1, true, adjacent);
					}
				}
				else {
					letter = grid[j][i];
					adjacent = 1;
				}
			}
			}	
//		// Now scan the columns from left to right; each column is scanned from the bottom to the top
//		for each column j of the grid starting at the leftmost one and moving to the right do {
		for (int j = 0; j < this.grid.length; j++) {
//			letter = letter at the bottom row of the grid and column j
			char letter = grid[j][grid[0].length - 1];
			adjacent = 1;
//			for each row i of the grid from the second row from the bottom and moving to the top do {
			for (int i = grid[0].length - 2; i >= 0; i--) {
//				if letter at row i and column j of the grid is equal to letter and letter ≠ blank space then {
//				increase adjacent
				if (grid[j][i] == letter && letter != EMPTY) {
					adjacent ++;
//					if adjacent is bigger than largest then {
//					largest = adjacent
//					longline = new object of the class Line representing a vertical line
//					starting at row i and column j of length adjacent
//					}
					if (adjacent > largest) {
						largest = adjacent;
						longLine = new Line(i , j, false, adjacent);
					}
				}
				else {
					letter = grid[j][i];
					adjacent = 1;
				}
			}
		}
//		if the length of longline is larger than 2 then return longline else return null
		if (longLine.length() > 2) {
			return longLine;
		}
		else {
			return null;
		}
	}

	/**
     * Applies the cascade effect by continuously removing the longest lines and applying gravity
     * until no more lines can be formed.
     */
	public void cascade() {
		while (longestLine() != null) {
			remove(longestLine());
			while (isStable() == false) {
				applyGravity();
			}
		}
	}	
}


	
	