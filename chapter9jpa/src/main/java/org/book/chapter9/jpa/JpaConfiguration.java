package org.book.chapter9.jpa;

import org.book.chapter9.common.WildcardConverter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/*
    @SpringBootConfiguration = scan the current package (and any packages whose names start
      with org.book.chapter9.jpa) for Spring components which catch MusicService and ArtistRepository
      and SongRepository interfaces;

      @EnableJpaRepositories = we're informing Spring of what kind of services to implement
      (and where to look for classes that have the @Repository annotation)

      @EntityScan = force scanning for entities (classes marked with @Entity)
 */
@SpringBootConfiguration
@EnableJpaRepositories
@EntityScan
public class JpaConfiguration {

    @Bean
    WildcardConverter converter() {
        return new WildcardConverter("%");
    }

    @Bean
    MusicService musicService(
            ArtistRepository artistRepository,
            SongRepository songRepository,
            WildcardConverter converter
    ) {
        return new MusicService(artistRepository, songRepository, converter);
    }

}
