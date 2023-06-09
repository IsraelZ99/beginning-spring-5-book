package org.book.chapter9.mongodb;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.book.chapter9.common.BaseArtist;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor(force = true)
@RequiredArgsConstructor
public class Artist implements BaseArtist<String> {

    @Id
    String id;

    @NonNull
    String name;
}
