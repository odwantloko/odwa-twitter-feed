package twitter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class TwitterUserTest {

    @Test
    @DisplayName("Test 1: setUsername()")
    void setUsername() {
        TwitterUser user = new TwitterUser();
        user.setUsername("Odwa");
        assertNotNull(user.getUsername());
    }

    @Test
    @DisplayName("Test 2 setName()")
    void setName() {
        TwitterUser user = new TwitterUser();
        user.setUsername("Odwa");
        assertNotNull(user.getName());
    }

    @Test
    @DisplayName("Test 3 getUsername()")
    void getUsername() {
        TwitterUser user = new TwitterUser("Odwa_123","Odwa");
        assertEquals("Odwa_123", user.getUsername());
    }

    @Test
    @DisplayName("Test 4 getName()")
    void getName() {
        TwitterUser user = new TwitterUser("Odwa_123","Odwa");
        assertEquals("Odwa", user.getName());
    }

    @Test
    @DisplayName("Test 5 setPostedTweets()")
    void setPostedTweets() {
        TwitterUser user = new TwitterUser("Odwa_123","Odwa");
        List<Tweet> tweets = new ArrayList<>();
        Tweet firstTweet = new Tweet("Odwa_123", "This is my first tweet!", new Date());
        Tweet secondTweet = new Tweet("Odwa_123", "This is my second tweet!", new Date());
        tweets.add(firstTweet);
        tweets.add(secondTweet);
        user.setPostedTweets(tweets);
        assertEquals(2, user.getPostedTweets().size());

    }

    @Test
    @DisplayName("Test 6 addToPostedTweets()")
    void addToPostedTweets() {
        TwitterUser user = new TwitterUser("Odwa_123","Odwa");
        Tweet firstTweet = new Tweet("Odwa_123", "This is my first tweet!", new Date());
        user.addToPostedTweets(firstTweet);
        assertEquals(1, user.getPostedTweets().size());
    }

    @Test
    @DisplayName("Test 7 setFollowedUsers()")
    void setFollowedUsers() {
        TwitterUser user = new TwitterUser("Odwa_123","Odwa");
        TwitterUser followed = new TwitterUser("Nelson", "Nelson");
        Map<String, TwitterUser> followList = new HashMap<>();
        followList.put("Nelson", followed);
        user.setFollowedUsers(followList);
        assertNotNull(user.getFollowedUsers());
        assertEquals(1, user.getFollowedUsers().size());

    }

    @Test
    @DisplayName("Test 8 addSingleFollowedUser()")
    void addSingleFollowedUser() {
        TwitterUser user = new TwitterUser("Odwa_123","Odwa");
        TwitterUser followed = new TwitterUser("Nelson", "Nelson");
        user.addSingleFollowedUser(followed);
        assertNotNull(user.getFollowedUsers());
        assertEquals(1, user.getFollowedUsers().size());
    }

    @Test
    @DisplayName("Test  getPostedTweets()")
    void getPostedTweets() {
        TwitterUser user = new TwitterUser("Odwa_123","Odwa");
        Tweet firstTweet = new Tweet("Odwa_123", "This is my first tweet!", new Date());
        user.addToPostedTweets(firstTweet);
        assertEquals("This is my first tweet!", user.getPostedTweets().get(0).getMessage());
    }

    @Test
    @DisplayName("Test  getFollowedUsers()")
    void getFollowedUsers() {
        TwitterUser user = new TwitterUser("Odwa_123","Odwa");
        TwitterUser followed = new TwitterUser("Nelson", "Nelson");
        user.addSingleFollowedUser(followed);
        assertNotNull(user.getFollowedUsers());
        assertEquals(1, user.getFollowedUsers().size());
    }
}