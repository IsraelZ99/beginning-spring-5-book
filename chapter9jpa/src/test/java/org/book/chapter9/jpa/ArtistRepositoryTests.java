package org.book.chapter9.jpa;

import org.book.chapter9.test.BaseArtistRepositoryTests;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest // It will set up a Spring application context
public class ArtistRepositoryTests
        extends BaseArtistRepositoryTests<Artist, Integer> {
    @Override
    protected Artist createArtist(String name) {
        return new Artist(name);
    }

}
