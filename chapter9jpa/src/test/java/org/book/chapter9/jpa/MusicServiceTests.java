package org.book.chapter9.jpa;

import org.book.chapter9.test.BaseMusicServiceTests;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MusicServiceTests
        extends BaseMusicServiceTests<Artist, Song, Integer> {

    @Override
    protected Integer getNonexistentId() {
        return 1928491;
    }

}
