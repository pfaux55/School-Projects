# LetterCrush

## Author
Peter Faux

## Description
This project is a grid-based game called LetterCrush, where characters are arranged in a grid. The game supports operations such as populating the grid with initial characters, applying gravity to make characters fall into empty spaces, removing lines of characters horizontally or vertically, and cascading removals where lines are removed continuously until no more lines can be formed.

## Requirements
- Java Development Kit (JDK) installed on your system

## Project Structure
- **LetterCrush.java**: Contains the main game logic, including grid initialization, gravity application, line removal, and cascade effects.
- **Line.java**: Represents a line segment on the grid, defined by start and end points, and supports both horizontal and vertical orientations.
- **LetterCrushTest.java**: Contains test cases for verifying the functionality of the `LetterCrush` class.
- **LineTest.java**: Contains test cases for verifying the functionality of the `Line` class.

## How to Use

### Running the Program

1. Compile the Java files using the following command:

   ```bash
   javac LetterCrush.java Line.java LetterCrushTest.java LineTest.java
2. Run the test cases using the following command:

   ```bash
   java LetterCrushTest
   java LineTest
### Example Interaction


   After running the LetterCrushTest and LineTest, you will see output indicating the status of each test, such as:

   ```bash
   Test 1 (LetterCrush: toString()) passed
   Test 2 (LetterCrush: applyGravity() & isStable()) passed
   ...
   Your code scored: 7 / 7
   ```

## How it Works

### Grid Initialization:

File: LetterCrush.java
Function: LetterCrush(int width, int height, String initial)
Process: Initializes a grid with specified width and height, populating it with characters from the initial string or empty spaces.

### Gravity Application:
File: LetterCrush.java
Function: applyGravity()
Process: Makes characters fall into empty spaces below them until the grid is stable.

### Line Removal:
File: LetterCrush.java
Function: remove(Line theLine)
Process: Removes a specified line of characters from the grid, either horizontally or vertically.

### Cascading Removals:
File: LetterCrush.java
Function: cascade()
Process: Continuously removes the longest lines and applies gravity until no more lines can be formed.

### Line Representation:
File: Line.java
Function: Line(int row, int col, boolean horizontal, int length)
Process: Represents a line segment on the grid with start and end points, supporting both horizontal and vertical orientations.

### Testing:
File: LetterCrushTest.java & LineTest.java
Functions: Various test functions such as test(int testNumber, String message, boolean testStatus)
Process: Verifies the functionality of the LetterCrush and Line classes, ensuring the game logic operates as expected.