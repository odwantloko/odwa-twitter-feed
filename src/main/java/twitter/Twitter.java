package twitter;

import java.util.*;
import java.io.BufferedReader;
import java.io.FileReader;


public class Twitter {
    public static Map<String, TwitterUser> processUserList(String[] userTxt){
        Map<String, TwitterUser> applicationUsers = new HashMap<>();

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
                TwitterUser following = new TwitterUser(profile[i], profile[i]);
                if (!applicationUsers.containsKey(profile[i])){

                    applicationUsers.put(profile[i],following);

                }

                followings.put(profile[i], following);
            }

            mainUser.setFollowedUsers(followings);

        }

        return applicationUsers;
    }

    public static List<Tweet> processTweets(String[] tweets){
        List<Tweet> applicationTweets = new ArrayList<>();

        int timeIncrement = 0; // time incrementer
        for(String line: tweets){
            String[] userTweet = line.split("> ");

            Date now = new Date();
            now.setTime(now.getTime()+timeIncrement);

            Tweet tweet = new Tweet(userTweet[0], userTweet[1], now);
            applicationTweets.add(tweet);

            timeIncrement+=1000; // increment by 1 second
        }
        return applicationTweets;
    }

    public static Map<String, TwitterUser> linkTweetsToUser(List<Tweet> tweets, Map<String, TwitterUser> users){
        Map<String, TwitterUser> linkedUsers = users;
        for (Tweet tweet: tweets){
            TwitterUser tweetOwner = linkedUsers.get(tweet.getAuthorUsername());
            tweetOwner.addToPostedTweets(tweet);
        }

        return linkedUsers;
    }
    //method sorts the data according to when they were posted/processed
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

    //method returns the user's data sorted according to time processed/posted
    public static UserTwitterFeed createSingleUserFeedData(TwitterUser user, List<Tweet> applicationTweets){
        List<Tweet> userVisibleTweets = new ArrayList<>();

        userVisibleTweets.addAll(user.getPostedTweets()); //add own tweets to feed
        for(Map.Entry<String, TwitterUser> followed: user.getFollowedUsers().entrySet()){
            userVisibleTweets.addAll(followed.getValue().getPostedTweets()); //add followed users tweets to feed
        }

        UserTwitterFeed CurrentUserFeed = new UserTwitterFeed(user.getUsername(), sortSingleUserFeedData(userVisibleTweets, applicationTweets));
        return CurrentUserFeed;
    }

    //method to be called to display each feed
    public static void displaySingleUserFeed(UserTwitterFeed feed){
        System.out.println(feed.getUsername());
        feed.displayUserTimelineTweets();

    }

    public static void main(String[] args) {
        Map<String, TwitterUser> applicationUsers = new HashMap<>();
        List<Tweet> applicationTweets = new ArrayList<>();
        List<UserTwitterFeed> applicationFeeds = new ArrayList<>();

        String userTxt[] = {
                "Ward follows Alan",
                "Alan follows Martin",
                "Ward follows Martin, Steve, Alan"
        };//read file 1
        String tweets[] = {
                "Alan> If you have a procedure with 10 parameters, you probably missed some."
                , "Ward> There are only two hard things in Computer Science: cache invalidation, naming things and off-by-1 errors."
                , "Steve> Fuck"
                , "Alan> Random numbers should not be generated with a method chosen at random."
        };//read file 2

        if (args.length < 2) {
            System.out.println("Too little arguments provided. Please enter the user file name followed by the tweets file name separated by space");
        }


        applicationUsers = processUserList(userTxt);
        applicationTweets = processTweets(tweets);

        //link tweets to user:
        applicationUsers = linkTweetsToUser(applicationTweets,applicationUsers);


        // create application feeds
        for(TwitterUser user: applicationUsers.values()){
            applicationFeeds.add(createSingleUserFeedData(user, applicationTweets ));
        }

        // sort
        Collections.sort(applicationFeeds);
        //display fields
        for(UserTwitterFeed feed: applicationFeeds){
            displaySingleUserFeed(feed);
        }

    }
}
