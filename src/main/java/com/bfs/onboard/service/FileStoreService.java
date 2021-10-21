package com.bfs.onboard.service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.util.IOUtils;
import com.bfs.onboard.exception.MyS3FileNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Service
public class FileStoreService {

    private AmazonS3 amazonS3;

    @Autowired
    public void setAmazonS3(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public void upload(String path, String fileName,
                       String contentType, long contentLength,
                       InputStream inputStream) {

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(contentType);
        objectMetadata.setContentLength(contentLength);

        try {
            // saves the file to Amazon S3 bucket.
            amazonS3.putObject(path, fileName, inputStream, objectMetadata);
        } catch (AmazonServiceException e) {
            throw new IllegalStateException("Failed to upload the file", e);
        }
    }

    public Map<String, Object> download(String path, String key) {

        try {
            S3Object object = amazonS3.getObject(path, key);
            String contentType = object.getObjectMetadata().getContentType();

            byte[] byteArray = IOUtils.toByteArray(object.getObjectContent());
            Map<String, Object> map = new HashMap<>();
            map.put("byteArray", byteArray);
            map.put("contentType", contentType);
            return map;
        } catch (com.amazonaws.services.s3.model.AmazonS3Exception e) {
            throw new MyS3FileNotFoundException();
        } catch (AmazonServiceException | IOException e) {
            throw new IllegalStateException("Failed to download the file", e);
        }
    }

}