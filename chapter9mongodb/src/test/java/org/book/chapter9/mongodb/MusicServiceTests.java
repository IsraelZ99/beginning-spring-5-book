package org.book.chapter9.mongodb;

import org.book.chapter9.test.BaseMusicServiceTests;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.TestPropertySource;

import java.util.UUID;

@DataMongoTest
@TestPropertySource(properties = "spring.mongodb.embedded.version=3.5.5")
public class MusicServiceTests
        extends BaseMusicServiceTests<Artist, Song, String> {

    @Override
    protected String getNonexistentId() {
        return UUID.randomUUID().toString();
    }

}
