package org.book.chapter6;

import org.book.chapter3.MusicService;
import org.book.chapter3.model.Artist;
import org.book.chapter6.exception.ArtistNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GetArtistsExceptionController {

    @Autowired
    MusicService musicService;

    @GetMapping("/artists/error/{artist}")
    public ResponseEntity<Artist> getSong(@PathVariable("artist") final String artist) {
        throw new ArtistNotFoundException("Artist with name " + artist + " not found");
    }

    @ExceptionHandler(ArtistNotFoundException.class)
    public ModelAndView handleCustomException(ArtistNotFoundException ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("message", ex.getMessage());
        model.addObject("statusCode", 404);
        return model;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllExceptions(Exception ex) {
        ModelAndView model = new ModelAndView("error");
        model.addObject("message", ex.getMessage());
        model.addObject("statusCode", 500);
        return model;
    }

}
