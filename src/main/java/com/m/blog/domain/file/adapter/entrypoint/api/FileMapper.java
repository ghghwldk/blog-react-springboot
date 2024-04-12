package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.domain.file.application.domain.BaseFile;
import com.m.blog.domain.file.application.domain.DownloadTrialCondition;
import com.m.blog.domain.file.application.domain.UploadedFile;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.domain.file.infrastructure.web.dto.FileUploadRequest;
import com.m.blog.domain.posting.application.domain.Posting;

import java.io.IOException;

public class FileMapper {
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
