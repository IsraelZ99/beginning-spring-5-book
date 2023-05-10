package org.book.chapter9.jpa;

import org.book.chapter9.test.BaseSongRepositoryTests;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.testng.annotations.Test;

@DataJpaTest
public class SongRepositoryTests
        extends BaseSongRepositoryTests<Artist, Song, Integer> {

    @Override
    protected Artist createArtist(String name) {
        return new Artist(name);
    }

    @Override
    protected Song createSong(Artist artist, String name) {
        return new Song(artist, name);
    }

}
