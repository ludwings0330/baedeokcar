package com.project.baedeokcar.service;

import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class FileUploadService {

    private final UploadService awsS3UploadService;

    public String uploadImage(MultipartFile file) {

        String savedFileName = createSaveFileName(file.getOriginalFilename());

        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentLength(file.getSize());
        objectMetadata.setContentType(file.getContentType());

        try {
            InputStream inputStream = file.getInputStream();
            awsS3UploadService.uploadFile(inputStream, objectMetadata, savedFileName);
        } catch (IOException e) {
            throw new IllegalStateException("파일 변환 error");
        }
        String savedFileUrl = awsS3UploadService.getFileUrl(savedFileName);

        return savedFileUrl;
    }

    private String createSaveFileName(String originFileName) {
        return UUID.randomUUID().toString().concat(getFileExtension(originFileName));
    }

    private String getFileExtension(String originFileName) {
        return originFileName.substring(originFileName.lastIndexOf('.'));
    }
}
