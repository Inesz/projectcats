package com.cats.DAO;

import com.google.api.gax.paging.Page;
import com.google.cloud.storage.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

public class CloudCatFotoDAO {
    private static Logger LOGGER = LoggerFactory.getLogger(CloudCatFotoDAO.class);
    final String bucketName = "animals_photos";


    public CloudCatFotoDAO() {
        logBucketList();
    }

    public void fileUpload(MultipartFile file, String fileName) {

        try {
            byte[] bytes = file.getBytes();
            InputStream inputStream = new BufferedInputStream(file.getInputStream());

            Storage storage = StorageOptions.getDefaultInstance().getService();
            BlobId blobId = BlobId.of(bucketName, fileName);
            BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType(file.getContentType()).build();

            Blob blob = storage.create(blobInfo, inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logBucketList() {
        Storage storage = StorageOptions.getDefaultInstance().getService();
        LOGGER.info("BEGIN: Bucket list");

        Page<Bucket> buckets = storage.list();
        for (Bucket bucket : buckets.iterateAll()) {
            LOGGER.info("Bucket name : " + bucket.getName());
        }

        LOGGER.info("END: Bucket list");
    }
}