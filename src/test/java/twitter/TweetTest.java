package twitter;

import org.junit.jupiter.api.DisplayName;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TweetTest {

    @org.junit.jupiter.api.Test
    @DisplayName("Test for setAuthorUsername")
    void setAuthorUsername() {
        Tweet test = new Tweet();
        test.setAuthorUsername("Kevin");
        assertNotNull(test.getAuthorUsername());

    }

    @org.junit.jupiter.api.Test
    void setMessage() {
        Tweet test = new Tweet("Kevin","", new Date());
        test.setMessage("Test");
        assertNotEquals("",test.getMessage());
    }

    @org.junit.jupiter.api.Test
    void setTimestamp() {
        Tweet test = new Tweet("Kevin","First Tweet", null);
        test.setTimestamp(new Date());
        assertNotNull(test.getPostedTime());
    }


    @org.junit.jupiter.api.Test
    @DisplayName("Test 5: getAuthorUsername()")
    void getAuthorUsername() {
        Tweet test = new Tweet("Alan","First Tweet", new Date());
        assertEquals("Alan",test.getAuthorUsername());
    }


    @org.junit.jupiter.api.Test
    @DisplayName("Test 5: getMessage()")
    void getMessage() {
        Tweet test = new Tweet("Alan","First Tweet", new Date());
        assertEquals("First Tweet",test.getMessage());
    }

    @org.junit.jupiter.api.Test
    @DisplayName("Test 6: getPostedTime()")
    void getPostedTime() {
        Date d = new Date();
        Tweet test = new Tweet("Alan","First Tweet", d);
        assertEquals(d,test.getPostedTime());
    }

}