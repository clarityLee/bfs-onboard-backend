package com.bfs.onboard.controller;

import com.bfs.onboard.response.UploadResponse;
import com.bfs.onboard.service.FileStoreService;
import com.bfs.onboard.service.UpDownloadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@RestController
@CrossOrigin
public class UploadDLController {

    private Set<MediaType> mediaTypes;
    private Set<String> s3Buckets;
    private FileStoreService fileStoreService;
    private UpDownloadService userUploadService;

    @Autowired
    public void setS3Buckets(Set<String> s3Buckets) {
        this.s3Buckets = s3Buckets;
    }

    @Autowired
    public void setFileStoreService(FileStoreService fileStoreService) {
        this.fileStoreService = fileStoreService;
    }

    @Autowired
    public void setUserUploadService(UpDownloadService userUploadService) {
        this.userUploadService = userUploadService;
    }

    @PostConstruct
    public void init() {
        mediaTypes = new HashSet<>(Arrays.asList(
                MediaType.IMAGE_JPEG,
                MediaType.IMAGE_PNG,
                MediaType.IMAGE_GIF,
                MediaType.APPLICATION_PDF));
    }

    @PostMapping(value = "/upload/{bucketName}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<UploadResponse> upload(
            @PathVariable(name = "bucketName") String bucketName,
            @RequestParam MultipartFile file) {

        if (!s3Buckets.contains(bucketName))
            return new ResponseEntity<>(new UploadResponse(), HttpStatus.BAD_REQUEST);

        UploadResponse res = userUploadService.uploadUnclaimed(bucketName, file);
        return new ResponseEntity<>(res, res.getSuccess() ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    @GetMapping(value = "/download/{bucketName}/{uuid}/{filename}",
            produces = {MediaType.IMAGE_PNG_VALUE, MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_GIF_VALUE,
                    MediaType.APPLICATION_PDF_VALUE, MediaType.APPLICATION_OCTET_STREAM_VALUE})
    public ResponseEntity<byte[]> download(@PathVariable(name = "bucketName") String bucketName,
                                           @PathVariable(name = "uuid") String uuid,
                                           @PathVariable(name = "filename") String filename) {

        Map<String, Object> map = fileStoreService.download(bucketName + "/" + uuid, filename);
        MediaType mediaType = MediaType.parseMediaType((String) map.get("contentType"));
        boolean previewAble = mediaTypes.contains(mediaType);

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.add(HttpHeaders.CONTENT_DISPOSITION,
                (previewAble ? "inline" : "attachment") + "; filename=" + filename);
        responseHeaders.setContentType(previewAble ? mediaType : mediaType.APPLICATION_OCTET_STREAM);
        return new ResponseEntity<>((byte[]) map.get("byteArray"), responseHeaders, HttpStatus.OK);
    }
}
