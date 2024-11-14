# odwa-twitter-feed
This is a repo for a twitter feed assignment I was given as part of a job interview process. I was allowed to choose any programming language to do the assignment in and I chose to do it in java.

This was because:
- I was a java tutor in university so it was the programming language I felt the most comfortable with considering the limited time I had
- I learnt all my DataStructures and algorithm courses in java so I knew the best data structures to use if I were to be asked to opmtomize the solution (which I was).
- Java just has a special place in my heart even though I have only used it for the first 2 years of my career. This is mainly due to some C & C++ university trauma lol!

# To run this assignment the following prerequesites/recommendations apply:

1. IntelliJ IDE or any IDE with maven build support
3. Ensure that the input files are stored in the root directory of the project or enter the full path as arguments 
3. This application was built with openjdk version "17.0.3" 

# How to run:
1. mvn clean
2. mvn install
3. enter the file names as command line arguments starting with the user file then the tweets file

e.g. 
user.txt tweet.txt

If you are using intelliJ, edit the run/debug configuration and add the arguments to the input field named: "CLI arguments to your application"

# Assumptions made:
1. The list of users that a person "follows" will always have a space after the comma e.g. "Ward follows Martin, Alan"
2. No 2 users will have the same name spelling in the file. Username is assumed to be the id that links different classes/potential tables
3. Processing time was too quick in milisecond to tell which tweet was create first, so I'm incrementing the time manually for the purpose of this assignment
4. The system allows users to edit their tweets



