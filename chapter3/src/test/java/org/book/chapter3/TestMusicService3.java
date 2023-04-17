package org.book.chapter3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/config-03.xml")
public class TestMusicService3 extends AbstractTestNGSpringContextTests {

    @Autowired
    MusicService musicService;

    MusicServiceTest tests = new MusicServiceTest();

    @Test
    void testSongVoting() {
        tests.testSongVoting(musicService);
    }

    @Test
    void testGetMatchingArtistNames() {
        tests.testMatchingArtistNames(musicService);
    }

    @Test
    void testGetSongForArtist() {
        tests.testSongsForArtist(musicService);
    }

    @Test
    void testMatchingSongNamesForArtist() {
        tests.testMatchingSongNamesForArtist(musicService);
    }

}
