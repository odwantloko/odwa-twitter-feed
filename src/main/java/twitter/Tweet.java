package twitter;

import java.util.Date;
public class Tweet {
    private String authorUsername;
    private String message;
    private Date timestamp;

    public Tweet(){}

    public Tweet(String author, String message, Date timestamp){
        this.authorUsername = author;
        this.message = message;
        this.timestamp = timestamp;
    }

    public void setAuthorUsername(String authorUsername){
        this.authorUsername = authorUsername;
    }

    public void setMessage(String message){
        this.message = message;
    }

    public void setTimestamp(Date timestamp){
        this.timestamp = timestamp;
    }

    public String getAuthorUsername(){
        return this.authorUsername;
    }

    public String getMessage(){
        return this.message;
    }

    public Date getPostedTime(){
        return this.timestamp;
    }

    public void displayTweet(){
        System.out.println("@"+this.authorUsername+": "+ this.message);
    }
}
