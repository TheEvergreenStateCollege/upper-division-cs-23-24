package com.mycompany.app;
import java.util.Date;


public class Song {
    String title;
    String album;
    String artist;
    String countryListenedIn;
    Date datePlayed;
    String username;
    String platformPlayed;
    int msPlayed;
    String spotifyTrackUrl;
    String episodeName;
    String episodeShowNameString;
    String spotifyEpisodeUrl;
    String reasonStart;
    String reasonEnd;
    boolean shuffule;
    boolean offline;
    boolean incognito;
    String skipped;
    String offlineTimestamp;

    public void setDatePlayed(Date ts){
        datePlayed = ts;
    }

    public void setUsername(String username){
        this.username = username;
    }

    

    



}
