package com.m.blog.domain.file.adapter.file;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.port.file.FileDownloadPort;
import com.m.blog.domain.file.application.port.persistence.ReadFilePort;
import com.m.blog.domain.file.infrastructure.file.FileDownloadHelper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;


@Adapter
@RequiredArgsConstructor
public class FileDownloadAdapter implements FileDownloadPort {
    private final ReadFilePort readFilePort;
    private final FileDownloadHelper fileDownloadHelper;

    @Value("${file.isLocal}")
    private boolean isLocal;

    @Override
    public Resource getResource(DownloadFile fileVo) throws IOException {
        return isLocal?
                fileDownloadHelper.getLocalResource(fileVo):
                fileDownloadHelper.getS3Resource(fileVo);
    }


}
