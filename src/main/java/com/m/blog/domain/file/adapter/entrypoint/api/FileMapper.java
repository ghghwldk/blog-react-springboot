package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.domain.UploadFile;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public class FileMapper {
    public static DownloadFile.TrialCondition of(FileDownloadRequest request){
        return DownloadFile.TrialCondition.builder()
                .assignedFileName(request.getFileName())
                .build();
    }



    public static UploadFile of(MultipartFile multipartFile, String directoryName) throws IOException {
        String originalFileName = multipartFile.getOriginalFilename();
        assert originalFileName != null;

        return UploadFile.builder()
                .originalFileName(originalFileName)
                .directoryName(directoryName)
                .data(multipartFile.getBytes())
                .build();
    }
}
