package org.book.chapter8.controllers;

import org.book.chapter8.MusicRepository;
import org.book.chapter8.models.Song;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.nio.charset.Charset;
import java.util.List;

@RestController
public class SongController {

    private final MusicRepository service;

    public SongController(MusicRepository service) {
        this.service = service;
    }

    public String decode(Object data) {
        return UriUtils.decode(data.toString(), Charset.defaultCharset());
    }

    @GetMapping("/artists/{name}/vote/{title}")
    public Song voteForSong(@PathVariable String name, @PathVariable String title) {
        return service.voteForSong(decode(name), decode(title));
    }

    @GetMapping("/artists/{name}/song/{title}")
    public Song getSong(@PathVariable String name, @PathVariable String title) {
        return service.getSong(decode(name), decode(title));
    }

    @GetMapping("/artists/{name}/songs")
    public List<Song> getSongsForArtist(@PathVariable String name) {
        return service.getSongsForArtist(decode(name));
    }

    @GetMapping(value = {"/artists/{name}/match/{title}", "/artists/{name}/match/"})
    public List<String> findSongsForArtist(@PathVariable String name,
                                           @PathVariable(required = false) String title) {
        return service.getMatchingSongNamesForArtist(decode(name),
                title != null ? decode(title) : "");
    }

}
