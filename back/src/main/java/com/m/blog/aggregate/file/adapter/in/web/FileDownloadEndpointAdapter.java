package com.m.blog.aggregate.file.adapter.in.web;

import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.in.web.FileDownloadEndpointPort;
import com.m.blog.aggregate.file.application.usecase.FileDownloadUsecase;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

@Adapter
@RequiredArgsConstructor
public class FileDownloadEndpointAdapter implements FileDownloadEndpointPort {
    private final FileDownloadUsecase fileDownloadUsecase;

    @Override
    public FileDownloadResponse download(FileDownloadRequest request) throws IOException {
        File_ file = fileDownloadUsecase.download(File_.getDownloadCondition(request.getId()));

        return FileEntrypointMapper.toDownloadResposne(file);
    }
}
