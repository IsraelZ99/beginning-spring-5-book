package org.book.chapter3.mem02;

import org.book.chapter3.AbstractMusicService;
import org.book.chapter3.Normalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MusicService2 extends AbstractMusicService implements Normalizer{

    @Override
    protected String transformArtist(String input) {
        return transform(input);
    }

    @Override
    protected String transformSong(String input) {
        return transform(input);
    }
}
