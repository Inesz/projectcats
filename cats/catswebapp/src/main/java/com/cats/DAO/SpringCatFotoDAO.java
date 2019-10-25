package com.cats.DAO;

import com.cats.Cat;
import com.cats.CatFoto;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface SpringCatFotoDAO extends CrudRepository<CatFoto, Long> {
    CatFoto save(CatFoto catFoto);

    //Optional<CatFoto> findByCatId(int catId);
    //void deleteByCatId(int catId);
}