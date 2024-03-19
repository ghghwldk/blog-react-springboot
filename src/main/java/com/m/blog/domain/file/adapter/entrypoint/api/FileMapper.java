package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.application.domain.UploadFile;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.UUID;

public class FileMapper {
    public static DownloadFile.TrialCondition of(FileDownloadRequest request){
        return DownloadFile.TrialCondition.builder()
                .fileName(request.getFileName())
                .build();
    }

    public static UploadFile of(MultipartFile multipartFile, String directoryName) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        assert originalFileName != null;

        String extension = originalFileName.substring(originalFileName.lastIndexOf("."));

        return UploadFile.builder()
                .originalFileName(originalFileName)
                .extension(extension)
                .assignedFileName(UUID.randomUUID() + extension)
                .directoryName(directoryName)
                .data(multipartFile.getBytes())
                .build();
    }
}
