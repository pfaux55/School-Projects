# AnalyzeSentiment
## Author
Peter Faux
## Description
This project provides a sentiment analysis tool for analyzing the sentiment of tweets. It reads keywords and their sentiment values from a TSV file, processes tweets from a CSV file, and generates a report with various sentiment metrics.

## Requirements

- Python 3.x installed on your system

## Project Structure

- **sentiment_analysis.py**: Contains functions for reading keywords, cleaning tweet text, calculating sentiment, classifying sentiment, reading tweets, generating reports, and writing reports.
- **main.py**: Prompts the user for input files, calls functions from `sentiment_analysis.py` to perform sentiment analysis, and writes the report to a text file.
- **adidas.csv**: Example CSV file containing tweets and their metadata.
- **keywords.tsv**: Example TSV file containing keywords and their sentiment values.

## How to Use

### Running the Program

1. Open a terminal and navigate to the directory containing the project files.
2. Run the program using the following command:

   ```bash
   python main.py
3. Follow the prompts to input the keyword file (TSV), tweet file (CSV), and output report file (TXT).

### Example Interaction


   Input keyword filename (.tsv file): keywords.tsv
   Input tweet filename (.csv file): adidas.csv
   Input filename to output report in (.txt file): report.txt
   Wrote report to report.txt

### How it Works

#### Reading Keywords:
Function: read_keywords
Process:
Accepts a TSV file name as input.
Opens the file and reads its contents line by line.
Creates a dictionary with keywords as keys and their assigned sentiment values as values.

#### Cleaning Tweet Text:
Function: clean_tweet_text
Process:
Accepts a tweet text string as input.
Converts the text to lowercase and removes non-alphabetic characters and spaces.
Returns the cleaned text.

#### Calculating Sentiment:
Function: calc_sentiment
Process:
Accepts a cleaned tweet text and the keyword dictionary as inputs.
Splits the tweet text into individual words.
Calculates the sentiment score by summing the values of the words found in the keyword dictionary.
Returns the total sentiment score.

#### Classifying Sentiment:
Function: classify
Process:
Accepts a sentiment score as input.
Classifies the tweet as 'positive' if the score is greater than 0, 'neutral' if the score is 0, and 'negative' if the score is less than 0.
Returns the classification.

#### Reading Tweets:
Function: read_tweets
Process:
Accepts a CSV file name as input.
Opens the file and reads its contents line by line.
Creates a list of dictionaries, each representing a tweet with its metadata (date, text, user, retweet count, favorite count, etc.).
Cleans the tweet text using clean_tweet_text.
Returns the list of tweet dictionaries.

#### Generating Report:
Function: make_report
Process:
Accepts the list of tweet dictionaries and the keyword dictionary as inputs.
Calculates various statistics, such as average sentiment score, total number of tweets, counts of positive, negative, and neutral tweets, and average sentiment scores for favorited and retweeted tweets.
Calculates sentiment scores and classifications for each tweet.
Aggregates sentiment scores by country.
Identifies the top five countries by average sentiment score.
Returns a report dictionary containing all calculated values.

#### Writing Report:
Function: write_report
Process:
Accepts the report dictionary and the output file name as inputs.
Writes the report's content to the specified text file.
Prints a confirmation message upon successful writing.

#### Main Function:
File: main.py
Process:
Prompts the user for input filenames for the keyword TSV file and tweet CSV file.
Reads the keywords and tweets using read_keywords and read_tweets.
Generates a report using make_report.
Prompts the user for an output filename for the report.
Writes the report to the specified file using write_report.

