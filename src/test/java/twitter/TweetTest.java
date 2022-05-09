package twitter;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class TweetTest {

    @Test
    @DisplayName("Test 1: setAuthorUsername()")
    void setAuthorUsername() {
        Tweet test = new Tweet();
        test.setAuthorUsername("Kevin");
        assertNotNull(test.getAuthorUsername());

    }

    @Test
    @DisplayName("Test 2: setMessage()")
    void setMessage() {
        Tweet test = new Tweet("Kevin","", new Date());
        test.setMessage("Test");
        assertNotEquals("",test.getMessage());
    }

    @Test
    @DisplayName("Test 3: setTimestamp()")
    void setTimestamp() {
        Tweet test = new Tweet("Kevin","First Tweet", null);
        test.setTimestamp(new Date());
        assertNotNull(test.getPostedTime());
    }


    @Test
    @DisplayName("Test 4: getAuthorUsername()")
    void getAuthorUsername() {
        Tweet test = new Tweet("Alan","First Tweet", new Date());
        assertEquals("Alan",test.getAuthorUsername());
    }


    @Test
    @DisplayName("Test 5: getMessage()")
    void getMessage() {
        Tweet test = new Tweet("Alan","First Tweet", new Date());
        assertEquals("First Tweet",test.getMessage());
    }

    @Test
    @DisplayName("Test 6: getPostedTime()")
    void getPostedTime() {
        Date d = new Date();
        Tweet test = new Tweet("Alan","First Tweet", d);
        assertEquals(d,test.getPostedTime());
    }

}