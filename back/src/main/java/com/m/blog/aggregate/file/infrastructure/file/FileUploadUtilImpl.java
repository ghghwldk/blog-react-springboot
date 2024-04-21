package com.m.blog.aggregate.file.infrastructure.file;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.m.blog.aggregate.file.application.domain.BlogFile;
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

    @Override
    public Optional<File> convert(BlogFile blogFile) throws IOException {
        File convertFile = new File(blogFile.getOriginalFileName());
        if(convertFile.createNewFile()) {
            try (FileOutputStream fos = new FileOutputStream(convertFile)) {
                fos.write(blogFile.getData());
            }
            return Optional.of(convertFile);
        }
        return Optional.empty();
    }

    @Override
    public void remove(File targetFile) {
        if (targetFile.delete()) {
            log.info("파일이 삭제되었습니다.");
        } else {
            log.info("파일이 삭제되지 못했습니다."); }
    }

    @Override
    public void uploadOnLocal(BlogFile blogFile) throws IOException{
        File file = null;

        log.info("file is uploading on the local pc");
        try{
            InputStream fileStream = new ByteArrayInputStream(blogFile.getData());
            String key = blogFile.getFileKey();

            file = new File(key);

            FileUtils.copyInputStreamToFile(fileStream, file);
        }catch (IOException e) {
            FileUtils.deleteQuietly(file);
            throw e;
        }
    }

    private void putS3(File file, BlogFile blogFile) {
        String key= blogFile.getFileKey();

        amazonS3Client
                .putObject(new PutObjectRequest(awsProperties.getS3().getBucket(), key, file)
                        .withCannedAcl(CannedAccessControlList.PublicRead));
    }

    @Override
    public void uploadOnS3(BlogFile fileVo) throws IOException{
        File file = null;

        try{
            file = convert(fileVo)
                    .orElseThrow(() ->
                            new IllegalArgumentException("MultipartFile -> File로 전환이 실패했습니다.")
                    );

            putS3(file, fileVo);
        } finally{
            assert file != null;
            remove(file);
        }
    }
}
