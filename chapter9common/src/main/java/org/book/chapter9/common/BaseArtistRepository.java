package org.book.chapter9.common;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BaseArtistRepository<T extends BaseArtist<ID>, ID> extends CrudRepository<T, ID> {

    List<T> findAllByNameIsLikedIgnoreCaseOrderByName(String name);

    Optional<T> findByNameIgnoreCase(String name);

}
