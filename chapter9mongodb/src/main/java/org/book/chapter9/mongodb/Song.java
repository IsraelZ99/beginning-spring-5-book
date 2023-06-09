package org.book.chapter9.mongodb;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.book.chapter9.common.BaseSong;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
@CompoundIndexes(
        @CompoundIndex(unique = true, def = "{'artist':1, 'name':1}")
)
public class Song implements BaseSong<Artist, String> {

    @Id
    String id;

    @NonNull
    @DBRef
    Artist artist;

    @NonNull
    String name;

    int votes;

}
