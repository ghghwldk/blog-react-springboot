package com.m.blog.aggregate.file.adapter.out.file;

import com.m.blog.aggregate.file.adapter.out.file.util.FileDownloadUtil;
import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.out.file.FileDownloadPort;
import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.io.InputStream;


@Adapter
@RequiredArgsConstructor
public class FileDownloadAdapter implements FileDownloadPort {
    private final FileDownloadUtil fileDownloadUtil;

    @Override
    public File_ get(File_ file) throws IOException {
        InputStream inputStream = fileDownloadUtil.get(file);

        return file.setDataAfterDownload(inputStream.readAllBytes());
    }
}
