package org.book.chapter3;

import org.book.chapter3.mem01.MusicService1;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 In static configuration you declare a class with methods that return components;
 Mark the methods that return Spring beans with @Bean, and mark class with @Configuration
 This configuration would contribute one Spring bean to an ApplicationContext whose name
 would be derived from the method.
 */
@Configuration
public class Configuration7 {

    /*
      Create reference to a MusicService called musicService, with an instance reference of type
      MusicService1
     */
    @Bean
    MusicService musicService() {
        return new MusicService1();
    }

}
