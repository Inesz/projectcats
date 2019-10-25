package com.cats.service;

import com.cats.CatFoto;
import com.cats.DAO.CloudCatFotoDAO;
import com.cats.DAO.SpringCatFotoDAO;
import com.cats.DAO.SpringDataDAO;
import com.cats.exception.IllegalFileFormat;
import com.google.cloud.storage.Blob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
public class CatFotoMngmService {
    private static Logger LOGGER = LoggerFactory.getLogger(CatFotoMngmService.class);
    String[] allowedExt = {"jpg", "jpeg", "png", "gif"};
    private CloudCatFotoDAO cloudCatFotoDAO = new CloudCatFotoDAO();
    @Autowired
    private SpringDataDAO springDataDAO;
    @Autowired
    private SpringCatFotoDAO springCatFotoDAO;

    public void saveFile(MultipartFile file, String catId, String comment) {
        try {
            checkFileType(file);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("File incorrect - upload denied.");
            LOGGER.info("File name: " + file.getOriginalFilename());
            LOGGER.info("File format: " + file.getName());
            LOGGER.info("Content type: " + file.getContentType());
            return;
        } finally {
            LOGGER.info("File type check completed.");
        }

        removeFile(catId);
        insertFile(file, catId, comment);
    }

    private void insertFile(MultipartFile file, String catId, String comment) {
        String newName = createNewName();
        CatFoto catFoto = setCatFoto(file, newName, catId, comment);
        updateDB(catFoto);
        cloudCatFotoDAO.fileUpload(file, newName);
    }

    private void removeFile(String catId) {
//        Optional<CatFoto> catFoto = springCatFotoDAO.findByCatId(Integer.parseInt(catId));
//
//        if(catFoto.isPresent()){
//            cloudCatFotoDAO.fileRemove(catFoto.get().getNewname());
//            springCatFotoDAO.deleteByCatId(Integer.parseInt(catId));
//        }
    }

    /**
     *
     * @param catId
     * @return Base64 String img
     */
    public String getCatImage(String catId) {
        String encodeCatImg = "";
//        Optional<CatFoto> catFoto = springCatFotoDAO.findByCatId(Integer.parseInt(catId));
//
//        if(catFoto.isPresent()) {
//            Blob catImg = cloudCatFotoDAO.fileDownload(catFoto.get().getNewname());
//            encodeCatImg = Base64.getEncoder().encodeToString(catImg.getContent());
//        }

        return encodeCatImg;
    }

    private void updateDB(CatFoto catFoto) {
        springCatFotoDAO.save(catFoto);
    }

    private CatFoto setCatFoto(MultipartFile file, String newName, String catId, String comment) {
        return new CatFoto(comment, file.getOriginalFilename(), newName, (int) file.getSize(), file.getContentType());
    }

    /**
     * Check File Type
     *
     * Using your own exception compatible with JDK 7:
     * <ul>
     *  <li>multi-catch exception</li>
     *  <li>rethrow exceptions</li>
     *  <li>more precise rethrow, exceptions chain ('Caused by:' in err message)</li>
     * </ul>
     * @param file
     * @throws Exception there is no need to declare IllegalArgumentException and IllegalFileFormat exceptions in throws section (are Exceptions subclass)
     */
    private void checkFileType(MultipartFile file) throws Exception {
        final String fileName = file.getOriginalFilename();

        try{
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1);

            if(fileName == null || fileName.isEmpty()){
                throw new IllegalArgumentException("Invalid file name; File name is empty.");
            }
            if(!fileName.contains(".")){
                throw new IllegalArgumentException("Invalid file name; File name does not contain the extension");
            }
            if(!file.getName().equals("imgFile")){
                throw new IllegalArgumentException("Invalid file format [" + file.getName() + "]; only 'imgFile' is allowed.");
            }
            if (!validExtension(allowedExt, extension)) {
                throw new IllegalFileFormat("Invalid file extension [" + extension + "].");
            }
        }catch(IllegalArgumentException | IllegalFileFormat e){
            throw e;
        }catch(Exception e){
            throw new Exception("Invalid name [" + file.getOriginalFilename() + "] or format [" + file.getName() + "].", e);
        }
    }

    private boolean validExtension(String[] array, String value) {
        for (String s : array) {
            if (s.equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }

    private String createNewName() {
        Long time = new Date().getTime();
        return Long.toString(time);
    }

}