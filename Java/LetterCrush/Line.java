/**
 * The Line class represents a line segment on a 2D grid, defined by start and end points with support for both 
 * horizontal and vertical orientations. It provides methods to get the start point, calculate line length, 
 * check orientation, determine if a point is on the line, and to represent the line as a string. 
 * This class is useful for grid-based geometric calculations in graphics, games, and spatial analysis.
 * 
 * @author - pfaux
 */
public class Line {
	// Start point of the line represented by an array of two integers [row, col].
	private int[] start = new int[2];
	 // End point of the line represented by an array of two integers [row, col].
	private int[] end = new int[2];
	 /**
     * Constructor to initialize a line given its start position, orientation, and length.
     * 
     * @param row The row index of the start point.
     * @param col The column index of the start point.
     * @param horizontal A boolean indicating the orientation of the line (true for horizontal, false for vertical).
     * @param length The length of the line.
     */
	public Line(int row, int col, boolean horizontal, int length) {
		 // Setting the start position of the line.
		this.start[0] = row;
		this.start[1] = col;
		
		// If the line is horizontal, calculate the end column index keeping the row constant.
		if (horizontal == true){
			this.end[1] = this.start[1] + length - 1;
			this.end[0] = this.start[0];
		}
		 // If the line is vertical, calculate the end row index keeping the column constant.
		else {
			this.end[0] = this.start[0] + length - 1;
			this.end[1] = this.start[1];
		}
		
	}
	
	/**
     * Provides a copy of the start point of the line to prevent direct modification.
     * 
     * @return A new array containing the start position [row, col].
     */
	public int[] getStart() {
		int[] startCopy = new int[2];
		startCopy[0] = start[0];
		startCopy[1] = start[1];
		return startCopy;
	}

	/**
     * Calculates the length of the line based on its orientation.
     * 
     * @return The length of the line.
     */
	public int length() {
		if (this.isHorizontal() == true) {
			return this.end[1] - this.start[1] + 1;
		}
		else {
			return this.end[0] - this.start[0] + 1;
		}
		
	}
	
	/**
     * Determines if the line is horizontal based on the start and end points.
     * 
     * @return true if the line is horizontal, otherwise false.
     */
	public boolean isHorizontal() {
		if (this.start[0] == this.end[0]) {
			return true;
		}
		return false;
	}
	
	 /**
     * Checks if a given point lies on the line.
     * 
     * @param row The row index of the point to check.
     * @param col The column index of the point to check.
     * @return true if the point is on the line, otherwise false.
     */
	public boolean inLine(int row, int col) {
		if (this.isHorizontal() == true) {
			if (this.start[0] == row && start[1] <= col && end[1] >= col) {
				return true;
			}
		}
		else {
			if (this.start[1] == col && start[0] <= row && end[0] >= row) {
				return true;
			}
		}
		return false;
	}
	
	/**
     * Generates a string representation of the line including its start and end points.
     * 
     * @return A string describing the line in the format "Line:[startRow,startCol]->[endRow,endCol]".
     */
	public String toString() {
		return String.format("Line:[%d,%d]->[%d,%d]", this.start[0], this.start[1], this.end[0], this.end[1]);
	}
}
