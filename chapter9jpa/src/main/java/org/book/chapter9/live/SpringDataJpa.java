package org.book.chapter9.live;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {
        "org.book.chapter9.jpa"
})
public class SpringDataJpa {

    public static void main(String[] args) {
        SpringApplication.run(SpringDataJpa.class, args);
    }

}
