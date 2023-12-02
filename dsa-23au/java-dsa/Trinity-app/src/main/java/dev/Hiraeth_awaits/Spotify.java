package dev.Hiraeth_awaits;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Hello world!
 *
 */

class Song {
    private String name;
    private int duration; // assuming duration is in seconds

    public Song(String name, int duration) {
        this.name = name;
        this.duration = duration;
    }

    public String getName() {
        return name;
    }

    public int getDuration() {
        return duration;
    }

    @Override
    public String toString() {
        return "Song{" +
                "name='" + name + '\'' +
                ", duration=" + duration +
                '}';
    }
}

public class SpotifyDataSorter {

    public static void main(String[] args) {
        // Create a list of songs
        List<Song> Song = new ArrayList<>();
        song.add(new Song(//song name, song duration ));
        song.add(new Song(//song name, song duration ));
        song.add(new Song(//song name, song duration));

        // Sort the songs by name using Comparator
        Collections.sort(song, Comparator.comparing(Song::getName));

        // Print sorted songs by name
        System.out.println("Sorted by Name:");
        for (Song song : songs) {
            System.out.println(song);
        }

        // Sort the songs by duration using Comparator
        Collections.sort(songs, Comparator.comparingInt(Song::getDuration));

        // Print sorted songs by duration
        System.out.println("\nSorted by Duration:");
        for (Song song : songs) {
            System.out.println(song);
        }
    }
}
