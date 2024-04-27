package com.m.blog.aggregate.file.adapter.in.web;

import com.m.blog.global.customAnnotation.Mapper;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadRequest;

import java.io.IOException;

import static com.m.blog.aggregate.file.application.domain.File_.withSnowflakeId;

@Mapper
class FileEntrypointMapper {
    public static File_ of(FileDownloadRequest request){
        return File_.withDownloadCondition(request.getId());
    }



    public static File_ of(FileUploadRequest request, String directoryName) throws IOException {
        String originalFileName = request.getMultipartFile().getOriginalFilename();
        byte[] data = request.getMultipartFile().getBytes();
        String postingId = request.getPostingId();

        assert originalFileName != null;

        return withSnowflakeId(originalFileName, directoryName, postingId, data);
    }
}
