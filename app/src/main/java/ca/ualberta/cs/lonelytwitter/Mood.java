package ca.ualberta.cs.lonelytwitter;

import java.util.Date;

/**
 * Created by amwhitta on 9/14/17.
 */

public abstract class Mood {

    private String mood;
    private Date date;

    // CONSTRUCTORS
    public Mood(){
        this.date = new Date();
    }

    public Mood(Date date) {
        this.date = date;
    }
    //

    public String getMood(){
        return mood;
    }

    public void setDate(Date date){
        this.date = date;
    }

    public Date getDate() {
        return date;
    }
}
