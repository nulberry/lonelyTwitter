package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by amwhitta on 9/14/17.
 */

public class HappyMood extends Mood {

    public HappyMood() {
        setMood("Happy! :-)");
    }

    public HappyMood(Date date) {
        super(date);
        setMood("Happy! :-)");
    }

}
