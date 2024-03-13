package com.m.blog.domain.file.adapter.file;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.DownloadContent;
import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.port.file.FileDownloadPort;
import com.m.blog.domain.file.infrastructure.file.FileDownloadHelper;
import com.m.blog.global.properties.FileProperties;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;


@Adapter
@RequiredArgsConstructor
public class FileDownloadAdapter implements FileDownloadPort {
    private final FileProperties fileProperties;
    private final FileDownloadHelper fileDownloadHelper;

    @Override
    public DownloadContent get(DownloadFile downloadFile) throws IOException {
        InputStream inputStream = fileProperties.isLocal()?
                fileDownloadHelper.getLocalResource(downloadFile):
                fileDownloadHelper.getS3Resource(downloadFile);

        return DownloadContent.builder()
                .data(inputStream)
                .downloadFile(downloadFile)
                .build();
    }
}
