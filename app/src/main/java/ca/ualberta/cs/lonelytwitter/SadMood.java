package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by amwhitta on 9/14/17.
 */

public class SadMood extends Mood {

    public SadMood() {
        setMood("Sad. :-(");
    }

    public SadMood(Date date) {
        super(date);
        setMood("Sad. :-(");
    }
}
