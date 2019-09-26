package com.cats.service;

import com.cats.DAO.CloudCatFotoDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class CatFotoMngmService {
    private static Logger LOGGER = LoggerFactory.getLogger(CatFotoMngmService.class);
    String[] allowedExt = {"jpg", "jpeg", "png", "gif"};
    private CloudCatFotoDAO cloudCatFotoDAO = new CloudCatFotoDAO();

    public void saveFile(MultipartFile file, String catName, String comment) {
        try {
            checkFileType(file);
        } catch (Exception e) {
            e.printStackTrace();
            LOGGER.info("File incorrect - upload denied.");
            LOGGER.info("File name: " + file.getOriginalFilename());
            LOGGER.info("File format: " + file.getName());
            LOGGER.info("Content type: " + file.getContentType());
            return;
        }

        String newName = createNewName();
        cloudCatFotoDAO.fileUpload(file, newName);
    }

    private void checkFileType(MultipartFile file) throws Exception {
        final String fileName = file.getOriginalFilename();
        String extension = "";

        if (fileName != null && !fileName.isEmpty() && fileName.contains(".") && file.getName().equals("imgFile")) {
            extension = fileName.substring(fileName.lastIndexOf('.') + 1);
            if (!validExtension(allowedExt, extension)) {
                throw new Exception("File must be an image, is '" + extension + "'.");
            }
        } else {
            throw new Exception("Invalid name [" + file.getOriginalFilename() + "] or format [" + file.getName() + "].");
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