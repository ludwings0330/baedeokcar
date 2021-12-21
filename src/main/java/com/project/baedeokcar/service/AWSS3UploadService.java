package com.project.baedeokcar.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.InputStream;

@RequiredArgsConstructor
@Component
public class AWSS3UploadService implements UploadService {

    // properties 에서 access-key, secret-key, region 정보를 가져와서 연결해준다.
    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    // properties 에서 bucket 이름을 가져온다.
    // amazonS3 -> bucket 으로 putObject 를 이용하여 파일을 업로드한다.
    private String bucket;

    @Override
    public void uploadFile(InputStream inputStream, ObjectMetadata objectMetadata, String fileName) {
        amazonS3.putObject(new PutObjectRequest(bucket, fileName, inputStream, objectMetadata).withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public String getFileUrl(String fileName) {
        return amazonS3.getUrl(bucket, fileName).toString();
    }
}
