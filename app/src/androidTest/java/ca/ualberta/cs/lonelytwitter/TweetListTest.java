package ca.ualberta.cs.lonelytwitter;

import android.test.ActivityInstrumentationTestCase2;

import java.util.ArrayList;

/**
 * Created by amwhitta on 10/12/17.
 */

public class TweetListTest extends ActivityInstrumentationTestCase2 {

    public TweetListTest() {
        super(ca.ualberta.cs.lonelytwitter.LonelyTwitterActivity.class);
    }

    public void testAddTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet1 = new NormalTweet("adding Tweet");
        tweets.addTweet(tweet1);
        Tweet tweet2 = new NormalTweet("adding Tweet");
        try {
            tweets.addTweet(tweet2);
        } catch (IllegalArgumentException e) {
            assertNotNull(e);
        }
        assertTrue(tweets.hasTweet(tweet1));
    }

    public void testDelete() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("test delete");
        tweets.addTweet(tweet);
        tweets.deleteTweet(tweet);
        assertFalse(tweets.hasTweet(tweet));
    }

    public void testHasTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("has Tweet");
        tweets.addTweet(tweet);
        assertTrue(tweets.hasTweet(tweet));
    }

    public void testGetTweet() {
        TweetList tweets = new TweetList();
        Tweet tweet = new NormalTweet("get Tweet");
        tweets.addTweet(tweet);
        Tweet returnedTweet = tweets.getTweet(0);
        assertEquals(returnedTweet.getMessage(), tweet.getMessage());
    }

    public void testGetTweets() {
        TweetList tweets = new TweetList();
        for (int i = 0; i < 4; ++i) {
            Tweet tweet = new NormalTweet(Integer.toString(i));
            tweets.addTweet(tweet);
        }
        ArrayList<Tweet> returnedList = tweets.getTweets();
        assertEquals(4, returnedList.size());
    }

    public void testGetCount() {
        TweetList tweets = new TweetList();
        for (int i = 0; i < 4; ++i) {
            Tweet tweet = new NormalTweet(Integer.toString(i));
            tweets.addTweet(tweet);
        }
        assertEquals(4, tweets.getCount());
    }
}
