package org.book.chapter3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

@ContextConfiguration(locations = "/config-02.xml")
public class TestMusicService2 extends AbstractTestNGSpringContextTests{

     // Autowired: it will look for classes that is manages that fit the description of the field
    @Autowired
    MusicService musicService;

    MusicServiceTest tests = new MusicServiceTest();

    @Test
    public void testSongVoting(){
        tests.testSongVoting(musicService);
    }

    @Test

    public void testGetMatchingArtistNames() {
        tests.testMatchingArtistNames(musicService);
    }

    @Test
    public void testGetSongsForArtist() {
        tests.testSongsForArtist(musicService);
    }

    @Test
    public void testMatchingSongNamesForArtist() {
        tests.testMatchingSongNamesForArtist(musicService);
    }

}
