package com.cats.service;

import com.cats.Cat;
import com.cats.CatFoto;
import com.cats.DAO.SpringCatDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * Ensure operations on DB
 */
@Service
public class CatsCRUDService {
    private static Logger LOGGER = LoggerFactory.getLogger(CatsCRUDService.class);

    @Autowired
    private SpringCatDAO springCatDAO;

    public void insertCat(Cat cat) {
        springCatDAO.save(cat);
    }

    public Cat selectCatById(String id) {
        Integer catId = Integer.parseInt(id);
        return springCatDAO.findById(catId).get();
    }

    public List<Cat> selectCats() {
        return springCatDAO.findAll();
    }

    public void deleteCat(String id) {
        deleteCatById(id);
        LOGGER.info("Removed from DB: " + id);
    }

    private void deleteCatById(String id) {
        int catId = Integer.parseInt(id);
        springCatDAO.deleteById(catId);
    }


    public String getCatFotoNewName(String id) {
        try {
            return selectCatById(id).getCatFoto().getNewname();
        } catch (Exception e) {
            return "";
        }
    }

    public String getCatFotoNewName(Cat cat) {
        try {
            return cat.getCatFoto().getNewname();
        } catch (Exception e) {
            return "";
        }
    }

    public CatFoto createCatFoto(MultipartFile file, String comment) {
        return new CatFoto(comment, file.getOriginalFilename(), catFotoNewName(), (int) file.getSize(), file.getContentType());
    }

    private String catFotoNewName() {
        Long time = new Date().getTime();
        return Long.toString(time);
    }
}
