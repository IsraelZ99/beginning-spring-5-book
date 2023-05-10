package org.book.chapter9.jpa;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.book.chapter9.common.BaseArtist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "songs")
public class Artist implements BaseArtist<Integer> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NonNull
    String name;

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "artist",
            fetch = FetchType.EAGER
    )
    @OrderBy("votes DESC")
    @JsonIgnore
    List<Song> songs = new ArrayList<>();

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }
}
