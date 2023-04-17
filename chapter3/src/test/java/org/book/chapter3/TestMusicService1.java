package org.book.chapter3;

import org.book.chapter3.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.testng.Assert.*;

@ContextConfiguration(locations = "/config-01.xml")
public class TestMusicService1 extends AbstractTestNGSpringContextTests {

    // Autowired: it will look for classes that is manages that fit the description of the field
    @Autowired
    ApplicationContext context;

    // Autowired: it will look for classes that is manages that fit the description of the field
    @Autowired
    MusicService musicService;

    @Test
    void testConfiguration() {
        assertNotNull(context);
        Set<String> definitions = new HashSet<>(Arrays.asList(context.getBeanDefinitionNames()));
        for (String d : definitions) {
            System.out.println(d + " definition");
        }
        assertTrue(definitions.contains("musicService1"));
    }

    @Test
    void testMusicService() {
        Song song = musicService.getSong("Threadbare Loaf", "Someone Stole the Flour");
        assertEquals(song.getVotes(), 0);
    }

}
