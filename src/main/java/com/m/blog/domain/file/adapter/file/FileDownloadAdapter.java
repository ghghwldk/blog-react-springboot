package com.m.blog.domain.file.adapter.file;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.DownloadedFile;
import com.m.blog.domain.file.application.domain.File;
import com.m.blog.domain.file.application.port.file.FileDownloadPort;
import com.m.blog.domain.file.infrastructure.file.FileDownloadUtil;
import com.m.blog.global.properties.FileProperties;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;


@Adapter
@RequiredArgsConstructor
public class FileDownloadAdapter implements FileDownloadPort {
    private final FileProperties fileProperties;
    private final FileDownloadUtil fileDownloadUtil;

    @Override
    public DownloadedFile get(File file) throws IOException {
        InputStream inputStream = fileProperties.isLocal()?
                fileDownloadUtil.getLocalResource(file):
                fileDownloadUtil.getS3Resource(file);

        return DownloadedFile.from(inputStream.readAllBytes(), file);
    }
}
