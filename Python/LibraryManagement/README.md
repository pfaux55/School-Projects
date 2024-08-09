# Library System

## Author
Peter Faux
## Description
This project provides a basic interface to manage a collection of books in a library. 
Users can add new books, borrow books based on specific search criteria, return borrowed 
books using their ISBN, view a list of all books, and exit the program. The system maintains 
a record of all books, tracks borrowed books, and monitors availability status.

## Requirements

- Python 3.x installed on your system

## Project Structure

- **library.py**: Main program file that contains all the functions for managing the library system.

## How to Use

### Running the Program

1. Open a terminal and navigate to the directory containing the `library.py` file.
2. Run the program using the following command:

   ```bash
   python library.py

### Main Menu Options
1. (A)dd a new book: Allows users to add a new book to the library by entering details 
like name, author, edition, and ISBN.
2. Bo(R)row books: Enables users to borrow books based on specific search criteria.
3. Re(t)urn a book: Facilitates returning borrowed books using their ISBN.
4. (L)ist all books: Displays a list of all books in the library along with their details and availability status.
5. E(x)it: Exits the program.

### Example Interaction
#### Adding a New Book:
	Book Name> The Great Gatsby
	Author name> F. Scott Fitzgerald
	Edition> 1
	ISBN> 9780743273565
	A new book is added successfully.

#### Borrowing a Book

	
	Enter the borrower name> John Doe
	Search term> Gatsby
	-"The Great Gatsby" is borrowed!

#### Returning a Book

	
	ISBN> 9780743273565
	"The Great Gatsby" is returned.

#### Listing All Books

	
	---------------
	[Available]
	The Great Gatsby - F. Scott Fitzgerald
	E: 1 ISBN: 9780743273565
	borrowed by: []
	---------------
	[Unavailable]
	The Earth Inside Out - Mike B
	E: 2 ISBN: 9780596007126
	borrowed by: [Ali]

### How it Works


#### Adding a New Book:

Function: add_book
Process:
Prompts the user for the book's name (bookName), ensuring it does not contain invalid characters (*, %).
Prompts for the author's name (bookAuthor).
Prompts for the edition (bookEdition), ensuring it is a valid integer.
Prompts for the ISBN (bookISBN), ensuring it is a 13-digit number and validates it using a checksum calculation.
Checks for duplicate ISBNs in the availableISBNs and borrowedISBNs lists.
Adds the new book to the allBooks list and the ISBN to the availableISBNs list if valid.

#### Borrowing a Book:
Function: borrow_book
Process:
Prompts for the borrower's name (borrowerName).
Prompts for a search term (searchTerm) to find the book.
Searches the allBooks list for books matching the search term in the title.
If a matching book is found and it is available, it removes the ISBN from availableISBNs and adds it to borrowedISBNs.
Records the borrower's name in the book's entry in the allBooks list.

#### Returning a Book:
Function: return_book
Process:
Prompts for the book's ISBN (returnedISBN).
Checks if the ISBN is in the borrowedISBNs list.
If found, removes the ISBN from borrowedISBNs and adds it to availableISBNs.
Updates the status of the book in the allBooks list.

#### Listing All Books:
Function: list_books
Process:
Iterates through the allBooks list.
For each book, checks if the ISBN is in availableISBNs or borrowedISBNs.
Prints the book's details along with its availability status.

#### Main Menu:
Function: main
Process:
Displays a menu with options to add, borrow, return, list books, or exit the program.
Prompts the user for their choice (menuChoice).
Calls the corresponding function based on the user's selection.
Continues to display the menu until the user chooses to exit.


