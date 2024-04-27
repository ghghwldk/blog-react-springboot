package com.m.blog.aggregate.file.adapter.in.web;

import com.m.blog.global.customAnnotation.Mapper;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;

import java.io.IOException;

import static com.m.blog.aggregate.file.application.domain.BlogFile.withSnowflakeId;

@Mapper
class FileEntrypointMapper {
    public static BlogFile of(FileDownloadRequest request){
        return BlogFile.withDownloadCondition(request.getId());
    }



    public static BlogFile of(FileUploadRequest request, String directoryName) throws IOException {
        String originalFileName = request.getMultipartFile().getOriginalFilename();
        byte[] data = request.getMultipartFile().getBytes();
        String postingId = request.getPostingId();

        assert originalFileName != null;

        return withSnowflakeId(originalFileName, directoryName, postingId, data);
    }
}
