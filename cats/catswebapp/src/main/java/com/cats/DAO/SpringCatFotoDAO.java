package com.cats.DAO;

import com.cats.CatFoto;
import org.springframework.data.repository.CrudRepository;

public interface SpringCatFotoDAO extends CrudRepository<CatFoto, Long> {
    CatFoto save(CatFoto catFoto);
}