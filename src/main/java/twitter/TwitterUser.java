package twitter;

import java.util.*;

public class TwitterUser extends Person{
    private List<Tweet> postedTweets = new ArrayList<>();
    private Map<String, TwitterUser> followedUsers = new HashMap<>();

    public TwitterUser(){}

    public TwitterUser(String username, String name){
        super(username, name);
    }

    public TwitterUser(String username, String name, List<Tweet> tweets, Map<String, TwitterUser> follows){
        super(username, name);
        this.postedTweets = tweets;
        this.followedUsers = follows;
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

    public void addSingleFollowedUse(TwitterUser follows){
        this.followedUsers.put(follows.getUsername(), follows);
    }

    public List<Tweet> getPostedTweets(){
        return this.postedTweets;
    }

    public Map<String, TwitterUser> getFollowedUsers(){
        return this.followedUsers;
    }

}
