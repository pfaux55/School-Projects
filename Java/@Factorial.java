import java.util.Scanner;

/**
 * This class provides a method to calculate the factorial of a given number
 * and a main method to get user input and display the factorial.
 * 
 * @author peterfaux
 */
public class Factorial {

    /**
     * Calculates the factorial of a given number using recursion.
     *
     * @param count the number for which to calculate the factorial
     * @return the factorial of the given number
     */
    public int factorNext(int count) {
        // Base case: factorial of 1 or 0 is 1
        if (count <= 1) {
            return 1;
        }
        // Recursive case
        return count * factorNext(count - 1);
    }

    /**
     * The main method prompts the user to enter a positive integer between 1 and 100,
     * validates the input, and then prints the factorial of the input number.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Factorial test = new Factorial();
        Scanner scanner = new Scanner(System.in);
        int userInput = 0;

        // Loop to get a valid input from the user
        while (true) {
            System.out.print("Enter a positive integer (1-100): ");
            if (scanner.hasNextInt()) {
                userInput = scanner.nextInt();
                if (userInput >= 1 && userInput <= 100) {
                    break; // Valid input, exit loop
                } else {
                    System.out.println("Invalid input. Please enter an integer between 1 and 100.");
                }
            } else {
                System.out.println("Invalid input. Please enter an integer between 1 and 100.");
                scanner.next(); // Clear the invalid input
            }
        }

        scanner.close(); // Close the scanner
        // Print the factorial of the valid input
        System.out.println(userInput + " --> " + test.factorNext(userInput));
    }
}
