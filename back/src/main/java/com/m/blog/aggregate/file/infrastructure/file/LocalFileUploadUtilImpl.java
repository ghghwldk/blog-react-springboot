package com.m.blog.aggregate.file.infrastructure.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@Slf4j
public class LocalFileUploadUtilImpl implements FileUploadUtil{
    public void upload(String fileKey, byte[] data) throws IOException {
        File file = null;

        log.info("file is uploading on the local pc");
        try{
            InputStream fileStream = new ByteArrayInputStream(data);
            String key = fileKey;

            FileUtils.copyInputStreamToFile(fileStream, new File(key));
        }catch (IOException e) {
            FileUtils.deleteQuietly(file);
            throw e;
        }
    }
}
