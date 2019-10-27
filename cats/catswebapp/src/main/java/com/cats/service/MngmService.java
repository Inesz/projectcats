package com.cats.service;

import com.cats.Cat;
import com.cats.CatFoto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * Ensures compatibility database with the cloud.
 */
@Service
public class MngmService {
    private static Logger LOGGER = LoggerFactory.getLogger(MngmService.class);

    @Autowired
    private ImgCloudService imgCloudService;
    @Autowired
    private CatsCRUDService catsCRUDService;

    public void deleteCatWithCatFoto(String id) {
        String ImgName = catsCRUDService.getCatFotoNewName(id);
        imgCloudService.removeFile(ImgName);
        catsCRUDService.deleteCat(id);
    }

    public void addImage(MultipartFile file, String catId, String comment) {
        if (!checkFileType(file)) return;

        Cat cat = catsCRUDService.selectCatById(catId);

        if (cat.getCatFoto() != null) {
            deleteCatFoto(cat);
        }

        CatFoto catFoto = catsCRUDService.createCatFoto(file, comment);
        insertCatFoto(cat, catFoto, file);
    }

    private boolean checkFileType(MultipartFile file) {
        try {
            imgCloudService.checkFileType(file);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("File incorrect - upload denied.");
            LOGGER.info("File name: " + file.getOriginalFilename());
            LOGGER.info("File format: " + file.getName());
            LOGGER.info("Content type: " + file.getContentType());
            return false;
        } finally {
            LOGGER.info("File type check completed.");
        }
    }

    public String getImage(Cat cat) {
        try {
            String fileName = catsCRUDService.getCatFotoNewName(cat);
            return imgCloudService.getCatImage(fileName);
        } catch (Exception e) {
            return "";
        }
    }

    private void deleteCatFoto(Cat cat) {
        imgCloudService.removeFile(cat.getCatFoto().getNewname());
        cat.setCatFoto(null);
    }

    private void insertCatFoto(Cat cat, CatFoto catFoto, MultipartFile file) {
        cat.setCatFoto(catFoto);
        imgCloudService.saveFile(file, catFoto.getNewname());
    }

}