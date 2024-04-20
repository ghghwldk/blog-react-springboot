package com.m.blog.aggregate.file.adapter.entrypoint.api;

import com.m.blog.common.Mapper;
import com.m.blog.aggregate.file.application.domain.BaseFile;
import com.m.blog.aggregate.file.application.domain.DownloadTrialCondition;
import com.m.blog.aggregate.file.application.domain.UploadedFile;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.aggregate.boardCollection.application.domain.Posting;

import java.io.IOException;

@Mapper
class FileEntrypointMapper {
    public static DownloadTrialCondition of(FileDownloadRequest request){
        return DownloadTrialCondition.builder()
                .fileId(BaseFile.FileId.builder().value(request.getId()).build())
                .build();
    }



    public static UploadedFile of(FileUploadRequest request, String directoryName) throws IOException {
        String originalFileName = request.getMultipartFile().getOriginalFilename();
        byte[] data = request.getMultipartFile().getBytes();
        String postingId = request.getPostingId();

        assert originalFileName != null;

        return new UploadedFile(originalFileName,
                directoryName,
                data,
                Posting.PostingId.builder().value(postingId).build());
    }
}
