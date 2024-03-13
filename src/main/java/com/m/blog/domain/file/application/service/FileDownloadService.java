package com.m.blog.domain.file.application.service;

import com.m.blog.common.UseCase;
import com.m.blog.domain.file.application.domain.DownloadCondition;
import com.m.blog.domain.file.application.domain.DownloadContent;
import com.m.blog.domain.file.application.domain.DownloadFile;
import com.m.blog.domain.file.application.port.file.FileDownloadPort;
import com.m.blog.domain.file.application.port.persistence.ReadFilePort;
import com.m.blog.domain.file.application.usecase.FileDownloadUsecase;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@UseCase
@RequiredArgsConstructor
public class FileDownloadService implements FileDownloadUsecase {
    private final ReadFilePort readFilePort;
    private final FileDownloadPort fileDownloadPort;

    @Override
    public DownloadContent downlaod(DownloadCondition condition) throws IOException {
        DownloadFile downloadFile = readFilePort.get(condition);

        return  fileDownloadPort.get(downloadFile);
    }
}
