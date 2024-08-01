"""
CS1026a
Assignment 02 - Library System
October 24 2023
Peter Faux
pfaux 251363513


This program provides a basic interface to manage a collection of books in a library.
Users can:
Add new books to the system with details like name, author, edition and ISBN
Borrow books based on specific search criteria
Return borrowed books using their ISBN
View a list of all teh books, displaying details and their availability status
5. Exit the program

The system maintains a record of all books, the books that are currently borrowed, and the books that are available
it also tracks the users that have borrowed each book
"""

# start() function calls the main function
def start():
    print_menu()
    main()


# print_menu() prints the main menu of the program to display options to the user
def print_menu():
    print('\n######################')
    print('1: (A)dd a new book.')
    print('2: Bo(R)row books.')
    print('3: Re(t)urn a book.')
    print('4: (L)ist all books.')
    print('5: E(x)it.')
    print('######################\n')


# add_book() function to add new book with all information to the library
def add_book():

    # validAnswer: A flag to check the validity of the input from the user for info
    # bookName stores the name of the book
    validAnswer = False
    while not validAnswer:
        bookName = input("Book Name> ")
        if not ("*" in bookName or "%" in bookName):
            validAnswer = True
        else:
            print("Invalid book name!")

    # bookAuthor: String input to store the author's name
    bookAuthor = input("Author name> ")


    # bookEdition: stores the numeric edition of the book
    validAnswer = False
    while not validAnswer:
        bookEdition = input("Edition> ")
        if bookEdition.isdigit():
            validAnswer = True
            bookEdition = int(bookEdition)
        else:
            print("Invalid book edition!")

    # bookISBN: Numeric input to represent the book's ISBN number
    invalidAnswer = True
    while invalidAnswer:
        bookISBN = input("ISBN> ")
        if bookISBN.isdigit() and len(bookISBN) == 13:
            invalidAnswer = False
        else:
            print("Invalid ISBN!")

    # total: Used to compute the sum for ISBN validation
    total = 0
    for digit in range(0, len(bookISBN)):
        if digit % 2 == 0:
            total += int(bookISBN[digit])
        else:
            total += (int(bookISBN[digit]) * 3)

    if total % 10 != 0:
        print("Invalid ISBN!")
        return
    elif bookISBN in availableISBNs or bookISBN in borrowedISBNs:
        print("Duplicate ISBN is found! Cannot add the book.")
        return
    else:
        print("A new book is added successfully.")

    # oneNewBook: List storing the collected information on the book
    oneNewBook = [bookISBN, bookName, bookAuthor, bookEdition, []]
    availableISBNs.append(bookISBN)
    allBooks.append(oneNewBook)

    return


