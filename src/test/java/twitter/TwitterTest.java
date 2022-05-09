package twitter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TwitterTest {

    @Test
    @DisplayName("Test 1: processUsers()")
    void processUsers() {
        String line = "Ward follows Martin, Alan";
        List<String> fileLine = new ArrayList<>();
        fileLine.add(line);
        Map<String, TwitterUser> users =  Twitter.processUsers(fileLine);
        assertEquals("Ward", users.get("Ward").getUsername());

    }

    @Test
    @DisplayName("Test 2: processTweets()")
    void processTweets() {
        String line = "Alan> Random numbers should not be generated with a method chosen at random.";
        List<String> fileLine = new ArrayList<>();
        fileLine.add(line);
        List<Tweet> tweets =  Twitter.processTweets(fileLine);
        assertEquals(1, tweets.size());
        assertEquals("Random numbers should not be generated with a method chosen at random.", tweets.get(0).getMessage());

    }

    @Test
    @DisplayName("Test 3: linkTweetsToUser()")
    void linkTweetsToUser() {
        TwitterUser user = new TwitterUser("Odwa","Odwa"); // create app users
        Map<String, TwitterUser> appUsers = new HashMap<>();
        appUsers.put("Odwa", user);

        Tweet tweet = new Tweet("Odwa", "My first tweet", new Date()); // create app tweets
        List<Tweet> appTweets = new ArrayList<>();
        appTweets.add(tweet);

        Map<String, TwitterUser> linkedResult = Twitter.linkTweetsToUser(appTweets, appUsers);
        TwitterUser linkedUser = linkedResult.get("Odwa");

        assertEquals(1, linkedUser.getPostedTweets().size());
        assertEquals("My first tweet", linkedUser.getPostedTweets().get(0).getMessage());


    }

    @Test
    @DisplayName("Test 4: sortSingleUserFeedData()")
    void sortSingleUserFeedData() {
        TwitterUser user = new TwitterUser("Odwa","Odwa"); // create app users
        Map<String, TwitterUser> appUsers = new HashMap<>();
        appUsers.put("Odwa", user);

        Tweet first = new Tweet("Odwa", "My first tweet", new Date()); // create app tweets
        Tweet second = new Tweet("Odwa", "My second tweet", new Date()); // create app tweets
        List<Tweet> appTweets = new ArrayList<>();
        appTweets.add(first);
        appTweets.add(second);


        Map<String, TwitterUser> linkedResult = Twitter.linkTweetsToUser(appTweets, appUsers);
        TwitterUser linkedUser = linkedResult.get("Odwa");

        List<Tweet> sorted = Twitter.sortSingleUserFeedData(linkedUser.getPostedTweets(), appTweets);
        assertEquals("My first tweet", sorted.get(0).getMessage());

    }

    @Test
    @DisplayName("Test 5: createSingleUserFeedData()")
    void createSingleUserFeedData() {
        TwitterUser user = new TwitterUser("Odwa","Odwa"); // create app users
        TwitterUser otherUser = new TwitterUser("Nelson","Nelson");
        Map<String, TwitterUser> appUsers = new HashMap<>();
        appUsers.put("Odwa", user);
        appUsers.put("Nelson", otherUser);


        Tweet first = new Tweet("Odwa", "My first tweet", new Date()); // create app tweets
        Tweet other = new Tweet("Nelson", "My tweet is not related", new Date());
        Tweet second = new Tweet("Odwa", "My second tweet", new Date());
        List<Tweet> appTweets = new ArrayList<>();
        appTweets.add(first);
        appTweets.add(other);
        appTweets.add(second);


        Map<String, TwitterUser> linkedResult = Twitter.linkTweetsToUser(appTweets, appUsers);

        UserTwitterFeed feed = Twitter.createSingleUserFeedData(linkedResult.get("Odwa"), appTweets);

        assertEquals("My second tweet", feed.getVisibleTweets().get(1).getMessage());


    }



}