# Factorial Calculator
## Author:
Peter Faux

This project contains a simple Java program that calculates the factorial of a given number. 
The program prompts the user to enter a positive integer between 1 and 100 and then calculates 
and displays the factorial of that number.

### Requirements

- Java Development Kit (JDK) installed on your system

### How to Use

#### 1. **Compile the Program:**

Open a terminal and navigate to the directory containing the `Factorial.java` file. 
Compile the program using the following command:

  	
  	javac Factorial.java

#### 2. Run the Program:

After successful compilation, run the program using the following command:
	java Factorial

#### 3. Input a Number:

When prompted, enter a positive integer between 1 and 100. The program will validate 
the input and then calculate and display the factorial of the entered number.

Example interaction:

	Enter a positive integer (1-100): 5
	5 --> 120

#### How It Works
The program uses a recursive method factorNext to calculate the factorial of the input number.
The main method handles user input, ensuring that the input is a valid integer within the specified range.
