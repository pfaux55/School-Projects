# Linked Number Class

## Author
Peter Faux
## Description

The LinkedNumber class models numbers as doubly linked lists to facilitate operations such as base conversion, digit manipulation, and validation within a specified numerical base. It supports initializing numbers from strings or integers, adding or removing digits at specific positions, and checking the validity of the number for its base.

## Requirements

- Java Development Kit (JDK) 8 or higher

## Project Structure

- **LinkedNumber.java**: The main class that handles the representation and manipulation of numbers as doubly linked lists.
- **LinkedNumberException.java**: A custom exception class to handle specific errors related to the LinkedNumber class.
- **DLNode.java**: A doubly linked node class used within LinkedNumber to represent each digit.
- **Digit.java**: A class to represent individual digits and their corresponding values in different bases.
- **TestLinkedNumber.java**: A testing class with various test cases to validate the functionality of the LinkedNumber class.

## How to Use

1. **Compile the Classes**:
   ```bash
   javac LinkedNumber.java LinkedNumberException.java DLNode.java Digit.java TestLinkedNumber.java
2. Run the Tests:

   ```bash
   java TestLinkedNumber
This will execute a series of tests to verify the functionality of the LinkedNumber class.

## How it Works
LinkedNumber Initialization:

The class can be initialized with either a string or an integer. The constructor converts the input into a doubly linked list of digits.
Base Conversion:

convert(int newBase): Converts the number from its current base to a new base and returns a new LinkedNumber object representing the converted number.
Digit Manipulation:

addDigit(Digit digit, int position): Adds a digit to a specified position in the linked list.
removeDigit(int position): Removes a digit from the specified position in the linked list and returns its value in base 10.
Validation:

isValidNumber(): Checks if all digits in the number are valid according to its base.
Equality Check:

equals(LinkedNumber other): Compares two LinkedNumber objects to determine if they represent the same number in the same base.
The TestLinkedNumber class contains methods to test various functionalities of the LinkedNumber class, including base conversions, digit manipulations, and equality checks.