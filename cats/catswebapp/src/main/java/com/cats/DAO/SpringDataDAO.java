package com.cats.DAO;

import com.cats.Cat;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface SpringDataDAO extends CrudRepository<Cat, Long>
{
    List<Cat> findAll();
    Optional<Cat> findById(Integer id);
    Optional<Cat> findByName(String name);
    Cat save(Cat cat);
    void delete(Cat cat);
    void deleteById(Long id);
}