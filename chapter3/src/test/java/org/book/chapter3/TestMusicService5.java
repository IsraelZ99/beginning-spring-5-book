package org.book.chapter3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = {"/config-05.xml", "/musicservicetest.xml"})
public class TestMusicService5 extends AbstractTestNGSpringContextTests {

    @Autowired
    MusicService musicService;

    @Autowired
    MusicServiceTest tests;

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
