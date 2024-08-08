# Library System

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

## Author
Peter Faux


