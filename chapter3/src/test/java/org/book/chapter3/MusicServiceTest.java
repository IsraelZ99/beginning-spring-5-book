package org.book.chapter3;

import static org.testng.Assert.assertEquals;

import java.util.List;
import java.util.function.Consumer;

public class MusicServiceTest {

    private Object[][] model = new Object[][]{
            {"Threadbare Loaf", "Someone Stole the Flour", 4},
            {"Threadbare Loaf", "What Happened To Our First CD", 17},
            {"Therapy Zeppelin", "Medium", 4},
            {"Clancy in Silt", "Igneous", 5}
    };

    void iterateOverModel(Consumer<Object[]> consumer) {
        for (Object[] data : model) {
            consumer.accept(data);
        }
    }

    void populateService(MusicService service) {
        iterateOverModel(data -> {
            for (int i = 0; i < (Integer) data[2]; i++) {
                service.voteForSong((String) data[0], (String) data[1]);
            }
        });
    }

    void reset(MusicService musicService) {
        if (musicService instanceof Resettable) {
            ((Resettable) musicService).reset();
        } else {
            throw new RuntimeException(musicService + "does not implements Resettable");
        }
    }

    void testSongVoting(MusicService musicService) {
        reset(musicService); // Clear map
        populateService(musicService); // Populate map
        iterateOverModel(data ->
                assertEquals(
                        musicService.getSong((String) data[0], (String) data[1]).getVotes(),
                        ((Integer) data[2]).intValue()
                )
        );
    }

    void testSongsForArtist(MusicService musicService) {
        reset(musicService);
        populateService(musicService);
        iterateOverModel(data -> {
            assertEquals(
                    musicService.getSong((String) data[0], (String) data[1]).getVotes(),
                    ((Integer) data[2]).intValue()
            );
        });
    }

    void testMatchingArtistNames(MusicService musicService) {
        reset(musicService);
        populateService(musicService);
        List<String> names = musicService.getMatchingArtistName("Th");
        assertEquals(names.size(), 2);
        assertEquals(names.get(0), "Therapy Zeppelin");
        assertEquals(names.get(1), "Threadbare Loaf");
    }

    void testMatchingSongNamesForArtist(MusicService musicService) {
        reset(musicService);
        populateService(musicService);
        List<String> names = musicService.getMatchingSongNamesForArtist("Threadbare Loaf", "W");
        assertEquals(names.size(), 1);
        assertEquals(names.get(0), "What Happened To Our First CD");
    }

}
