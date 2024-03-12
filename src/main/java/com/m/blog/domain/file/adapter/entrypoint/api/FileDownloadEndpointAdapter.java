package com.m.blog.domain.file.adapter.entrypoint.api;

import com.m.blog.common.Adapter;
import com.m.blog.domain.file.application.domain.DownloadCondition;
import com.m.blog.domain.file.application.domain.DownloadContent;
import com.m.blog.domain.file.application.port.entrypoint.api.FileDownloadEndpointPort;
import com.m.blog.domain.file.application.port.file.FileDownloadPort;
import com.m.blog.domain.file.application.usecase.FileDownloadUsecase;
import com.m.blog.domain.file.infrastructure.web.dto.FileDownloadRequest;
import com.m.blog.domain.file.application.domain.DownloadFileInfo;
import com.m.blog.domain.file.application.port.persistence.ReadFilePort;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Adapter
@RequiredArgsConstructor
public class FileDownloadEndpointAdapter implements FileDownloadEndpointPort {\
    private final FileDownloadUsecase fileDownloadUsecase;

    private ResponseEntity<Resource> get(DownloadContent downloadContent) throws UnsupportedEncodingException {
        Resource resource = new InputStreamResource(downloadContent.getData());
        String header = downloadContent.getDownloadFileInfo()
                .getHeaderValues();

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .header(HttpHeaders.CONTENT_DISPOSITION, header)
                .body(resource);
    }

    @Override
    public ResponseEntity<Resource> download(FileDownloadRequest request) throws IOException {
        DownloadCondition downloadCondition = FileMapper.of(request);

        DownloadContent downloadContent = fileDownloadUsecase.downlaod(downloadCondition);

        return get(downloadContent);
    }
}
