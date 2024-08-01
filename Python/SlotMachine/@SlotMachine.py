########################################
# Slot Machine Program Phase 4
# Peter Faux
# Creation date: November 3, 2020
# Modified date: n/a
# SlotMachineP4.py
# other files: 6500.dat(cheat code to start with 6500), redCasino.gif, scoreboard.dat
# The program will take you into a "casino" and allow to to play a slot machine
# game. The user starts with 200 or a previously accrued total in their bank and
# then places a bet before spinning the slot machine. 
#
# Variable List
# name          list of names on the scoreboard
# score         list of scores on the scoreboard
# still_going   used to keep playing the slot machine
# Screen        used to setup the size and location of the turtle window
# bet           the amount the user wants to bet before spinning
# bank          amount of money player has in the bank to bet
# slot_1        number in the first slot
# slot_2        number in the second slot
# slot_3        number in the third slot
# label         turtle that creates the bank line
# label_2       turtle that creates the you win line and the bet line
# label_3       turtle that creates the win condition line
# instruct      turtle that creates the instruction screen and conclusion screen
# reading       boolean variable that lets user to read the instructions as long as they want
# keep_spinning used to stop the spinning animation when user clicks the spacebar
########################################

# This function creates an instructon screen into the casino
def instruction_screen():
    global instruct
    turtle.bgcolor("white")
    instruct.pu()
    instruct.goto(-250,300)
    instruct.write("Welcome to the Slot Machine!",font=("arial",30,"normal"))
    instruct.goto(-350,200)
    instruct.write("-Minimum of 5 tickets to play",font=("arial",20,"normal"))
    instruct.goto(-350,150)
    instruct.write("-Bets must be placed in 5 ticket intervals",font=("arial",20,"normal"))
    instruct.goto(-350,100)
    instruct.write("-You place your bet then slots begin spinning",font=("arial",20,"normal"))
    instruct.goto(-350,50)
    instruct.write("-Click the spacebar to stop slot machine",font=("arial",20,"normal"))
    instruct.goto(-350,0)
    instruct.write("-In order to win, you must match 2  or 3 numbers, or get a straight. For example 1, 2, 3",font=("arial",20,"normal"))
    instruct.goto(-350,-50)
    instruct.write("-A  3 of a kind or 3 matched numbers (Ex. 3, 3, 3) will multiply your bet by 4.",font=("arial",20,"normal"))
    instruct.goto(-350,-100)
    instruct.write("-For an outside match, (Ex 2, 3, 2) your bet will multiply by 3.",font=("arial",20,"normal"))
    instruct.goto(-350,-150)
    instruct.write("-For a side by side match, (Ex 2, 2, 3) your bet will multiply by 2",font=("arial",20,"normal"))
    instruct.goto(-350,-200)
    instruct.write("-For a straight, (Ex 1, 2, 3) your bet will multiply by 5",font=("arial",20,"normal"))
    instruct.goto(-300,-300)
    instruct.write("Click 'p' when you are ready to play",font=("arial",30,"normal"))

# This function creates an intro screen into the casino
# n   variable used in for loop to create white box behind "Welcome!" message
def introduction_screen():
    turtle.speed(4)
    turtle.bgcolor("dark red")
    turtle.pu()
    turtle.goto(-100,250)
    turtle.pd()
    turtle.write("Casino",font=("Times", 60, "normal"))
    turtle.hideturtle()
    turtle.pu()
    turtle.goto(-160,70)
    turtle.pd()
    turtle.begin_fill()
    turtle.pencolor("black")
    turtle.fillcolor("white")
    for n in range(2):
            turtle.forward(330)
            turtle.right(90)
            turtle.forward(100)
            turtle.right(90)
    turtle.end_fill()
    turtle.pu()
    turtle.goto(-125,-10)
    turtle.write("Welcome!",font=("Times", 60, "normal"))
    time.sleep(1)

# This function does the setup the slot machine graphics
def draw_slot_machine():
    #Casino at top
    turtle.pu()
    turtle.goto(-100,250)
    turtle.color("dark red")
    turtle.write("Casino",font=("Times", 60, "normal"))
    turtle.bgcolor("black")
    #Slot machine
    turtle.pu()
    turtle.begin_fill()
    turtle.goto(-300,-350)
    turtle.pencolor("black")
    turtle.fillcolor("dark red")
    turtle.pd()
    turtle.goto(-300,200)
    turtle.goto(300,200)
    turtle.goto(300,-350)
    turtle.goto(-300,-350)
    turtle.end_fill()
    #Slot boxes
    turtle.goto(-350,120)
    turtle.pd()
    for n in range(3):
        turtle.setheading(0)
        turtle.pu()
        turtle.forward(150)
        turtle.pd()
        turtle.begin_fill()
        turtle.pencolor("black")
        turtle.fillcolor("white")
        for k in range(2):
            turtle.forward(90)
            turtle.right(90)
            turtle.forward(110)
            turtle.right(90)
        turtle.end_fill()

