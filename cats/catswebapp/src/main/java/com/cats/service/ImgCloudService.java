package com.cats.service;

import com.cats.DAO.CloudCatFotoDAO;
import com.cats.exception.IllegalFileFormat;
import com.google.cloud.storage.Blob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;

/**
 * ensure operations on file
 */
@Service
public class ImgCloudService {
    private static Logger LOGGER = LoggerFactory.getLogger(ImgCloudService.class);
    String[] allowedExt = {"jpg", "jpeg", "png", "gif"};

    @Autowired
    private CloudCatFotoDAO cloudCatFotoDAO;

    public void removeFile(String newName) {
        cloudCatFotoDAO.fileRemove(newName);
    }

    public void saveFile(MultipartFile file, String newName) {
        cloudCatFotoDAO.fileUpload(file, newName);
    }

    /**
     * @param fileName name of file
     * @return
     */
    public String getCatImage(String fileName) {
        Blob catImg = cloudCatFotoDAO.fileDownload(fileName);
        String encodeCatImg = Base64.getEncoder().encodeToString(catImg.getContent());
        return encodeCatImg;
    }

    /**
     * Check File Type
     * <p>
     * Using your own exception compatible with JDK 7:
     * <ul>
     * <li>multi-catch exception</li>
     * <li>rethrow exceptions</li>
     * <li>more precise rethrow, exceptions chain ('Caused by:' in err message)</li>
     * </ul>
     *
     * @param file
     * @throws Exception there is no need to declare IllegalArgumentException and IllegalFileFormat exceptions in throws section (are Exceptions subclass)
     */
    public void checkFileType(MultipartFile file) throws Exception {
        final String fileName = file.getOriginalFilename();

        try {
            String extension = fileName.substring(fileName.lastIndexOf('.') + 1);

            if (fileName == null || fileName.isEmpty()) {
                throw new IllegalArgumentException("Invalid file name; File name is empty.");
            }
            if (!fileName.contains(".")) {
                throw new IllegalArgumentException("Invalid file name; File name does not contain the extension");
            }
            if (!file.getName().equals("imgFile")) {
                throw new IllegalArgumentException("Invalid file format [" + file.getName() + "]; only 'imgFile' is allowed.");
            }
            if (!validExtension(allowedExt, extension)) {
                throw new IllegalFileFormat("Invalid file extension [" + extension + "].");
            }
        } catch (IllegalArgumentException | IllegalFileFormat e) {
            throw e;
        } catch (Exception e) {
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

}