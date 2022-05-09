package twitter;

import java.util.*;

public class TwitterUser implements Person{
    private String username;
    private String name;
    private List<Tweet> postedTweets = new ArrayList<>();
    private Map<String, TwitterUser> followedUsers = new HashMap<>();

    public TwitterUser(){}

    public TwitterUser(String username, String name){
        this.username = username;
        this.name = name;
    }

    public TwitterUser(String username, String name, List<Tweet> tweets, Map<String, TwitterUser> follows){
        this.username = username;
        this.name = name;
        this.postedTweets = tweets;
        this.followedUsers = follows;
    }

    @Override
    public void setUsername(String username){
        this.username = username;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUsername(){
        return this.username;
    }

    public String getName(){
        return this.name;
    }

    public void setPostedTweets(List<Tweet> tweets){
        this.postedTweets = tweets;
    }

    public void addToPostedTweets(Tweet tweet){
        this.postedTweets.add(tweet);
    }

    public void setFollowedUsers(Map<String, TwitterUser> follows){
        for (Map.Entry<String,TwitterUser> entry : follows.entrySet()){
            if (!this.followedUsers.containsKey(entry.getKey())){
                this.followedUsers.put(entry.getKey(), entry.getValue());
            }
        }
    }

    public void addSingleFollowedUser(TwitterUser follows){
        this.followedUsers.put(follows.getUsername(), follows);
    }

    public List<Tweet> getPostedTweets(){
        return this.postedTweets;
    }

    public Map<String, TwitterUser> getFollowedUsers(){
        return this.followedUsers;
    }

}
