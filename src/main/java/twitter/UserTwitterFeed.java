package twitter;

import java.util.List;

public class UserTwitterFeed implements Comparable<UserTwitterFeed>{
    private String username;
    private List<Tweet> visibleTweets;

    public UserTwitterFeed(){}

    public UserTwitterFeed(String username){
        this.username = username;
    }

    public UserTwitterFeed(String username, List<Tweet> tweets){
        this.username = username;
        this.visibleTweets = tweets;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public void setVisibleTweets(List<Tweet> tweets){
        this.visibleTweets = tweets;
    }


    public String getUsername(){
        return this.username;
    }

    public List<Tweet> getVisibleTweets(){
        return this.visibleTweets;
    }

    public void displayUserTimelineTweets(){
        for (Tweet tweet : visibleTweets){
            System.out.println("\t@"+tweet.getAuthorUsername()+": "+ tweet.getMessage());
        }
    }


    @Override
    public int compareTo(UserTwitterFeed o) {
        return this.getUsername().compareTo(o.getUsername());
    }
}
