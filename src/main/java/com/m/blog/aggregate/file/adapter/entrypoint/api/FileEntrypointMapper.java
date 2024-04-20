package com.m.blog.aggregate.file.adapter.entrypoint.api;

import com.m.blog.global.customAnnotation.Mapper;
import com.m.blog.aggregate.file.application.domain.BaseFile;
import com.m.blog.aggregate.file.application.domain.BlogFile.DownloadTrialCondition;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;

import java.io.IOException;

@Mapper
class FileEntrypointMapper {
    public static BlogFile.DownloadTrialCondition of(FileDownloadRequest request){
        return BlogFile.DownloadTrialCondition.builder()
                .fileId(BaseFile.FileId.builder().value(request.getId()).build())
                .build();
    }



    public static BlogFile of(FileUploadRequest request, String directoryName) throws IOException {
        String originalFileName = request.getMultipartFile().getOriginalFilename();
        byte[] data = request.getMultipartFile().getBytes();
        String postingId = request.getPostingId();

        assert originalFileName != null;

        return new BlogFile(originalFileName,
                directoryName,
                data,
                Posting.PostingId.builder().value(postingId).build());
    }
}
