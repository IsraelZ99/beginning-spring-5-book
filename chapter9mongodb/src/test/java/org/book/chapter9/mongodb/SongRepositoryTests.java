package org.book.chapter9.mongodb;

import org.book.chapter9.test.BaseSongRepositoryTests;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;

@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class SongRepositoryTests
        extends BaseSongRepositoryTests<Artist, Song, String> {

    @Override
    protected Artist createArtist(String name) {
        return new Artist(name);
    }

    @Override
    protected Song createSong(Artist artist, String name) {
        return new Song(artist, name);
    }
}
