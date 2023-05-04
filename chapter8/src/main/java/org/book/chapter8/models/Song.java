package org.book.chapter8.models;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Song {

    Integer id;

    @NonNull
    Integer artistId;

    @NonNull
    String name;

    int votes;

}
