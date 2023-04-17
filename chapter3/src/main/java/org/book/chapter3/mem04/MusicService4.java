package org.book.chapter3.mem04;

import org.book.chapter3.AbstractMusicService;
import org.book.chapter3.Normalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("ms4")
public class MusicService4 extends AbstractMusicService {

    private final Normalizer artistNormalizer;
    private final Normalizer songNormalizer;

    public MusicService4(@Autowired
                         @Qualifier("bar")
                         Normalizer artistNormalizer,
                         @Autowired
                         @Qualifier("foo")
                         Normalizer songNormalizer) {
        this.artistNormalizer = artistNormalizer;
        this.songNormalizer = songNormalizer;
    }

    @Override
    protected String transformArtist(String input) {
        return artistNormalizer.transform(input);
    }

    @Override
    protected String transformSong(String input) {
        return songNormalizer.transform(input);
    }
}
