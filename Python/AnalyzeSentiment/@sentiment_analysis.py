#############################################################################################################
# Peter Faux
# 
# pfaux
# November, 9 2023
# This file calculates sentimental values from the files provided, and then
# generates metadata for the entire tweet file including average sentiment
# value for favourited tweets and retweeted tweets. Then it writes a .txt
# file with the metadata
#############################################################################################################


# read_keywords() accepts a variable as a tsv keyword file name and opens the file and creates a dictionary with the
# keywords and their assigned values
# keyword_file_name represents the name of the file being read
def read_keywords(keyword_file_name):
    # this function will read the file containing the values for each word and return a dictionary with all the words
    # and values keywordValues is a dictionary that stores the keywords and the value that each keyword has
    keyword_dict = {}
    try:
        # new_file is the variable for the file being opened containing keywords and values
        new_file = open(keyword_file_name, "r")
        # lines is a list of all the lines in the file
        lines = new_file.readlines()
        for line in lines:
            if line != "":
                # newWordAndValue is the list being created with the next word and value of the word being read
                line = line.strip("\n")
                new_word_and_value = line.split("\t")
                # newWord is the word being read on the next line which will be the first value in the
                # newWordAndValue list
                new_word = new_word_and_value[0]
                # newValue is the value being read on the next line which will be the second value in the
                # newWordAndValue list
                new_value = new_word_and_value[1]
                keyword_dict[new_word] = int(new_value)
        new_file.close()
    except IOError:
        print("Could not open file", keyword_file_name + "!")

    return keyword_dict


# clean_tweet_text() accepts a tweet from tweets_list and makes it lower case and removes everything from the text
# other than letters and spaces
# tweet_text represents the tweet text from the tweets_list being cleaned
def clean_tweet_text(tweet_text):
    return ''.join(char for char in tweet_text.lower() if char.isalpha() or char.isspace())


# calc_sentiment accepts a tweet from tweets_list and calculates its numerical sentiment value using the keyword
# dictionary
# tweet_text represents the tweet text from the tweets_list being evaluated
def calc_sentiment(tweet_text, keyword_dict):
    # words is a list that stores all the words in the tweet being calculated separately
    words = tweet_text.split()
    # score is the integer variable that stores the sentiment value the tweet accumulates
    score = 0
    # word is the string for the word currently being checked in the list words
    for word in words:
        if word in keyword_dict:
            score = score + keyword_dict[word]

    return score


# classify() accepts the sentimental score from calc_sentiment() and determines whether the tweet is positive
# neutral or negative
# score represents the sentimental score evaluated from calc_sentiment()
def classify(score):
    if score > 0:
        return 'positive'
    elif score == 0:
        return 'neutral'
    else:
        return 'negative'


# read_tweets() accepts a variable as a csv keyword file name and opens the file and creates a list of dictionaries
# with the tweet file provided
# tweet_file_name represents the name of the tweet file being read
def read_tweets(tweet_file_name):
    # tweets_list is a list of all the dictionaries of all the tweets
    tweets_list = []
    # current tweet info is a dictionary that stores the information of the current tweet being added to tweets_list
    current_tweet_info = {}
    # tweet_keys is a list of the keywords for current_tweet_info
    tweet_keys = ['date', 'text', 'user', 'retweet', 'favorite', 'lang', 'country',
                  'state', 'city', 'lat', 'lon']
    try:
        # new_file is the variable for the file to be read containing the tweets
        new_file = open(tweet_file_name, "r")
        # lines is a list for all the lines in the file
        lines = new_file.readlines()
        # line is a string variable for the new line being read in the file
        for line in lines:
            if line != "":
                line = line.strip("\n")
                # tweet_info is a list of the values from the new line being read that are split by a comma
                tweet_info = line.split(",")
                for cur_info in range(len(tweet_info)):
                    # if the loop is on the tweet text value, the text will be 'cleaned' and the value within
                    # tweet_info will be replaced with clean text
                    if cur_info == 1:
                        tweet_info[cur_info] = clean_tweet_text(tweet_info[cur_info])
                        current_tweet_info[tweet_keys[cur_info]] = tweet_info[cur_info]
                    elif cur_info == 3 or cur_info == 4:
                        current_tweet_info[tweet_keys[cur_info]] = int(tweet_info[cur_info])
                    elif (cur_info == 9 or cur_info == 10) and (tweet_info[cur_info] != "NULL"):
                        current_tweet_info[tweet_keys[cur_info]] = float(tweet_info[cur_info])
                    else:
                        current_tweet_info[tweet_keys[cur_info]] = tweet_info[cur_info]

                tweets_list.append(current_tweet_info)
                current_tweet_info = {}

        new_file.close()
    except IOError:
        print("Could not open file", tweet_file_name)

    return tweets_list


