package com.m.blog.aggregate.file.adapter.in.web;

import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadResponse;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadResponse;
import com.m.blog.global.customAnnotation.Mapper;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.global.exception.GetFileNullException;

import java.io.IOException;

import static com.m.blog.aggregate.file.application.domain.File_.withoutId;

@Mapper
class FileEntrypointMapper {
    static File_ of(FileDownloadRequest request){
        return File_.withDownloadCondition(request.getId());
    }

    static FileDownloadResponse toDownloadResposne(File_ file){
        return FileDownloadResponse.builder()
                .originalFileName(file.getOriginalFileName())
                .data(file.getDownloadData().orElseThrow(GetFileNullException::new))
                .build();
    }

    static FileUploadResponse toUploadResponse(File_ file){
        return FileUploadResponse.builder()
                .originalFileName(file.getOriginalFileName())
                .fileName(file.getFileId().getValue())
                .downloadUrl(file.getDownloadUrl())
                .build();
    }

    static File_ of(FileUploadRequest request, String directoryName) throws IOException {
        String originalFileName = request.getMultipartFile().getOriginalFilename();
        byte[] data = request.getMultipartFile().getBytes();
        String postingId = request.getPostingId();

        assert originalFileName != null;

        return withoutId(originalFileName, directoryName, postingId, data);
    }
}
