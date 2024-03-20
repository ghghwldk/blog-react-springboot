package com.m.blog.domain.file.adapter.file;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.DownloadResult;
import com.m.blog.domain.file.application.domain.BaseFile;
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
    public DownloadResult get(BaseFile baseFile) throws IOException {
        InputStream inputStream = fileProperties.isLocal()?
                fileDownloadUtil.getLocalResource(baseFile):
                fileDownloadUtil.getS3Resource(baseFile);

        return DownloadResult.from(inputStream, baseFile);
    }
}