# This function does the setup the slot machine variables
# valid_file  this variable determines whether the file the user is attempting to open exists
def initialize_slot_machine():
    global bank
    label.speed(0)
    valid_file = True
    while valid_file:
        load_save = tkinter.simpledialog.askstring("Load game" , "Please enter the file you would like to load, enter your number of tickets or click 'cancel' to start a new game:")
        if load_save == None:
            bank = 20
            valid_file = False
        elif load_save != "":
            try:
                my_file = open(load_save+".dat", 'r')
                bank = int(my_file.readline())
                my_file.close()
                valid_file = False
            except FileNotFoundError:
                tkinter.messagebox.showwarning("File Error", "Please enter an existing file")
    label.pu()
    label.goto(100,-170)
    label.write("Tickets: "+str(bank),font=("Arial", 20, "normal"))

# This function gets the bet from the user and does the neccesary error checking
# valid_answer  used for error checking on the answer
def get_bet():
    global bet, bank, still_going
    valid_answer = True
    while valid_answer:
        bet = tkinter.simpledialog.askstring("Bet" , "Please enter your bet or enter 'done' to quit")
        if bet != None:
            try:
                bet = int(bet)
                if bet > 0 and bet <= bank and bet % 5 == 0:
                    valid_answer = False
                else:
                    tkinter.messagebox.showwarning("Bet Error", "Please enter a valid bet")
            except ValueError:
                if bet == 'done':
                    still_going = False
                    valid_answer = False
                else:
                    tkinter.messagebox.showwarning("Bet Error", "Please enter a bet")
        else:
            tkinter.messagebox.showwarning("Bet Error", "Please enter 'done' to exit bet entering")
    label_2.speed(0)
    label_2.clear()
    label_2.pu()
    label_2.goto(-150,-170)
    label_2.write("Bet: "+str(bet),font=("Arial", 20, "normal"))

# This function creates the "spinning" animation for the slots
def spin_slots():
    global slot_1, slot_2, slot_3, keep_spinning
    keep_spinning = True
    slot.speed(0)
    while keep_spinning:
        slot.clear()
        slot_1 = random.randint(1,10)
        slot_2 = random.randint(1,10)
        slot_3 = random.randint(1,10)
        slot.pu()
        slot.goto(-170, 40)
        slot.write(slot_1, font=("Arial", 50, "normal"))
        slot.goto(-20, 40)
        slot.write(slot_2, font=("Arial", 50, "normal"))
        slot.goto(130, 40)
        slot.write(slot_3, font=("Arial", 50, "normal"))
        time.sleep(0.2)

# this function is used to stop the slots
def spacebar():
    global keep_spinning
    keep_spinning = False

# this function is used to determine if the player wins and how they have won
# round_win     used to let the user know they have won
def determine_win():
    global bet, round_win
    round_win = False
    label_3.clear()
    label_3.pu()
    label_3.color("black")
    if slot_1 == slot_2 and slot_2 == slot_3:
        bet = bet * 4
        round_win = True
        label_3.goto(-100, -130)
        label_3.write("three of a kind", font=("Arial", 20, "normal"))
        label_2.goto(-100,-250)
        label_2.write("You won: "+str(bet), font=("Arial", 20, "normal"))
    elif slot_1 == slot_3:
        bet = bet * 3
        round_win = True
        label_3.goto(-100, -130)
        label_3.write("outside match", font=("Arial", 20, "normal"))
        label_2.goto(-100,-250)
        label_2.write("You won: "+str(bet), font=("Arial", 20, "normal"))
    elif slot_1 == slot_2 or slot_2 == slot_3:
        bet = bet * 2
        round_win = True
        label_3.goto(-100, -130)
        label_3.write("side by side match", font=("Arial", 20, "normal"))
        label_2.goto(-100,-250)
        label_2.write("You won: "+str(bet), font=("Arial", 20, "normal"))
    elif slot_3 == slot_2+1 and slot_2 == slot_1+1:
        bet = bet * 5
        round_win = True
        label_3.goto(-100, -130)
        label_3.write("straight", font=("Arial", 20, "normal"))
        label_2.goto(-100,-250)
        label_2.write("You won: "+str(bet), font=("Arial", 20, "normal"))
    else:
        label_3.goto(-100, -100)
        label_3.write("You lost (No matches)",font=("Arial",20,"normal"))
        bet = -bet
    if round_win == True:
        label_3.goto(-50, -100)
        label_3.write("You Win!!", font=("Arial", 20, "normal"))

# this function adds the winnings from the bet to their bank
def add_bank():
    global bank, bet
    bank = bank + bet

# this function updates the bank label to the current bank amount
def update_user():
    label.clear()
    label.write("Bank: "+str(bank),font=("Arial", 20, "normal"))

