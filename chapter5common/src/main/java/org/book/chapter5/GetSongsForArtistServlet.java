package org.book.chapter5;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.book.chapter3.MusicService;
import org.book.chapter3.model.Song;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/songs")
public class GetSongsForArtistServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ApplicationContext context = (ApplicationContext) req.getServletContext()
                .getAttribute("context");
        MusicService service = context.getBean(MusicService.class);
        ObjectMapper mapper = new ObjectMapper();
        String artist = req.getParameter("artist");

        if (artist == null) {
            log("Missing data in request: requires artist parameter");
            resp.setStatus(500);
        } else {
            List<Song> songsForArtist = service.getSongsForArtist(artist);
            resp.setStatus(200);
            resp.getWriter().println(mapper.writeValueAsString(songsForArtist));
        }
    }
}
