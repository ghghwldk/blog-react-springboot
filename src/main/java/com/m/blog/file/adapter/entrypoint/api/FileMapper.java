package com.m.blog.file.adapter.entrypoint.api;

import com.m.blog.common.Mapper;
import com.m.blog.file.application.domain.BaseFile;
import com.m.blog.file.application.domain.DownloadTrialCondition;
import com.m.blog.file.application.domain.UploadedFile;
import com.m.blog.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.boardCollection.application.domain.Posting;

import java.io.IOException;

@Mapper
class FileMapper {
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
