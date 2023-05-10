package org.book.chapter9.jpa;

import lombok.*;
import org.book.chapter9.common.BaseSong;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@AllArgsConstructor
@Table(indexes = {

})
public class Song implements BaseSong<Artist, Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @ManyToOne
    @NonNull
    Artist artist;

    String name;

    int votes;

    @Override
    public Integer getId() {
        return null;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public Artist getArtist() {
        return artist;
    }

    @Override
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getVotes() {
        return votes;
    }

    @Override
    public void setVotes(int votes) {
        this.votes = votes;
    }
}
