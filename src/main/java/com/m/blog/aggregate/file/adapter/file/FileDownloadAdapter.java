package com.m.blog.aggregate.file.adapter.file;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.domain.BlogFile;
import com.m.blog.aggregate.file.application.port.file.FileDownloadPort;
import com.m.blog.aggregate.file.infrastructure.file.FileDownloadUtil;
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
    public BlogFile get(BlogFile blogFile) throws IOException {
        InputStream inputStream = fileProperties.isForLocal()?
                fileDownloadUtil.getLocalResource(blogFile):
                fileDownloadUtil.getS3Resource(blogFile);

        return FileEntrypointMapper.from(inputStream.readAllBytes(), blogFile);
    }
}
