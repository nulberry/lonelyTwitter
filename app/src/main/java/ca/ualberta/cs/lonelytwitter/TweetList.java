package ca.ualberta.cs.lonelytwitter;

import java.util.ArrayList;

public class TweetList {
    private final ArrayList<Tweet> tweets = new ArrayList<Tweet>(); // declaration could have final modifier

    // made access to methods package-private because declaration access could be weaker
    TweetList(){

    }

    Tweet getTweet(int index){
        return tweets.get(index);
    }

    boolean hasTweet(Tweet tweet){
        return tweets.contains(tweet);
    }

    void add(Tweet tweet) {
        tweets.add(tweet);
    }

    void delete(Tweet tweet) {
        tweets.remove(tweet);
    }
}
