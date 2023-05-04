package org.book.chapter8;

import org.book.chapter8.models.Artist;
import org.book.chapter8.models.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class MusicRepository{

    /*
    @Transactional: The method annotated needs to run in a transaction context
    of some sort, which might even include "no transactions at all". A transactional
    context is referred to as its propagation.
     */

    JdbcTemplate jdbcTemplate;

    public MusicRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Autowired
    SongRowMapper songRowMapper;

    @Transactional
    public Artist findArtistById(Integer id) {
        return jdbcTemplate.query(
                        "SELECT id, name FROM artists WHERE id=?",
                        new Object[]{id},
                        (rs, rowNum) -> new Artist(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
                ).stream()
                .findFirst()
                .orElse(null);
    }

    @Transactional
    public Artist findArtistByName(String name) {
        return internalFindArtistByName(name, true);
    }

    @Transactional
    public Artist findArtistByNameNoUpdate(String name) {
        return internalFindArtistByName(name, false);
    }

    // It's private internal method, thus is not annotated with @Transactional
    private Artist internalFindArtistByName(String name, boolean update) {
        String insertSQL = "INSERT INTO artists (name) VALUES (?)";
        String selectSQL = "SELECT id, name FROM artists " +
                "WHERE lower(name)=lower(?)";
        return jdbcTemplate.query(
                        selectSQL,
                        new Object[]{name},
                        (rs, rowNum) -> new Artist(
                                rs.getInt("id"),
                                rs.getString("name")
                        )
                ).stream()
                .findFirst().orElseGet(() -> {
                    if (update) {
                        // Used for auto-generated keys as potentially returned by JDBC insert statements
                        KeyHolder keyHolder = new GeneratedKeyHolder();
                        jdbcTemplate.update(conn -> {
                            PreparedStatement ps = conn.prepareStatement(
                                    insertSQL,
                                    Statement.RETURN_GENERATED_KEYS
                            );
                            ps.setString(1, name);
                            return ps;
                        }, keyHolder);
                        return new Artist(keyHolder.getKey().intValue(), name);
                    } else {
                        return null;
                    }
                });
    }

    @Transactional
    public List<Song> getSongsForArtist(String artistName) {
        String selectSQL = "SELECT id, artist_id, name, votes " +
                "FROM songs WHERE artist_id=? " +
                "ORDER BY votes DESC, name ASC";
        Artist artist = internalFindArtistByName(artistName, true);
        return jdbcTemplate.query(
                selectSQL, new Object[]{artist.getId()},
                songRowMapper
        );
    }

    @Transactional
    public List<String> getMatchingSongNamesForArtist(
            String artistName, String prefix
    ) {
        String selectSQL = "SELECT name FROM songs WHERE artist_id=? " +
                "AND LOWER(name) LIKE LOWER(?) " +
                "ORDER BY name ASC";
        Artist artist = internalFindArtistByName(artistName, true);
        return jdbcTemplate.query(
                selectSQL, new Object[]{artist.getId(), prefix + "%"},
                (rs, rowNum) -> rs.getString("name")
        );
    }

    @Transactional
    public List<String> getMatchingArtistNames(String prefix) {
        String selectSQL = "SELECT name FROM artists WHERE " +
                "LOWER(name) LIKE LOWER(?) " +
                "ORDER BY name ASC";
        /*
        Note use of Object[] for query arguments, and the use of a lambda
        to map from row to a String
         */
        return jdbcTemplate.query(
                selectSQL,
                new Object[]{prefix + "%"},
                (rs, rowNum) -> rs.getString("name")
        );
    }

    @Transactional
    public Song getSong(String artistName, String name) {
        return internalGetSong(artistName, name);
    }

    // Makes to avoid nesting transactional calls made from the same class
    private Song internalGetSong(String artistName, String name) {
        String selectSQL = "SELECT id, artist_id, name, votes FROM songs " +
                "WHERE artist_id=? " +
                "AND LOWER(name) = LOWER(?)";
        String insertSQL = "INSERT INTO songs (artist_id, name, votes) " +
                "VALUES(?,?,?)";
        Artist artist = internalFindArtistByName(artistName, true);
        Song song = jdbcTemplate.query(
                        selectSQL,
                        new Object[]{artist.getId(), name},
                        songRowMapper
                ).stream()
                .findFirst()
                .orElseGet(() -> {
                    KeyHolder keyHolder = new GeneratedKeyHolder();
                    jdbcTemplate.update(conn -> {
                        PreparedStatement ps = conn.prepareStatement(
                                insertSQL,
                                Statement.RETURN_GENERATED_KEYS
                        );
                        ps.setInt(1, artist.getId());
                        ps.setString(2, name);
                        ps.setInt(3, 0);
                        return ps;
                    }, keyHolder);
                    return new Song(keyHolder.getKey().intValue(),
                            artist.getId(),
                            name,
                            0);
                });
        return song;
    }

    @Transactional
    public Song voteForSong(String artistName, String name) {
        String updateSQL = "UPDATE songs SET votes=? WHERE id=?";
        Song song = internalGetSong(artistName, name);
        song.setVotes(song.getVotes() + 1);
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement(updateSQL);
            ps.setInt(1, song.getVotes());
            ps.setInt(2, song.getId());
            return ps;
        });
        return song;
    }

}
