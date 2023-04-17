package org.book.chapter6.controller;

import org.book.chapter3.MusicService;
import org.book.chapter3.model.Song;
import org.book.chapter6.model.Band;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VoteForSongController {

    @Autowired
    MusicService service;

    @PostMapping("/songs/vote")
    @ResponseBody
    public ResponseEntity<Song> voteForSong(@RequestBody Band band) {
        String artist = band.getArtist();
        String name = band.getName();
        return new ResponseEntity<>(service.voteForSong(artist, name), HttpStatus.OK);
    }

}
