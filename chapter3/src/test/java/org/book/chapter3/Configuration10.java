package org.book.chapter3;

import org.book.chapter3.mem03.CapLeadingNormalizer;
import org.book.chapter3.mem03.SimpleNormalizer;
import org.book.chapter3.mem04.MusicService4;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
/*
 The advantage provided here is not only that of flexibility but management.
 Only one instance of a SimpleNormalizer, for example, is created; it's a singleton.
 */
@Configuration
public class Configuration10 {

    @Bean
    Normalizer foo(){
        return new SimpleNormalizer();
    }

    @Bean
    Normalizer bar(){
        return new CapLeadingNormalizer();
    }

    @Bean
    MusicService musicService(Normalizer bar,
                              @Qualifier("foo") Normalizer baz) {
        return new MusicService4(bar, baz);
    }

}
