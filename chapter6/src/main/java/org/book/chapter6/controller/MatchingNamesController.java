package org.book.chapter6.controller;

import org.book.chapter3.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MatchingNamesController {

    @Autowired
    private MusicService musicService;

    @GetMapping("/songs/matching")
    public ResponseEntity<List<String>> getMatchingSongNamesByArtist(
            @RequestParam(name = "artist") String artist,
            @RequestParam(name = "prefix") String prefix
    ) {
        return new ResponseEntity<>(
                musicService.getMatchingSongNamesForArtist(artist, prefix),
                HttpStatus.OK);
    }

    @GetMapping("/artists/matching")
    public ResponseEntity<List<String>> getMatchingArtistByName(
            @RequestParam(name = "prefix") String prefix
    ) {
        return new ResponseEntity<>(
                musicService.getMatchingArtistName(prefix),
                HttpStatus.OK);
    }

}
