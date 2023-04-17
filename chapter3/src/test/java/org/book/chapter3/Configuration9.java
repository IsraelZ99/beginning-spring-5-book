package org.book.chapter3;

import org.book.chapter3.mem03.CapLeadingNormalizer;
import org.book.chapter3.mem03.MusicService3;
import org.book.chapter3.mem03.SimpleNormalizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration9 {

    // Is the same that @Bean(name = "foo")
    @Bean
    Normalizer foo() {
        return new SimpleNormalizer();
    }

    @Bean(name = "bar")
    Normalizer capNormalizer() {
        return new CapLeadingNormalizer();
    }

    @Bean
    MusicService musicService() {
        return new MusicService3();
    }

}