# borrow_book() handles the process of borrowing a book
def borrow_book():

    for t in allBooks:
        if t[0] not in borrowedISBNs and t[0] not in availableISBNs:
            availableISBNs.append(t[0])

    # foundISBNs is a list that collects the ISBNs of books found from the search term
    foundISBNs = []

    # borrowerName stores the name of the person borrowing the book
    borrowerName = input("Enter the borrower name> ")

    # booksWereFound is a flag to determined whether books have been borrowed or not
    # searchTerm is a variable that stores what the user enters to search for books
    # ISBNBorrowed is a variable that stores the ISBN of the book currently being borrowed
    booksWereFound = False
    searchTerm = input("Search term> ")
    if searchTerm[-1] == "*":
        for currentBook in range(len(allBooks)):
            if searchTerm.strip("*").lower() in allBooks[currentBook][1].lower().replace(" ", "") and allBooks[currentBook][0] in availableISBNs:
                ISBNBorrowed = (allBooks[currentBook][0])
                foundISBNs.append(ISBNBorrowed)
                availableISBNs.remove(ISBNBorrowed)
                print("-" + '"' + allBooks[currentBook][1] + '"', "is borrowed!")
                booksWereFound = True


    elif searchTerm[-1] == "%":
        for currentBook in range(len(allBooks)):
            if searchTerm.strip("%").lower() in allBooks[currentBook][1].lower().split()[0] and allBooks[currentBook][0] in availableISBNs:
                ISBNBorrowed = (allBooks[currentBook][0])
                foundISBNs.append(ISBNBorrowed)
                availableISBNs.remove(ISBNBorrowed)
                print("-" + '"' + allBooks[currentBook][1] + '"', "is borrowed!")
                booksWereFound = True


    else:
        for currentBook in range(len(allBooks)):
            if searchTerm.lower() == allBooks[currentBook][1].lower() and allBooks[currentBook][0] in availableISBNs:
                ISBNBorrowed = (allBooks[currentBook][0])
                foundISBNs.append(ISBNBorrowed)
                availableISBNs.remove(ISBNBorrowed)
                print("-" + '"' + allBooks[currentBook][1] + '"', "is borrowed!")
                booksWereFound = True


    if foundISBNs == [] or booksWereFound == False:
        print("No books found!")
        return

    # Adding the name of the borrower to the list of people who have borrowed the book
    for each in allBooks:
        if each[0] in foundISBNs:
            borrowedISBNs.append(each[0])
            each[4].append(borrowerName)


# return_book() handles the process of returning a book
def return_book():

    # returnedISBN stores the ISBN of the book being returned
    returnedISBN = input("ISBN> ")

    # bookIsReturned is a flag to determine whether a book has been returned
    bookIsReturned = False
    for currentBook in allBooks:
        if currentBook[0] == returnedISBN and returnedISBN in borrowedISBNs:
            borrowedISBNs.remove(returnedISBN)
            availableISBNs.append(returnedISBN)
            print('"' + currentBook[1] + '"', "is returned.")
            bookIsReturned = True

    if bookIsReturned == False:
        print("No book is found!")


# list_books() handles the process of listing all the books with their info and whether they are available
def list_books():
    for i in allBooks:
        print("---------------")
        if i[0] in availableISBNs:
            print("[Available]")
        elif i[0] in borrowedISBNs:
            print("[Unavailable]")
        print(i[1], " - ", i[2])
        print("E: ", i[3], "ISBN:", i[0])
        print("borrowed by:", i[4])


# main() is the central function of the program, runs a function depending on the user input
def main():

    # stillGoing is a flag to determine whether the user is done making entries
    stillGoing = True
    while stillGoing == True:

        # menuChoice is a variable that stores the user inputted choice from the menu

        menuChoice = input("Your selection> ")

        if menuChoice == '1' or menuChoice == 'a' or menuChoice == 'A':
            add_book()

        elif menuChoice == '2' or menuChoice == 'r' or menuChoice == 'R':
            borrow_book()

        elif menuChoice == '3' or menuChoice == 't' or menuChoice == 'T':
            return_book()

        elif menuChoice == '4' or menuChoice == 'l' or menuChoice == 'L':
            list_books()

        elif menuChoice == '5' or menuChoice == 'x' or menuChoice == 'X':
            stillGoing = False

        else:
            print("Wrong selection! Please selection a valid option.")

        if menuChoice != '5' and menuChoice != 'x' and menuChoice != 'X':
            print_menu()

    print("\n\n$$$$$$$$ FINAL LIST OF BOOKS $$$$$$$$")
    list_books()


# allBooks list of all books stored in the library
allBooks = [
    ['9780596007126', "The Earth Inside Out", "Mike B", 2, ['Ali']],
    ['9780134494166', "The Human Body", "Dave R", 1, []],
    ['9780321125217', "Human on Earth", "Jordan P", 1, ['David', 'b1', 'user123']]
]
# borrowedISBNs list of IBSNs of books being borrowed currently
borrowedISBNs = []
# availableISBNs list of IBSNs of books available currently
availableISBNs = ['9780596007126', '9780134494166', '9780321125217']

start()
