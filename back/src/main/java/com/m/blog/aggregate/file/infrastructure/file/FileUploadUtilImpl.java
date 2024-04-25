package com.m.blog.aggregate.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.m.blog.global.exception.CustomIllegalArgumentException;
import com.m.blog.global.properties.AwsProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
class FileUploadUtilImpl implements FileUploadUtil {
    private final AwsProperties awsProperties;
    private final AmazonS3Client amazonS3Client;


    private Optional<File> convert(String originalFileName, byte[] data) throws IOException {
        File convertFile = new File(originalFileName);
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(data);
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    @Override
    public void uploadOnLocal(String fileKey, byte[] data) throws IOException{
        File file = null;

        log.info("file is uploading on the local pc");
        try{
            InputStream fileStream = new ByteArrayInputStream(data);
            String key = fileKey;

            file = new File(key);

            FileUtils.copyInputStreamToFile(fileStream, file);
        }catch (IOException e) {
            FileUtils.deleteQuietly(file);
            throw e;
        }
    }

    private void putS3(File file, String fileKey) {
        String key= fileKey;

        amazonS3Client
                .putObject(new PutObjectRequest(awsProperties.getS3().getBucket(), key, file)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public void uploadOnS3(String originalFileName, String fileKey, byte[] data) throws IOException{
        File file = null;

        try{
            file = convert(originalFileName, data)
                    .orElseThrow(() ->new CustomIllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다."));

            putS3(file, fileKey);
        } finally{
            assert file != null;
            FileUtils.deleteQuietly(file);
        }
    }
}
