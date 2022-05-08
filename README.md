# odwa-twitter-feed
This is the repo for the twitter feed assignment.

# To run this assignment the following prerequesites/recommendations apply:

1. IntelliJ IDE or any IDE with maven build support
3. Ensure that the input files are stored in the root directory of the project or enter the full path as arguments 
3. This application was built with openjdk version "17.0.3" 

# How to run:
enter the file names as command line arguments starting with the user file then the tweets file

e.g. 
user.txt tweet.txt

If you are using intelliJ, edit the run/debug configuration and add the arguments to the input field named: "CLI arguments to your application"

# Assumptions made:
1. The list of users that a person "follows" will always have a space after the comma e.g. "Ward follows Martin, Alan"
2. No 2 users will have the same name spelling in the file. Username is assumed to be the id that links different classes/potential tables
3. Processing time was too quick in milisecond to tell which tweet was create first, so I'm incrementing the time manually for the purpose of this assignment
4. The system allows users to edit their tweets



