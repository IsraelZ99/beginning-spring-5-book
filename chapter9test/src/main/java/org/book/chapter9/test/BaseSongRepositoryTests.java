package org.book.chapter9.test;

import org.book.chapter9.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Optional;

public abstract class BaseSongRepositoryTests<
        A extends BaseArtist<ID>,
        S extends BaseSong<A, ID>,
        ID
        > extends AbstractTestNGSpringContextTests {

    @Autowired
    BaseArtistRepository<A, ID> artistRepository;

    @Autowired
    BaseSongRepository<A, S, ID> songRepository;

    @Autowired
    WildcardConverter wildcardConverter;

    protected abstract A createArtist(String name);

    protected abstract S createSong(A artist, String name);

    @BeforeMethod
    public void clearDatabase() {
        songRepository.deleteAll();
        artistRepository.deleteAll();
        buildModel();
    }

    private Object[][] model = new Object[][]{
            {"Threadbare Loaf", "Someone Stole the Flour", 4},
            {"Threadbare Loaf", "What Happened To Our First CD?", 17},
            {"Therapy Zeppelin", "Mfbrbl Is Not A Word", 0},
            {"Therapy Zeppelin", "Medium", 4},
            {"Clancy in Silt", "Igneous", 5}
    };

    private void buildModel() {
        for (Object[] data : model) {
            String artistName = (String) data[0];
            String songTitle = (String) data[1];
            Integer votes = (Integer) data[2];
            Optional<A> artistQuery = artistRepository.findByNameIgnoreCase(artistName);
            A artist = artistQuery.orElseGet(() -> {
                A entity = createArtist(artistName);
                artistRepository.save(entity);
                return entity;
            });
            Optional<S> songQuery = songRepository.findByArtistIdAndNameIgnoreCase(
                    artist.getId(),
                    songTitle);
            if(songQuery.isEmpty()) {
                S song = createSong(artist, songTitle);
                song.setVotes(votes);
                songRepository.save(song);
            }
        }
    }

    @Test
    public void testOperations() {
        A artist = artistRepository.findByNameIgnoreCase("therapy zeppelin")
                .orElseThrow();
    }

}
