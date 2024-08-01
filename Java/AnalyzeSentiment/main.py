#############################################################################################################
# Peter Faux
# 251363513
# pfaux
# November, 9 2023
# This file prompts the user to input a tsv file containing keywords and their sentimental values, and then
# a csv file containing tweets and metadata for the tweets and generates metadata for the entire tweet file
# including average sentiment value for favourited tweets and retweeted tweets.
#############################################################################################################
from sentiment_analysis import *


# The main function goes through the process of the program, calling functions from sentiment_analysis.py
# to carryout tasks
def main():
    # key_file_name is a variable for the name of the keyword file being opened
    key_file_name = input("Input keyword filename (.tsv file): ")
    if ".tsv" not in key_file_name:
        raise IOError("Must have tsv file extension")
    # keyword_dict is a dictionary containing the values for all the keywords obtained from read_keywords()
    keyword_dict = read_keywords(key_file_name)
    if keyword_dict == {}:
        raise Exception("Tweet List or keyword dictionary is empty!")
    # tweet_file_name is a variable for the name of the tweet file being opened
    tweet_file_name = input("Input tweet filename (.csv file): ")
    if ".csv" not in tweet_file_name:
        raise IOError("Must have csv file extension")
    # tweets_list is a list of dictionaries for all the metadata obtained from read_tweets
    tweets_list = read_tweets(tweet_file_name)
    if not tweets_list:
        raise Exception("Tweet List or keyword dictionary is empty!")
    # report is a dictionary obtained from make_report() that is used to write the report file
    report = make_report(tweets_list, keyword_dict)
    report_file_name = input("Input filename to output report in (.txt file): ")
    if ".txt" not in report_file_name:
        raise IOError("Must have txt file extension")
    write_report(report, report_file_name)


main()
