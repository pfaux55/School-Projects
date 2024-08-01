'''
CS1026a 2023
Assignment 01 Project 01 - Part B
Peter Faux
251363513
pfaux
Sept 26 2023

'Prime Choice'
the user is asked to provide the from & to range and then the program displays all the prime numbers in sequential manner for the provided range. 
Using this program, the user can find out the prime numbers between any valid range. The user enters the desired range, for example, if the user 
wants to display all the prime numbers from 100 to 999 then they will enter numbers 100 and 999 when program prompts for input
'''

#printing title of program
print("Project One (01) - Part B : Prime Choice")

#getting starting and ending numbers from user

#valid_answer is a boolean variable for whether or not a valid input was given
valid_answer = False

#running loop until valid answer is given
while valid_answer == False:

    #obtaining the number the sequence is starting with
    #starting_num is the number the sequence is starting with
    starting_num = int(input("Prime Numbers starting with: "))

    #if the number inputted was greater then 0, the loop is ran
    if starting_num > 0:
        valid_answer = True

    #informing the user the input given was invalid
    else:
        print("Seqeunce must start with a number and must be greater then 0")

#boolean variable for whether or not a valid input was given
valid_answer = False

#running loop until valid answer is given
while valid_answer == False:

    #obtaining the number the sequence is ending with
    #ending_num is the number the sequence is ending with
    ending_num = int(input("and ending with: "))

    #if the number inputted was greater then 0, the loop is ran
    if ending_num > 0:
        valid_answer = True

    #informing the user the input given was invalid
    else:
        print("Seqeunce must end in a number and must be greater then 0")


#placeholder is a variable to store info temporarily in the process of a switching value sequence
#switching starting_num and ending_num if starting_num is less then ending_num
if starting_num > ending_num:

    place_holder = ending_num
    ending_num = starting_num
    starting_num = place_holder

    print("Incorrect Input: ", ending_num, "is greater than", starting_num)
    print("The numbers have been automatically switched.")

#displaying the number the sequence is beginning and ending with
print("The prime numbers in the range from: ", starting_num, "and", ending_num, "are:")


#every is a variable for the for loop describing the loop runs through every number
#looping through every number from 0 to ending_num
#prime is a boolean variable used for determining whether or not a number is a prime number
for every in range(2,ending_num + 1):

    prime = True

    #each is a variable for the for loop describing the loop runs through every number
    #looping through every number between 2 and ending number
    for each in range(2, every):

        #checking if ending number has a remainder when dividing by current number within range
        if every % each  == 0:

            #print(each,'\n')
            prime = False

    if prime == True and every >= starting_num: 
        print(every, "is prime")

print("END: Project One (01) - Part B")
print("Peter Faux pfaux 251363513")