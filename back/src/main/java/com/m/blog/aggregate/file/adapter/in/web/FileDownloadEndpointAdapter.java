package com.m.blog.aggregate.file.adapter.in.web;

import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadResponse;
import com.m.blog.global.customAnnotation.Adapter;
import com.m.blog.aggregate.file.application.domain.File_;
import com.m.blog.aggregate.file.application.port.in.web.FileDownloadEndpointPort;
import com.m.blog.aggregate.file.application.usecase.FileDownloadUsecase;
import com.m.blog.aggregate.file.infrastructure.web.dto.FileDownloadRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Adapter
@RequiredArgsConstructor
public class FileDownloadEndpointAdapter implements FileDownloadEndpointPort {
    private final FileDownloadUsecase fileDownloadUsecase;

    @Override
    public FileDownloadResponse download(FileDownloadRequest request) throws IOException {
        File_ file = fileDownloadUsecase.download(File_.withDownloadCondition(request.getId()));

        return FileEntrypointMapper.toDownloadResposne(file);
    }
}
