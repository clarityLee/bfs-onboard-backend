package com.bfs.onboard.service;

import com.bfs.onboard.dao.UnclaimedFileDao;
import com.bfs.onboard.domain.UnclaimedFile;
import com.bfs.onboard.response.UploadResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class UpDownloadService {

    private UnclaimedFileDao unclaimedFileDao;

    @Autowired
    public void setUnclaimedFileDao(UnclaimedFileDao unclaimedFileDao) {
        this.unclaimedFileDao = unclaimedFileDao;
    }

    private FileStoreService fileStoreService;

    @Autowired
    public void setFileStoreService(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @Transactional
    public UploadResponse uploadUnclaimed(String bucketName, MultipartFile file) {

        UploadResponse response = upload(bucketName, file);

        UnclaimedFile ucf = new UnclaimedFile();
        ucf.setPath(response.getPath());
        ucf.setCreateDate(LocalDateTime.now());
        unclaimedFileDao.save(ucf);

        return response;
    }

    public UploadResponse upload(String bucketName, MultipartFile file) {

        UploadResponse response = new UploadResponse();
        if (file.isEmpty()) {
            response.setErrorMessage("Empty file");
            return response;
        }

        String path = String.format("%s/%s", bucketName, UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        String pathAndFileName = path + "/" + fileName;
        if (pathAndFileName.length() > 255) {
            response.setErrorMessage("File name too long");
            return response;
        }

        try {
            fileStoreService.upload(path, fileName, file.getContentType(), file.getSize(), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }

        response.setSuccess(true);
        response.setPath(pathAndFileName);
        return response;
    }

}
