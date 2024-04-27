package com.m.blog.aggregate.file.adapter.in.web;

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
    public ResponseEntity<Resource> download(FileDownloadRequest request) throws IOException {
        File_ file = fileDownloadUsecase.download(File_.withDownloadCondition(request.getId()));

        return get(file);
    }

    private String getHeaderValues(String originalFileName) throws UnsupportedEncodingException {
        String encoded = URLEncoder.encode(originalFileName, StandardCharsets.UTF_8)
                .replaceAll("\\+", "%20");

        return "attachment; filename=\"" + encoded + "\"";
    }

    private ResponseEntity<Resource> get(File_ file) throws UnsupportedEncodingException {
        Resource resource = new InputStreamResource(new ByteArrayInputStream(file.getData()));
        String header = getHeaderValues(file.getOriginalFileName());

        return get(resource, header);
    }

    private ResponseEntity<Resource> get(Resource resource, String header){
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(MediaType.APPLICATION_OCTET_STREAM_VALUE))
                .header(HttpHeaders.CONTENT_DISPOSITION, header)
                .body(resource);
    }
}