# this function asks the user if they would like to keep playing or not
def keep_playing():
    global still_going
    still_going = tkinter.messagebox.askyesno("Continue","Would you like to play again?")

# this funciton creates a conclusion message for the user then closes
# the program
# instruct  turtle that writes the exit messages
def conclusion_screen():
    turtle.clearscreen()
    turtle.bgcolor("black")
    instruct.pu()
    instruct.color("dark red")
    instruct.goto(-140,30)
    instruct.write("Thanks for playing!",font=("arial",30,"normal"))
    instruct.goto(-130,0)
    instruct.write("Come back soon!",font=("arial",30,"normal")) 
    time.sleep(3)
    turtle.bye()

# This function allows the user to continue on from the instruction and scoreboard screen 
def p_key():
    global reading
    reading = False

#This function creates the scroeboard from the scoreboard file
# more_lines    determines whether there are more lines to get data from in the 
# temp_name     variable used to add name to 'name' list
# temp_score    variable used to add score to 'score' list
# n             variable used to go through score list to compare players bank to score element
# file_name     variable used to open scoreboard file
def get_scoreboard():
    global bank
    file_name = open('scoreboard.dat','r')
    more_lines = True
    while more_lines:
        temp_name = file_name.readline()
        if temp_name != "":
            temp_score = file_name.readline()
            name.append(temp_name.strip())
            score.append(int(temp_score))
        else:
            more_lines = False
    file_name.close()
    not_added = True
    for n in range(len(score)):
        if bank > int(score[n]) and not_added:
            input_name = tkinter.simpledialog.askstring("Name" , "Please enter your name to be put on the scoreboard")
            if input_name != None:
                name.insert(n,input_name)
                del(name[9])
                score.insert(n,bank)
                del(score[9])
            not_added = False
            
# This function updates the scoreboard file
# file_name  variable to open and edit the scoreboard file
# n          variable used in for loop to write scoreboard file
def update_scoreboard():
    file_name = open('scoreboard.dat','w')
    for n in range(10):
        file_name.write(name[n] + "\n")
        file_name.write(str(score[n]) + "\n")
    file_name.close()

# This function displays a leaderboard for the slot machine
# y     variable that moves down the turtle after writing the last score
# m     variable used in for loop to write scoreboard
def scoreboard():
    turtle.clear()
    turtle.bgpic('redCasino.gif')
    turtle.pu()
    turtle.begin_fill()
    turtle.goto(-300,-350)
    turtle.pencolor("black")
    turtle.fillcolor("white")
    turtle.pd()
    turtle.goto(-300,350)
    turtle.goto(300,350)
    turtle.goto(300,-350)
    turtle.goto(-300,-350)
    turtle.end_fill()
    turtle.pu()
    turtle.goto(-100,300)
    turtle.write("Scoreboard",font=("arial",30,"normal"))
    y = 250
    for m in range(10):
        turtle.goto(-150,y)
        turtle.write(name[m].ljust(15)+str(score[m]).ljust(15),font=("arial",30,"normal"))
        y = y - 50
    turtle.goto(-100,-300)
    turtle.write("Click 'p' to continue",font=("arial",20,"normal"))

#This function asks the user if they would like to save the game and does so if requested
# my_file       file written if user wants to save
# input_save    determines whether the user would like to save or not
def save_game():
    input_save = tkinter.messagebox.askyesno("Save","Would you like to save your money?")
    if input_save == True:
        my_file = tkinter.simpledialog.askstring("Name" , "What name do you want the file under?")
        my_file = open(my_file+'.dat','w')
        my_file.write(str(bank))
        my_file.close()

##Main Program##
import turtle, time, tkinter, random

name = []
score = []
screen = turtle.Screen()
screen.setup(1500, 1000, 0, 0)
screen.title("Slot Machine")
turtle.ht()
label = turtle.Turtle()
label.ht()
label_2 = turtle.Turtle()
label_2.ht()
label_3 = turtle.Turtle()
label_3.ht()
slot = turtle.Turtle()
slot.ht()
instruct = turtle.Turtle()
instruct.ht()
instruct.speed(0)
reading = True
instruction_screen()
screen.onkey(p_key, "p")
screen.listen()
while reading:
    turtle.home()
instruct.clear()
introduction_screen()
turtle.speed(0)
draw_slot_machine()
initialize_slot_machine()
still_going = True
while still_going:
    get_bet()
    if still_going == True:
        screen.onkey(spacebar, "space")
        screen.listen()
        spin_slots()
        determine_win()
        add_bank()
        update_user()
        keep_playing()
        if bank < 5 and still_going == True:
            still_going = tkinter.messagebox.askretrycancel("Bank Error", "You are out of money.")
            if still_going == True:
                bank = 20
                update_user()
get_scoreboard()
update_scoreboard()
scoreboard()
reading = True
screen.onkey(p_key, "p")
screen.listen()
while reading:
    turtle.home()
save_game()
conclusion_screen()
