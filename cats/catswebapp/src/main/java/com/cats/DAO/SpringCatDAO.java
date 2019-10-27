package com.cats.DAO;

import com.cats.Cat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * Cat is automatically updated after transaction finishing.
 * Changes made in Entity are saved to DB (also changes in subEntity CatFoto)
 */
public interface SpringCatDAO extends CrudRepository<Cat, Long> {
    List<Cat> findAll();

    Optional<Cat> findById(int id);

    Cat save(Cat cat);

    void delete(Cat cat);

    void deleteById(int id);
}