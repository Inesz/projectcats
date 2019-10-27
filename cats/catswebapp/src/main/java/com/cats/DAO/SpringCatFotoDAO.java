package com.cats.DAO;

import com.cats.CatFoto;
import org.springframework.data.repository.CrudRepository;

/**
 * CatFoto is automatically updated with Cat (cascade = CascadeType.ALL).
 * CatFoto without connection (FK) with Cat is automatically removed (orphanRemoval = true).
 */
public interface SpringCatFotoDAO extends CrudRepository<CatFoto, Long> {

}