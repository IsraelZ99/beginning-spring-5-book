package org.book.chapter9.test;

import org.book.chapter9.common.BaseArtist;
import org.book.chapter9.common.BaseArtistRepository;
import org.book.chapter9.common.BaseSong;
import org.book.chapter9.common.WildcardConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;

import java.util.List;
import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public abstract class BaseArtistRepositoryTests<
        A extends BaseArtist<ID>, ID
        > {

    @Autowired
    BaseArtistRepository<A, ID> artistRepository;

    @Autowired
    WildcardConverter converter;

    protected abstract A createArtist(String name);

    @BeforeMethod
    public void clearDatabase() {
        artistRepository.deleteAll();
    }

    @Test
    public void testOperations() {
        assertEquals(artistRepository.count(), 0);

        A firstEntity = createArtist("Threadbare Loaf");
        A secondEntity = createArtist("Therapy Zeppelin");

        // Save the first artist only
        artistRepository.save(firstEntity);

        Optional<A> artist = artistRepository.findById(firstEntity.getId());
        assertTrue(artist.isPresent());
        assertEquals(artist.get(), firstEntity);

        List<A> query = artistRepository.findAllByNameIsLikedIgnoreCaseOrderByName(
                converter.convertToWildCard("th")
        );
        assertEquals(query.size(), 1);
        assertEquals(query.get(0), firstEntity);

        artistRepository.save(secondEntity);
        query = artistRepository.findAllByNameIsLikedIgnoreCaseOrderByName(
                converter.convertToWildCard("th")
        );
        assertEquals(query.size(), 2);
    }

}
