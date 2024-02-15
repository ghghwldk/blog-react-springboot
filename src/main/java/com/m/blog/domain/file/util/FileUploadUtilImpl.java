package com.m.blog.domain.file.util;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.m.blog.domain.file.vo.UploadFileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
public class FileUploadUtilImpl implements FileUploadUtil {
    @Value("${file.directory}") private String directoryName; // static
    @Value("${cloud.aws.s3.bucket:#{null}}") private String bucket;

    private final AmazonS3Client amazonS3Client;

    @Override
    public Optional<File> convert(MultipartFile file) throws IOException {
        File convertFile = new File(file.getOriginalFilename());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(file.getBytes());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    @Override
    public void removeNewFile(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다."); }
    }

    @Override
    public void uploadOnLocal(UploadFileVo fileVo) throws IOException{
        File file = null;

        log.info("file is uploading on the local pc");
        try{
            InputStream fileStream = fileVo.getMultipartFile().getInputStream();
            file = new File(this.directoryName + "/" + fileVo.getSavedFileName());
            FileUtils.copyInputStreamToFile(fileStream, file);
        }catch (IOException e) {
            FileUtils.deleteQuietly(file);
            throw e;
        }
    }

    private void putS3(File uploadFile, UploadFileVo fileVo) {
        String key= this.directoryName + "/" + fileVo.getSavedFileName();

        amazonS3Client
                .putObject(new PutObjectRequest(bucket, key, uploadFile)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public void uploadOnS3(UploadFileVo fileVo) throws IOException{
        File file = null;

        try{
            file = convert(fileVo.getMultipartFile())
                    .orElseThrow(() ->
                            new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다.")
                    );

            putS3(file, fileVo);
        }catch (Exception e){
            throw e;
        }finally{
            removeNewFile(file);
        }
    }
}
