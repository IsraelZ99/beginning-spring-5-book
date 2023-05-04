package org.book.chapter8.controllers;

import org.book.chapter8.ArtistNotFoundException;
import org.book.chapter8.MusicRepository;
import org.book.chapter8.models.Artist;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Optional;

@RestController
public class ArtistController {

    private MusicRepository service;

    public ArtistController(MusicRepository service) {
        this.service = service;
    }

    public String decode(Object data) {
        return UriUtils.decode(data.toString(), Charset.defaultCharset());
    }

    @GetMapping("/artists/{id}")
    public Artist findArtistById(@PathVariable int id) {
        Artist artist = service.findArtistById(id);
        if (artist != null) {
            return artist;
        }
        throw new ArtistNotFoundException();
    }

    /*
    This method serves to migrate the MusicService's findArtistByName()
    to something that accepts and return an Optional
     */
    public Optional<Artist> findArtistByName(Optional<String> name, boolean update) {
        return Optional.of(service.findArtistByNameNoUpdate(decode(name.orElseThrow(
                () -> new IllegalArgumentException("No artist name supplied")
        ))));
    }

    @GetMapping(value = {"/artists/search/{name}", "/artists/search/"})
    public Artist findArtistByName(@PathVariable(required = false) Optional<String> name) {
        // Optional form
        Optional<Artist> artistOptional = findArtistByName(name, false);
        return artistOptional.orElseThrow(ArtistNotFoundException::new);

        // Traditional form
//        if (name != null) {
//            Artist artist = service.findArtistByNameNoUpdate(decode(name));
//            if (artist != null) {
//                return artist;
//            } else {
//                throw new ArtistNotFoundException();
//            }
//        } else {
//            throw new IllegalArgumentException("No artist name submitted");
//        }
    }

    @PostMapping("/artists")
    public Artist saveArtist(@RequestBody Artist artist) {
        return service.findArtistByName(artist.getName());
    }

    @GetMapping(value = {"/artists/match/{name}", "/artists/match/"})
    List<String> findArtistByMatchingName(@PathVariable(required = false) String name) {
        return service.getMatchingArtistNames(name != null ? decode(name) : "");
    }

}
