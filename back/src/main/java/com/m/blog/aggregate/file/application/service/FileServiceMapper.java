package com.m.blog.aggregate.file.application.service;

import com.m.blog.aggregate.file.adapter.in.web.FileUploadCommand;
import com.m.blog.aggregate.file.application.domain.File_;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

import static com.m.blog.aggregate.file.application.domain.File_.withoutId;

public class FileServiceMapper {
    static File_ of(String postingId, MultipartFile multipartFile, String directoryName) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        byte[] data = multipartFile.getBytes();

        assert originalFileName != null;

        return withoutId(originalFileName, directoryName, postingId, data);
    }
}
