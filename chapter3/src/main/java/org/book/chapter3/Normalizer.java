package org.book.chapter3;

// Implementation about delete white spaces and format text
public interface Normalizer {

    default String transform(String input) {
        return input.trim();
    }

}
