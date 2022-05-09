package twitter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class UserTwitterFeedTest {

    @Test
    @DisplayName("Test 1: setUsername()")
    void setUsername() {
        UserTwitterFeed feed = new UserTwitterFeed();
        feed.setUsername("Odwa");
        assertNotEquals("",feed.getUsername());
    }

    @Test
    @DisplayName("Test 2: setVisible()")
    void setVisibleTweets() {
        TwitterUser user = new TwitterUser("Odwa","Odwa");

        Tweet test = new Tweet("Odwa","First Tweet", new Date());
        user.addToPostedTweets(test);

        UserTwitterFeed feed = new UserTwitterFeed("Odwa");
        feed.setVisibleTweets(user.getPostedTweets());

        assertNotNull(feed.getVisibleTweets());
        assertEquals(1, feed.getVisibleTweets().size());


    }

    @Test
    @DisplayName("Test 3: getUsername()")
    void getUsername() {
        UserTwitterFeed feed = new UserTwitterFeed("Odwa");
        assertEquals("Odwa",feed.getUsername());
    }

    @Test
    @DisplayName("Test 4: getVisibleTweets()")
    void getVisibleTweets() {
        TwitterUser user = new TwitterUser("Odwa","Odwa");

        Tweet test = new Tweet("Odwa","First Tweet", new Date());
        user.addToPostedTweets(test);

        UserTwitterFeed feed = new UserTwitterFeed("Odwa");
        feed.setVisibleTweets(user.getPostedTweets());

        assertEquals("First Tweet", feed.getVisibleTweets().get(0).getMessage());
    }



}