package ca.ualberta.cs.lonelytwitter;


import java.util.Date;

public interface Tweetable {
    String getMessage();         // fixed unnecessary interface modifier (was public)

    Date getDate();              // fixed unnecessary interface modifier (was public)

}
