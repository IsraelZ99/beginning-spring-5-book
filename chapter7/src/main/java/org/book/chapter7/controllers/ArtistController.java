package org.book.chapter7.controllers;

import org.book.chapter7.models.Artist;
import org.book.chapter7.repository.ArtistRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

/*
 @RestController it's a convenient annotation that combines @Controller and @ResponseBody
 */
@RestController
public class ArtistController {

    private ArtistRepository service;

    public ArtistController(ArtistRepository service) {
        this.service = service;
    }

    @GetMapping("/artist/{id}")
    Artist findArtistById(@PathVariable int id) throws SQLException {
        return service.findArtistById(id);
    }

    @GetMapping("/artist/search/{name}")
    Artist findArtistByName(@PathVariable(required = false) String name) throws SQLException {
        if (name != null) {
            return service.findArtistByName(name);
        } else {
            throw new IllegalArgumentException("No artist name submitted");
        }
    }

    @PostMapping("/artist")
    Artist saveArtist(@RequestBody Artist artist) throws SQLException {
        return service.saveArtist(artist.getName());
    }

    @GetMapping({"/artist/match/{name}", "/artist/match/"})
    List<Artist> findArtistsByMatchingName(
            @PathVariable(required = false) String name) throws SQLException {
        return service.findAllArtistsByName(name != null ? name : "");
    }

}
