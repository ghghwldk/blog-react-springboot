package com.m.blog.aggregate.file.adapter.out.file;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


@Slf4j
public class LocalFileUploadUtilImpl implements FileUploadUtil{
    public void upload(String fileId, String pathName, byte[] data) throws IOException {
        File file = null;

        log.info("file is uploading on the local pc");
        try{
            InputStream fileStream = new ByteArrayInputStream(data);

            file = new File(pathName);
            FileUtils.copyInputStreamToFile(fileStream, file);
        }catch (IOException e) {
            FileUtils.deleteQuietly(file);
            throw e;
        }
    }
}