# make_report() creates a report on the average and accumulation values from the tweets from the keyword dictionary
# tweet_list represents the list of dictionaries of all the tweets and their individual information
# keyword_dict represents the dictionary of keywords and their sentimental value
def make_report(tweet_list, keyword_dict):
    # avg_score is a float variable for the average sentiment score for all the tweets
    avg_score = float(0)
    # total_score is a variable to keep track of the total sentiment score for all tweets used to calculate average
    total_score = 0
    # total tweets is a variable to keep track of the total amount of tweets in the tweet dictionary reported
    total_tweets = 0
    # pos_tweets is a variable to keep track of the total amount of positive tweets in the tweet dictionary reported
    pos_tweets = 0
    # neg_tweets is a variable to keep track of the total amount of negative tweets in the tweet dictionary reported
    neg_tweets = 0
    # neut_tweets is a variable to keep track of the total amount of neutral tweets in the tweet dictionary reported
    neut_tweets = 0
    # liked_tweets is a variable to keep track of the total amount of liked tweets in the tweet dictionary reported
    liked_tweets = 0
    # total_liked_score is a variable to keep track of total sentiment score to calculate average of liked tweets
    # sentiment score
    total_liked_score = 0
    # avg_liked_score is a float variable for the average sentiment score for all the liked tweets
    avg_liked_score = float(0)
    # total_retweeted is a variable to keep track of the total amount of retweeted tweets in the tweet dictionary
    # reported
    total_retweeted = 0
    # total_retweeted_score is a variable to keep track of total sentiment score to calculate average of liked tweets
    # sentiment score
    total_retweeted_score = 0
    # average_retweets is a float variable for the average sentiment score for all the retweeted tweets
    average_retweets = float(0)
    # country_score is a dictionary that contains a dictionary for each country's total score and each country's
    # amount of tweets
    country_score = {}
    for tweet_dict in tweet_list:
        total_tweets += 1
        tweet_score = calc_sentiment(tweet_dict['text'], keyword_dict)
        total_score += tweet_score
        tweet_rating = classify(tweet_score)
        if tweet_rating == 'positive':
            pos_tweets += 1
        elif tweet_rating == 'neutral':
            neut_tweets += 1
        else:
            neg_tweets += 1

        if tweet_dict['favorite'] > 0 and tweet_dict['favorite'] != 'NULL':
            liked_tweets += 1
            total_liked_score += tweet_score

        if tweet_dict['retweet'] > 0 and tweet_dict['retweet'] != 'NULL':
            total_retweeted += 1
            total_retweeted_score += tweet_score

        # creating a new dictionary for a new country found
        if tweet_dict['country'] != 'NULL' and tweet_dict['country'] not in country_score:
            country_score[tweet_dict['country']] = {'total score': tweet_score, 'occurrences': 1}
        # adding to the dictionary for the country that tweet is from
        elif tweet_dict['country'] != 'NULL' and tweet_dict['country'] in country_score:
            country_score[tweet_dict['country']]['total score'] += tweet_score
            country_score[tweet_dict['country']]['occurrences'] += 1

    if total_liked_score > 0:
        avg_liked_score = round(total_liked_score / liked_tweets, 2)
    elif total_liked_score == 0:
        avg_liked_score = 0
    elif total_liked_score < 0:
        avg_liked_score = round(total_liked_score / liked_tweets, 2)
    else:
        avg_liked_score = "NAN"

    if total_retweeted_score > 0:
        average_retweets = round(total_retweeted_score / total_retweeted, 2)
    elif total_retweeted_score == 0:
        average_retweets = 0
    elif total_retweeted_score < 0:
        average_retweets = round(total_retweeted_score / total_retweeted, 2)
    else:
        average_retweets = "NAN"

    # country_averages is a dictionary containing the averages for each country
    country_averages = {}
    # average_score is a variable for the average sentiment score for each country
    for country in country_score:
        average_score = round(country_score[country]['total score'] /
                              country_score[country]['occurrences'], 2)
        country_averages[country] = average_score

    sorted_country_score = dict(sorted(country_averages.items(), key=lambda item: item[1], reverse=True))

    # top_countries is a string that contains the top 5 countries by sentiment average
    top_five_countries = list(sorted_country_score.keys())[:5]
    top_countries = ",".join(top_five_countries)

    avg_score = round(total_score / total_tweets, 2)
    # report is the dictionary that contains all the values for all the tweets in the dataset
    report = {'avg_favorite': avg_liked_score, 'avg_retweet': average_retweets, 'avg_sentiment': avg_score,
              'num_favorite': liked_tweets, 'num_negative': neg_tweets, 'num_neutral': neut_tweets,
              'num_positive': pos_tweets, 'num_retweet': total_retweeted, 'num_tweets': total_tweets,
              'top_five': top_countries}

    return report


# write_report() writes a .txt file with the average and accumulation values from make_report()
# report represents the dictionary containing the average and accumulation values of the tweets
# output_file represents the name of the .txt file being written
def write_report(report, output_file):
    try:
        new_file = open(output_file, "w")
        new_file.write("Average sentiment of all tweets:" + str(report['avg_sentiment']) + "\n")
        new_file.write("Total number of tweets:" + str(report['num_tweets']) + "\n")
        new_file.write("Number of positive tweets:" + str(report['num_positive']) + "\n")
        new_file.write("Number of negative tweets:" + str(report['num_negative']) + "\n")
        new_file.write("Number of neutral tweets:" + str(report['num_neutral']) + "\n")
        new_file.write("Number of favorited tweets:" + str(report['num_favorite']) + "\n")
        new_file.write("Average sentiment of favorited tweets:" + str(report['avg_favorite']) + "\n")
        new_file.write("Number of retweeted tweets:" + str(report['num_retweet']) + "\n")
        new_file.write("Average sentiment of retweeted tweets:" + str(report['avg_retweet']) + "\n")
        new_file.write("Top five countries by average sentiment:" + report['top_five'])

        print("Wrote report to", output_file)
        new_file.close()
    except IOError:
        print("Could not open file", output_file)
