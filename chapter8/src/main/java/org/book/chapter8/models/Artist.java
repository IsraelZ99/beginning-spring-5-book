package org.book.chapter8.models;

import lombok.*;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor(force = true)
public class Artist {
    Integer id;
    @NonNull
    String name;

}
