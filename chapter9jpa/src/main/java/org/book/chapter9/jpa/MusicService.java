package org.book.chapter9.jpa;

import org.book.chapter9.common.BaseMusicService;
import org.book.chapter9.common.WildcardConverter;

public class MusicService extends BaseMusicService<Artist, Song, Integer> {

    MusicService(ArtistRepository artistRepository,
                 SongRepository songRepository,
                 WildcardConverter converter) {
        super(artistRepository, songRepository, converter);
    }

    @Override
    protected Artist createArtist(String name) {
        return new Artist(name);
    }

    @Override
    protected Song createSong(Artist artist, String name) {
        return new Song(artist, name);
    }
}
