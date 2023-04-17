package org.book.chapter6.controller;

import org.book.chapter3.MusicService;
import org.book.chapter3.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class GetSongsController {

    @Autowired
    MusicService service;

    @GetMapping("/songs")
    @ResponseBody // Use the return value and bind it yo the response body
    public ResponseEntity<List<Song>> getSongsByArtist(
            @RequestParam(name = "artist") String artist
    ) {
        System.out.println(artist);
        List<Song> data = service.getSongsForArtist(artist);
        return new ResponseEntity<>(data, HttpStatus.OK);
    }

    // With path variable you need to decode
    @GetMapping("/artists/{artist}/songs/{name}")
    @ResponseBody
    public ResponseEntity<Song> getSong(
            @PathVariable("artist") final String artist,
            @PathVariable("name") final String name
    ) {
        String artistDecoded = URLDecoder.decode(artist, StandardCharsets.UTF_8);
        String nameDecoded = URLDecoder.decode(name, StandardCharsets.UTF_8);
        Song song = service.getSong(artistDecoded, nameDecoded);
        return new ResponseEntity<>(song, HttpStatus.OK);
    }

}
