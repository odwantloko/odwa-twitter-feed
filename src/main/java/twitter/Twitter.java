package twitter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;


public class Twitter {

    // read textfiles
    public static List<String> readFileInList(String fileName) throws IOException {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(fileName), StandardCharsets.UTF_8);

        } catch (IOException e) {
            throw new IOException("\tERROR: An error occured while trying to read "+fileName+"!", e);
        }
        return lines;
    }

    // Create individual TwitterUser objects and return dataset with application users
    public static Map<String, TwitterUser> processUsers(List<String> userTxt){
        Map<String, TwitterUser> applicationUsers = new HashMap<>();

        String firstElement = ""; // first element used to check if the lines in the file are the correct structure
        if (userTxt.size() > 0){
            firstElement = userTxt.get(0);
        }


        if ((userTxt.size() > 0) && firstElement.contains("follows")){
            for (String line: userTxt){
                String formattedLine = line.replace(" follows",",");
                String profile[] = formattedLine.split(", ");

                String username = profile[0];

                TwitterUser mainUser;
                if (!applicationUsers.containsKey(username)){
                    mainUser = new TwitterUser(profile[0], profile[0]);
                    applicationUsers.put(mainUser.getUsername(),mainUser);

                }else{
                    mainUser = applicationUsers.get(username);
                }

                Map<String, TwitterUser> followings = new HashMap<>();
                for(int i = 1; i < profile.length; i++){
                    String usernameKey = profile[i];
                    TwitterUser following; // user being followed
                    if (!applicationUsers.containsKey(usernameKey)){
                        following = new TwitterUser(usernameKey, usernameKey); // create new user
                        applicationUsers.put(usernameKey,following);
                        followings.put(usernameKey, following);

                    }else{
                        following = applicationUsers.get(usernameKey);
                        followings.put(usernameKey,following); // add existing user

                    }

                }

                mainUser.setFollowedUsers(followings);

            }

        }else{
            System.out.println("The user file entered does not seem to have the correct contents. Please check the contents and retry the application. \nIf this is intentional, please feel free to ignore.");
        }

        return applicationUsers;
    }

    //Create Tweet objects and return dataset with all the tweets in the application
    public static List<Tweet> processTweets(List<String> tweets){
        List<Tweet> applicationTweets = new ArrayList<>();

        String firstElement = ""; // first element used to check if the lines in the file are the correct structure
        if (tweets.size() > 0){
            firstElement = tweets.get(0);
        }

        if (tweets.size() > 0 && firstElement.contains(">")){
            int timeIncrement = 0; // time incrementer
            for(String line: tweets){
                String[] userTweet = line.split("> ");

                Date now = new Date();
                now.setTime(now.getTime()+timeIncrement);

                Tweet tweet = new Tweet(userTweet[0], userTweet[1], now);
                applicationTweets.add(tweet);

                timeIncrement+=1000; // increment by 1 second
            }
        } else {
            System.out.println("The tweet file entered does not seem to have the correct contents/any tweets. Please check and retry the application. \nIf this was intended, please ignore this message.");
        }

        return applicationTweets;
    }

    //Link all tweets to their TwitterUsers/Author who published them and returns the updated dataset of users
    public static Map<String, TwitterUser> linkTweetsToUser(List<Tweet> tweets, Map<String, TwitterUser> users){
        Map<String, TwitterUser> linkedUsers = users;
        for (Tweet tweet: tweets){
            if (linkedUsers.get(tweet.getAuthorUsername()) != null){
                TwitterUser tweetOwner = linkedUsers.get(tweet.getAuthorUsername());
                tweetOwner.addToPostedTweets(tweet);
            } else {
                System.out.println("A tweet was created from a user that doesn't exist so it can't be displayed. Please check your input file and try again");
            }

        }

        return linkedUsers;
    }

    //method sorts the data according to when they were posted/processed, helper method to ensure tweets are displayed in order
    public static List<Tweet> sortSingleUserFeedData(List<Tweet> unsortedTweets, List<Tweet> applicationTweets){
        List<Tweet> sortedTweets =  new ArrayList<>();

        // for item in all application tweets, if visible tweets contains item append
        for (Tweet appTweet: applicationTweets){
            if (unsortedTweets.contains(appTweet)){
                sortedTweets.add(appTweet);
            }
        }

        return sortedTweets;

    }

    //method returns a single TwitterUser's UserTwitterFeed that would be display to them only based on who they follow and their own posts
    public static UserTwitterFeed createSingleUserFeedData(TwitterUser user, List<Tweet> applicationTweets){
        List<Tweet> userVisibleTweets = new ArrayList<>();

        userVisibleTweets.addAll(user.getPostedTweets()); //add own tweets to feed
        for(Map.Entry<String, TwitterUser> userEntry: user.getFollowedUsers().entrySet()){
            userVisibleTweets.addAll(userEntry.getValue().getPostedTweets()); //add followed users tweets to feed
        }

        UserTwitterFeed CurrentUserFeed = new UserTwitterFeed(user.getUsername(), sortSingleUserFeedData(userVisibleTweets, applicationTweets));
        return CurrentUserFeed;
    }

    //method to be called to display each feed
    public static void displaySingleUserFeed(UserTwitterFeed feed){
        System.out.println(feed.getUsername());
        feed.displayUserTimelineTweets();

    }

    public static void main(String[] args) throws Exception{
        Map<String, TwitterUser> applicationUsers = new HashMap<>();
        List<Tweet> applicationTweets = new ArrayList<>();
        List<UserTwitterFeed> applicationFeeds = new ArrayList<>();

        List<String> userFileData =  new ArrayList<>();
        List<String> tweetFileData =  new ArrayList<>();


        if (args.length < 2) {
            System.out.println("An error occured - Too little arguments provided. \nPlease enter the user filename followed by the tweets filename separated by a single space\ne.g. user.txt tweet.txt");

        }else{

            try{
                // file reading
                userFileData = readFileInList(args[0]);
                tweetFileData = readFileInList(args[1]);

                applicationUsers = processUsers(userFileData);
                applicationTweets = processTweets(tweetFileData);

                //if ( (applicationUsers.size() > 0)){

                Map<String, TwitterUser>  usersWithTweets = linkTweetsToUser(applicationTweets,applicationUsers); //link tweets to user:
                //}


                for(TwitterUser user: usersWithTweets.values()){ // create application user feeds
                    applicationFeeds.add(createSingleUserFeedData(user, applicationTweets ));
                }

                Collections.sort(applicationFeeds); // Sorts users in alphabetical order

                for(UserTwitterFeed feed: applicationFeeds){ //display feeds
                    displaySingleUserFeed(feed);
                }

            }catch (IOException e){
                System.out.println(e);
                System.out.println("Please ensure the file exists or you've typed the name correctly!");

            }

        }

    }
}
