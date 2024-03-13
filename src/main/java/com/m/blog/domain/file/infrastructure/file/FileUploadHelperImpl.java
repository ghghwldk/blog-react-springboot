package com.m.blog.domain.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.m.blog.domain.file.application.domain.UploadFile;
import com.m.blog.global.properties.FileProperties;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Slf4j
class FileUploadHelperImpl implements FileUploadHelper {
    private final FileProperties fileProperties;
    private final AmazonS3Client amazonS3Client;

    @Override
    public Optional<File> convert(UploadFile uploadFile) throws IOException {
        File convertFile = new File(uploadFile.getOriginalFileName());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(uploadFile.getData());
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
    public void uploadOnLocal(UploadFile uploadFile) throws IOException{
        File file = null;

        log.info("file is uploading on the local pc");
        try{
            InputStream fileStream = new ByteArrayInputStream(uploadFile.getData());
            String key = uploadFile.getS3Key(fileProperties.getDirectoryName());

            file = new File(key);

            FileUtils.copyInputStreamToFile(fileStream, file);
        }catch (IOException e) {
            FileUtils.deleteQuietly(file);
            throw e;
        }
    }

    private void putS3(File file, UploadFile uploadFile) {
        String key= uploadFile.getS3Key(fileProperties.getDirectoryName());

        amazonS3Client
                .putObject(new PutObjectRequest(fileProperties.getBucket(), key, file)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public void uploadOnS3(UploadFile fileVo) throws IOException{
        File file = null;

        try{
            file = convert(fileVo)
                    .orElseThrow(() ->
                            new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다.")
                    );

            putS3(file, fileVo);
        } finally{
            assert file != null;
            removeNewFile(file);
        }
    }
}
