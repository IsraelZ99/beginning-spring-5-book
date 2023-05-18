package org.book.chapter10;

import org.book.chapter9.jpa.MusicService;
import org.book.chapter9.jpa.Song;
import org.springframework.web.bind.annotation.*;

@RestController
public class SongController {

    private final MusicService musicService;

    SongController(MusicService service) {
        this.musicService = service;
    }

    @GetMapping(value = "/songs/{id}")
    Song getSongById(@PathVariable int id) {
        Song song = musicService.getSongById(id);
        if (song != null) {
            return song;
        } else {
            throw new SongNotFoundException();
        }
    }

    @PostMapping("/songs")
    Song saveSong(@RequestBody Song song) {
        Song songLookup = musicService.getSong(song.getArtist().getName(), song.getName());
        if(songLookup != null) {
            return songLookup;
        } else {
            throw new SongNotFoundException();
        }
    }

}
