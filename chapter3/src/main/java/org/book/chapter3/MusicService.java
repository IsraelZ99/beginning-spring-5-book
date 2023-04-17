package org.book.chapter3;

import org.book.chapter3.model.Song;

import java.util.List;

public interface MusicService {

    List<Song> getSongsForArtist(String artist);

    List<String> getMatchingSongNamesForArtist(String artist, String prefix);

    List<String> getMatchingArtistName(String prefix);

    Song getSong(String artist, String name);

    Song voteForSong(String artist, String name);

}
