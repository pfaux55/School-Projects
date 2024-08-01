'''
CS1026a 2023
Assignment 01 Project 01 - Part A
Peter Faux

pfaux
Sept 26 2023

'The Fibonacci Sequence'
The Fibonacci sequence is a sequence of numbers in which each successive number in the sequence is obtained by adding the two previous numbers in the sequence.
The sequence starts with zero and one, and proceeds forth as 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55 and so on.
This program prompts the user to set the amount of times the sequence will run, then prints the sequence accordingly
'''


#printing title of program
print("Project One (01) - Part A : Fibonacci Sequence")

#num_1 is the first placeholder in the sequence
num_1 = 0
#num_2 is the second placeholder in the sequence
num_2 = 1
#num_3 is the third placeholder in the sequence
num_3 = 0

#valid_answer is a boolean variable for whether or not a valid input was given
valid_answer = False

#running loop until valid answer is given
while valid_answer == False:

    #obtaining the last value the sequence is running until
    #loop_max is the last value the sequence is running until
    loop_max = int(input("Sequence ends at: "))

    #if the number inputted was greater then 0, the loop is ran
    if loop_max > 0:
        valid_answer = True

    #informing the user the input given was invalid
    else:
        print("Seqeunce must end in a number and must be greater then 0")


#running the loop the amount of times the user requested and printing the values in the sequence
#n is the number for loop is starting with
for n in range(loop_max + 1) :

    #printing the sequence number it is currently on, and then the number it calculated from the sequence both unformatted and formatted
    print(n, f": {num_1} {num_1:,d}")
    
    #setting num_3 to the sum of both numbers as a placeholder
    num_3 = num_1 + num_2
    #setting num_1 to the value of num_2
    num_1 = num_2
    #setting num_3 to the value of num_3 (the sum of both numbers previously)
    num_2 = num_3
        

print("END: Project One (01) - Part A")
print("Peter Faux pfaux ")